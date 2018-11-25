package kuwait.com.targetlogistics.delivery_logistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.APIError;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityDeliveryVehicleInfoBinding;
import kuwait.com.targetlogistics.history.HistoryActivity;
import kuwait.com.targetlogistics.home.HomeActivity;
import kuwait.com.targetlogistics.interfaces.OnConfirmationDialog;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.VehicleType;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryVehicleInfoActivity extends BaseActivity {
    ActivityDeliveryVehicleInfoBinding binding;
    private boolean isFromHistory = false;
    Context mContext;
    private VehicleTypeAdapter vehicleTypeAdapter;
    private ArrayList<VehicleType> vehicleTypeList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityDeliveryVehicleInfoBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.delivery));
        callGetVehicleListAPI();
        this.binding.layoutSteps.txtParcelInfoLabel.setBackground(ContextCompat.getDrawable(this.me, R.drawable.ic_round_shape_red));
        this.binding.layoutSteps.txtParcelInfo.setText(getString(R.string.vehicle_info));
        this.mContext = this;
        this.vehicleTypeList = new ArrayList();
        this.binding.linBack.setOnClickListener(this);
        this.binding.linSkip.setOnClickListener(this);
        LayoutManager manager = new LinearLayoutManager(this.me, 1, false);
        this.binding.recVehicleList.setHasFixedSize(true);
        this.binding.recVehicleList.setNestedScrollingEnabled(false);
        this.binding.recVehicleList.setLayoutManager(manager);
        this.binding.recVehicleList.setItemAnimator(null);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.lin_back:
                super.onBackPressed();
                return;
            case R.id.lin_skip:
                if (this.isFromHistory) {
                    startActivity(new Intent(this.me, HistoryActivity.class).setFlags(67108864));
                    onFinishAll(new Class[]{DeliveryPickupAddressActivity.class, DeliveryAddressActivity.class, DeliveryDateTimeActivity.class});
                    finish();
                    return;
                } else if (delivery.getVehicleId() != null) {
                    callDeliveryOrderAPI();
                    return;
                } else {
                    showSnackBar(this.me, getString(R.string.please_select_vehicle));
                    return;
                }
            default:
                return;
        }
    }

    private void callGetVehicleListAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.VEHICLE_LIST);
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallGet(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                DeliveryVehicleInfoActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        DeliveryVehicleInfoActivity.this.vehicleTypeList.clear();
                        DeliveryVehicleInfoActivity.this.vehicleTypeList = (ArrayList) new Gson().fromJson(((JsonObject) response.body()).getAsJsonArray("data"), new TypeToken<ArrayList<VehicleType>>() {
                        }.getType());
                        if (DeliveryVehicleInfoActivity.this.vehicleTypeList != null && DeliveryVehicleInfoActivity.this.vehicleTypeList.size() > 0) {
                            DeliveryVehicleInfoActivity.this.vehicleTypeAdapter = new VehicleTypeAdapter(DeliveryVehicleInfoActivity.this.me, DeliveryVehicleInfoActivity.this.vehicleTypeList, DeliveryVehicleInfoActivity.this.isFromHistory);
                            DeliveryVehicleInfoActivity.this.binding.recVehicleList.setAdapter(DeliveryVehicleInfoActivity.this.vehicleTypeAdapter);
                            return;
                        }
                        return;
                    }
                    DeliveryVehicleInfoActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                        public void onOk() {
                            DeliveryVehicleInfoActivity.this.dismissDialog();
                        }
                    });
                    return;
                }
                APIError error = ErrorUtils.parseError(response);
                Utils.printMsg("error message", error.message());
                DeliveryVehicleInfoActivity.this.showSimpleDialog(error.message(), new OnDialog() {
                    public void onOk() {
                        DeliveryVehicleInfoActivity.this.dismissDialog();
                    }
                });
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                DeliveryVehicleInfoActivity.this.showSimpleDialog(t.getMessage(), new OnDialog() {
                    public void onOk() {
                        DeliveryVehicleInfoActivity.this.dismissDialog();
                    }
                });
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }

    private void callDeliveryOrderAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.DELIVERY_ORDER);
        requestParams.put("user_id", Utils.getUserInfo(this.me).getId());
        requestParams.put("delivery_area", delivery.getAreaId() == null ? "" : delivery.getAreaId());
        requestParams.put("delivery_addressname", delivery.getFullName() == null ? "" : delivery.getFullName());
        requestParams.put("delivery_phone", delivery.getPhoneNo() == null ? "" : delivery.getPhoneNo());
        requestParams.put("delivery_block", delivery.getBlock() == null ? "" : delivery.getBlock());
        requestParams.put("delivery_street", delivery.getStreet() == null ? "" : delivery.getStreet());
        requestParams.put("delivery_houseno", delivery.getHouse() == null ? "" : delivery.getHouse());
        requestParams.put("delivery_apartment", delivery.getApartment() == null ? "" : delivery.getApartment());
        requestParams.put("delivery_floor", delivery.getFloor() == null ? "" : delivery.getFloor());
        requestParams.put("delivery_jadda", delivery.getJadda() == null ? "" : delivery.getJadda());
        requestParams.put("delivery_instruction", delivery.getPickupInstruction() == null ? "" : delivery.getPickupInstruction());
        requestParams.put("deliveryorder_vehicle", delivery.getVehicleId() == null ? "" : delivery.getVehicleId());
        requestParams.put("pickup_datetime", delivery.getPickupDateTIme() == null ? "" : delivery.getPickupDateTIme());
        requestParams.put("address_id", delivery.getAddressId() == null ? "" : delivery.getAddressId());
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                DeliveryVehicleInfoActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        DeliveryVehicleInfoActivity.this.showConfirmationDialog(DeliveryVehicleInfoActivity.this.getString(R.string.do_you_want_to_continue), new OnConfirmationDialog() {
                            public void onYes() {
                                EventBus.getDefault().post(new Intent().putExtra("Reorder", true));
                                DeliveryVehicleInfoActivity.this.finish();
                            }

                            public void onNo() {
                                DeliveryVehicleInfoActivity.this.startActivity(new Intent(DeliveryVehicleInfoActivity.this.me, HomeActivity.class));
                                DeliveryVehicleInfoActivity.this.finishAffinity();
                            }
                        });
                        return;
                    } else {
                        DeliveryVehicleInfoActivity.this.showSimpleDialog(DeliveryVehicleInfoActivity.this.getString(R.string.error_message), new OnDialog() {
                            public void onOk() {
                                DeliveryVehicleInfoActivity.this.dismissDialog();
                            }
                        });
                        return;
                    }
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                DeliveryVehicleInfoActivity.this.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
