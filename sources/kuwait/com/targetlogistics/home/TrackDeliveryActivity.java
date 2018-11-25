package kuwait.com.targetlogistics.home;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap$OnCameraIdleListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.concurrent.Future;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityTrackerBinding;
import kuwait.com.targetlogistics.history.HistoryDetailActivity;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;

public class TrackDeliveryActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap$OnCameraIdleListener, ConnectionCallbacks, OnConnectionFailedListener, LocationListener {
    public static final byte REQUEST_CHECK_SETTINGS = (byte) 111;
    LatLng LatLngPick;
    private final int MY_PERMISSIONS_REQUEST_LOCATION = Callback.DEFAULT_DRAG_ANIMATION_DURATION;
    private final int MY_PERMISSIONS_REQUEST_PHONE_CALL = 100;
    Future<String> apiCallForLocation;
    private ActivityTrackerBinding binding;
    Location currentLoc;
    double customer_lat;
    double customer_long;
    int delay = 2000;
    double driver_lat;
    double driver_long;
    Handler handler = new Handler();
    boolean isApicallFirstTime = false;
    LatLng latLng;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap map;
    SupportMapFragment mapFragment;
    Runnable runnable = new Runnable() {
        public void run() {
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityTrackerBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init(this.binding.getRoot());
    }

    protected void init(View view) {
        this.me.setFullScreen();
        if (this.mapFragment == null) {
            this.mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            this.mapFragment.getMapAsync(this);
            this.handler = new Handler();
            this.binding.imgClose.setOnClickListener(this);
            this.binding.tvCallCenter.setOnClickListener(this);
            this.binding.tvViewOrder.setOnClickListener(this);
            requestLocationPermission();
        }
        buildGoogleApiClient();
        apiCallForDuration();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                finish();
                return;
            case R.id.tvCallCenter:
                requestPhonePermission();
                return;
            case R.id.tvViewOrder:
                startActivity(new Intent(this, HistoryDetailActivity.class));
                setRedirectClass(TrackDeliveryActivity.class);
                return;
            default:
                super.onClick(v);
                return;
        }
    }

