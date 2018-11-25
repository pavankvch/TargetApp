package kuwait.com.targetlogistics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap$OnCameraIdleListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;
import java.util.HashMap;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityMapBinding;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.Area;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap$OnCameraIdleListener {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;
    private Area area;
    ActivityMapBinding binding;
    private boolean isPermissionGranted = false;
    private LatLng latLng;
    private GoogleMap map;
    private Drawable markerDrawable;
    private MarkerOptions markerOptions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMapBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.pin_location));
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        this.binding.btnConfirmAddress.setOnClickListener(this);
        if (getIntent().hasExtra("Area")) {
            this.area = (Area) getIntent().getSerializableExtra("Area");
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_confirm_address:
                ApiCallForGetAddress();
                return;
            default:
                return;
        }
    }

    private void ApiCallForGetAddress() {
        this.me.showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.GET_ADDRESS_LAT_LNG);
        requestParams.put("latitude", this.latLng.latitude + "");
        requestParams.put("longitude", this.latLng.longitude + "");
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        requestParams.put("area_name", this.area.getAreaName());
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallGet(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                MapActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        MapActivity.this.parseAddressData(((JsonObject) response.body()).toString());
                        return;
                    }
                    return;
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                MapActivity.this.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }

    private void parseAddressData(String response) {
        try {
            JSONObject obj = new JSONObject(response).getJSONObject("data");
            String country = obj.optString("country");
            String area1 = obj.optString("area");
            String street = obj.optString("street");
            String block = obj.optString("block");
            if (!country.equals("1")) {
                showSimpleDialog(getString(R.string.country_mismatch), new OnDialog() {
                    public void onOk() {
                        MapActivity.this.latLng = new LatLng(MapActivity.this.area.getAreaLatitude().doubleValue(), MapActivity.this.area.getAreaLongitude().doubleValue());
                        MapActivity.this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(MapActivity.this.latLng, 15.0f));
                    }
                });
            } else if (area1.equals("1")) {
                Intent intent = getIntent();
                intent.putExtra("strLat", this.latLng.latitude);
                intent.putExtra("strLong", this.latLng.longitude);
                intent.putExtra("street", street);
                intent.putExtra("block", block);
                intent.putExtra("key", "");
                setResult(-1, intent);
                finish();
            } else {
                showSimpleDialog(getString(R.string.area_mismatch), new OnDialog() {
                    public void onOk() {
                        MapActivity.this.latLng = new LatLng(MapActivity.this.area.getAreaLatitude().doubleValue(), MapActivity.this.area.getAreaLongitude().doubleValue());
                        MapActivity.this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(MapActivity.this.latLng, 15.0f));
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        if (ContextCompat.checkSelfPermission(this.me, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            ActivityCompat.requestPermissions(this.me, new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, 100);
        } else {
            googleMap.setMyLocationEnabled(false);
        }
        this.map.setOnCameraIdleListener(this);
        this.latLng = new LatLng(this.area.getAreaLatitude().doubleValue(), this.area.getAreaLongitude().doubleValue());
        this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(this.latLng, 15.0f));
    }

    private Bitmap getBitmap() {
        Drawable drawable = ContextCompat.getDrawable(this.me, R.drawable.ic_marker);
        int height = (int) Utils.convertDpToPixel(32.0f, getApplicationContext());
        int width = (int) Utils.convertDpToPixel(24.0f, getApplicationContext());
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length <= 0 || grantResults[0] != 0) {
                    this.isPermissionGranted = false;
                    return;
                } else {
                    this.isPermissionGranted = true;
                    return;
                }
            default:
                return;
        }
    }

    public void onCameraIdle() {
        this.latLng = this.map.getCameraPosition().target;
    }
}
