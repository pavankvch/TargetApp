package kuwait.com.targetlogistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.JsonObject;
import java.util.HashMap;
import kuwait.com.targetlogistics.api.APIError;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityNewAddressBinding;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.Address;
import kuwait.com.targetlogistics.model.Area;
import kuwait.com.targetlogistics.searchable.SearchActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAddressActivity extends BaseActivity {
    private static int mapResult = 102;
    private static int searchResult = 101;
    Address addressData;
    private String areaId;
    ActivityNewAddressBinding binding;
    Area data1;
    double latitude;
    double longitude;
    double selectedLat;
    double selectedLon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityNewAddressBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.new_address));
        this.addressData = (Address) getIntent().getSerializableExtra("Address");
        if (this.addressData != null) {
            setAreaName();
            setTitle(getString(R.string.edit_address));
            this.binding.setData(this.addressData);
            if (this.addressData.getPickupLatitude() != null && !this.addressData.getPickupLatitude().isEmpty()) {
                this.selectedLat = Double.parseDouble(this.addressData.getPickupLatitude());
            } else if (!(this.addressData.getPickupLongitude() == null || this.addressData.getPickupLongitude().isEmpty())) {
                this.selectedLon = Double.parseDouble(this.addressData.getPickupLongitude());
            }
            this.binding.executePendingBindings();
        }
        if (getIntent().hasExtra("from") && getIntent().getStringExtra("from").equalsIgnoreCase("root")) {
            Utils.setEnable(this.binding.relArea, false);
            Utils.setEnable(this.binding.linMain, false);
        }
        this.binding.relArea.setOnClickListener(this);
        this.binding.linSaveAddress.setOnClickListener(this);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.lin_save_address:
                if (validations()) {
                    callSaveAddressAPI();
                    Utils.hideSoftKeyboard(this.me);
                    return;
                }
                return;
            case R.id.rel_area:
                Intent i = new Intent(this.me, SearchActivity.class);
                this.selectedLat = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                this.selectedLon = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                startActivityForResult(i, searchResult);
                setRedirectClass(NewAddressActivity.class);
                return;
            default:
                return;
        }
    }

    private boolean validations() {
        if (this.binding.textArea.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_select_area));
            return false;
        } else if (this.selectedLat <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && this.selectedLon <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            showSnackBar(this.me, getString(R.string.please_select_proper_area));
            return false;
        } else if (this.binding.edtAddressName.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_address_name));
            return false;
        } else if (this.binding.edtBlock.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_block));
            return false;
        } else if (!this.binding.edtStreetNumber.getText().toString().isEmpty()) {
            return true;
        } else {
            showSnackBar(this.me, getString(R.string.please_enter_street_no));
            return false;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == -1) {
            try {
                if (requestCode == searchResult) {
                    this.data1 = (Area) data.getSerializableExtra("Area");
                    if (!this.binding.textArea.getText().toString().equals(getString(R.string.area))) {
                        Utils.setEnable(this.binding.linMain, true);
                        for (int i = 0; i < Utils.getAreaList(this.me).size(); i++) {
                            Area area = (Area) Utils.getAreaList(this.me).get(i);
                            if (area.getAreaName().equalsIgnoreCase(this.data1.getAreaName())) {
                                this.areaId = area.getAreaId();
                                Utils.printMsg("AreaId", this.areaId);
                            }
                        }
                        Intent intent = new Intent(this, MapActivity.class);
                        intent.putExtra("Area", this.data1);
                        startActivityForResult(intent, mapResult);
                    }
                } else if (requestCode == mapResult) {
                    this.selectedLat = data.getDoubleExtra("strLat", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    this.selectedLon = data.getDoubleExtra("strLong", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    String street = data.getStringExtra("street");
                    String block = data.getStringExtra("block");
                    if (block == null || !block.matches("[0-9]+")) {
                        this.binding.edtBlock.setEnabled(true);
                    } else {
                        this.binding.edtBlock.setText(block);
                        this.binding.edtBlock.setEnabled(false);
                    }
                    this.binding.edtStreetNumber.setText(street);
                    if (this.data1 != null) {
                        this.binding.textArea.setText(this.data1.getAreaName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void callSaveAddressAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        if (this.addressData != null) {
            requestParams.put("function", BaseUrl.EDIT_ADDRESS);
            requestParams.put("address_id", this.addressData.getPickupAddressId());
        } else {
            requestParams.put("function", BaseUrl.SAVE_ADDRESS);
        }
        requestParams.put("user_id", Utils.getUserId(this.me));
        requestParams.put("pickup_area", this.areaId);
        requestParams.put("pickup_addressname", this.binding.edtAddressName.getText().toString().trim());
        requestParams.put("pickup_phone", this.binding.edtPhone.getText().toString().trim());
        requestParams.put("pickup_block", this.binding.edtBlock.getText().toString().trim());
        requestParams.put("pickup_street", this.binding.edtStreetNumber.getText().toString().trim());
        requestParams.put("pickup_houseno", this.binding.edtHouse.getText().toString().trim());
        requestParams.put("pickup_apartment", this.binding.edtApartment.getText().toString().trim());
        requestParams.put("pickup_floor", this.binding.edtFloor.getText().toString().trim());
        requestParams.put("pickup_jadda", this.binding.edtJadda.getText().toString().trim());
        requestParams.put("pickup_instruction", this.binding.edtPickupInstructions.getText().toString().trim());
        requestParams.put("pickup_latitude", this.selectedLat + "");
        requestParams.put("pickup_longitude", this.selectedLon + "");
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                NewAddressActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        NewAddressActivity.this.finish();
                        return;
                    } else {
                        NewAddressActivity.this.showSimpleDialog(((JsonObject) response.body()).get(NotificationCompat.CATEGORY_MESSAGE).getAsString(), new OnDialog() {
                            public void onOk() {
                                NewAddressActivity.this.dismissDialog();
                            }
                        });
                        return;
                    }
                }
                APIError error = ErrorUtils.parseError(response);
                Utils.printMsg("error message", error.message());
                NewAddressActivity.this.showSimpleDialog(error.message(), new OnDialog() {
                    public void onOk() {
                        NewAddressActivity.this.dismissDialog();
                    }
                });
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                NewAddressActivity.this.showSimpleDialog(t.getMessage(), new OnDialog() {
                    public void onOk() {
                        NewAddressActivity.this.dismissDialog();
                    }
                });
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }

    private void setAreaName() {
        for (int i = 0; i < Utils.getAreaList(this.me).size(); i++) {
            Area area = (Area) Utils.getAreaList(this.me).get(i);
            if (area.getAreaId().equalsIgnoreCase(this.addressData.getPickupArea())) {
                this.areaId = area.getAreaId();
                this.binding.textArea.setText(area.getAreaName());
                Utils.printMsg("AreaId", this.areaId);
            }
        }
    }
}