    public void apiCallForDuration() {
        this.me.showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.TRACK_ORDER);
        requestParams.put("order_id", "1");
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallGet(BaseUrl.SUB_URL, requestParams).enqueue(new retrofit2.Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                TrackDeliveryActivity.this.me.dismissDialog();
                if (!response.isSuccessful()) {
                    Utils.printMsg("error message", ErrorUtils.parseError(response).message());
                } else if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    TrackDeliveryActivity.this.parseDistance(response);
                } else {
                    Utils.printMsg("ETA :", "No route found");
                }
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                TrackDeliveryActivity.this.me.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }

    protected synchronized void buildGoogleApiClient() {
        this.mGoogleApiClient = new Builder(this.me).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }

    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        apiCallForDuration();
    }

    private void getCurrentLocation() {
        if (Utils.checkPermission(this.me, "android.permission.ACCESS_FINE_LOCATION") && Utils.checkPermission(this.me, "android.permission.ACCESS_COARSE_LOCATION")) {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
            if (mLastLocation == null) {
                createLocationRequest();
            } else if (this.map != null) {
                this.currentLoc = mLastLocation;
                this.latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                this.LatLngPick = this.latLng;
                this.map.moveCamera(CameraUpdateFactory.newLatLng(this.latLng));
                this.map.animateCamera(CameraUpdateFactory.zoomBy(10.0f));
            }
        }
    }

    protected void createLocationRequest() {
        final LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(100);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        LocationServices.SettingsApi.checkLocationSettings(this.mGoogleApiClient, builder.build()).setResultCallback(new ResultCallback<LocationSettingsResult>() {
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                Status status = locationSettingsResult.getStatus();
                LocationSettingsStates state = locationSettingsResult.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case 0:
                        if (ActivityCompat.checkSelfPermission(TrackDeliveryActivity.this.me, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(TrackDeliveryActivity.this.me, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                            LocationServices.FusedLocationApi.requestLocationUpdates(TrackDeliveryActivity.this.mGoogleApiClient, mLocationRequest, TrackDeliveryActivity.this);
                            return;
                        }
                        return;
                    case 6:
                        try {
                            status.startResolutionForResult(TrackDeliveryActivity.this.me, 111);
                            return;
                        } catch (SendIntentException e) {
                            return;
                        }
                    default:
                        return;
                }
            }
        });
    }

    public void onLocationChanged(Location location) {
        if (this.currentLoc != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.mGoogleApiClient, this);
            return;
        }
        this.currentLoc = location;
        this.latLng = new LatLng(location.getLatitude(), location.getLongitude());
        this.map.moveCamera(CameraUpdateFactory.newLatLng(this.latLng));
        this.map.animateCamera(CameraUpdateFactory.zoomBy(10.0f));
    }

    public void onConnected(@Nullable Bundle bundle) {
        getCurrentLocation();
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public void onCameraIdle() {
    }

    private void parseDistance(Response response) {
        if (response != null) {
            try {
                JSONObject jsonObject = new JSONObject(response.body().toString());
                if (!this.isApicallFirstTime) {
                    this.binding.mProg.setVisibility(8);
                }
                JSONObject jsonObj = jsonObject.optJSONObject("data");
                if (jsonObj != null) {
                    this.driver_lat = Double.parseDouble(jsonObj.optString("driver_lat"));
                    this.driver_long = Double.parseDouble(jsonObj.optString("driver_long"));
                    this.customer_lat = Double.parseDouble(jsonObj.optString("customer_lat"));
                    this.customer_long = Double.parseDouble(jsonObj.optString("customer_long"));
                    int eta = jsonObj.optInt("eta") / 60;
                    this.binding.llEta.setVisibility(0);
                    this.binding.tvETA.setText(eta + "");
                    this.map.clear();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(getBitmap("driver")));
                    markerOptions.position(new LatLng(this.driver_lat, this.driver_long));
                    MarkerOptions markerOptions2 = new MarkerOptions();
                    markerOptions2.icon(BitmapDescriptorFactory.fromBitmap(getBitmap("customer")));
                    markerOptions2.position(new LatLng(this.customer_lat, this.customer_long));
                    this.map.addMarker(markerOptions);
                    this.map.addMarker(markerOptions2);
                    if (!this.isApicallFirstTime) {
                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        builder.include(markerOptions.getPosition());
                        builder.include(markerOptions2.getPosition());
                        this.map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 0));
                    }
                    this.isApicallFirstTime = true;
                    this.handler.postDelayed(this.runnable, (long) this.delay);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onResume() {
        init(this.binding.getRoot());
        this.handler.postDelayed(this.runnable, (long) this.delay);
        super.onResume();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == 0) {
                    makeCall();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onPause() {
        super.onPause();
        Utils.printMsg("ETA :", "No route found");
        this.handler.removeCallbacks(this.runnable);
    }

    private void requestPhonePermission() {
        if (ContextCompat.checkSelfPermission(this.me, "android.permission.CALL_PHONE") != 0) {
            ActivityCompat.requestPermissions(this.me, new String[]{"android.permission.CALL_PHONE"}, 100);
            return;
        }
        makeCall();
    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.me, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(this.me, new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        }
    }

    private void makeCall() {
        Intent callIntent = new Intent("android.intent.action.DIAL");
        callIntent.setData(Uri.parse("tel:" + Utils.getCallCenter(this.me)));
        startActivity(callIntent);
    }

    private Bitmap getBitmap(String type) {
        Drawable drawable;
        if (type.equalsIgnoreCase("customer")) {
            drawable = ContextCompat.getDrawable(this.me, R.drawable.client_pin);
        } else {
            drawable = ContextCompat.getDrawable(this.me, R.drawable.driver_pin);
        }
        int height = (int) convertDpToPixel(40.0f, this.me);
        int width = (int) convertDpToPixel(28.0f, this.me);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }
}
