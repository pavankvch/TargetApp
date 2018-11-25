package kuwait.com.targetlogistics.delivery_logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.NewAddressActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.APIError;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityExportPickupAddressBinding;
import kuwait.com.targetlogistics.export.AddressListAdapter;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.Address;
import kuwait.com.targetlogistics.model.Delivery;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryPickupAddressActivity extends BaseActivity {
    private ArrayList<Address> addressArrayList;
    ActivityExportPickupAddressBinding binding;
    private boolean isFromHistory = false;
    private AddressListAdapter listAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityExportPickupAddressBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        this.addressArrayList = new ArrayList();
        delivery = new Delivery();
        this.addressArrayList.addAll(Utils.getAddressList(this.me));
        setTitle(getString(R.string.delivery));
        this.binding.layoutSteps.txtPickUpAddress.setBackground(ContextCompat.getDrawable(this.me, R.drawable.ic_round_shape_red));
        this.binding.layoutSteps.txtParcelInfo.setText(getString(R.string.vehicle_info));
        LayoutManager manager = new LinearLayoutManager(this.me, 1, false);
        this.binding.recViewAddressList.setHasFixedSize(true);
        this.binding.recViewAddressList.setLayoutManager(manager);
        this.binding.recViewAddressList.setItemAnimator(null);
        this.binding.recViewAddressList.setNestedScrollingEnabled(false);
        this.listAdapter = new AddressListAdapter(this.me, this.addressArrayList, this.isFromHistory, getIntent().getStringExtra("HistoryType"));
        this.binding.recViewAddressList.setAdapter(this.listAdapter);
        setRedirectClass(DeliveryPickupAddressActivity.class);
        this.binding.txtAddNewAddress.setOnClickListener(this);
        this.binding.linSaveAddress.setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
        callGetAddressAPI();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_save_address:
                if (this.addressArrayList.size() <= 0) {
                    startActivity(new Intent(this.me, NewAddressActivity.class));
                    return;
                } else if (delivery.getAddressId() != null) {
                    startActivity(new Intent(this.me, DeliveryDateTimeActivity.class));
                    return;
                } else {
                    showSnackBar(this.me, getString(R.string.please_select_address));
                    return;
                }
            case R.id.txt_add_new_address:
                startActivity(new Intent(this.me, NewAddressActivity.class));
                return;
            default:
                super.onClick(view);
                return;
        }
    }

    private void callGetAddressAPI() {
        showOnlyProgressDialog();
        this.addressArrayList.clear();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.GET_ADDRESS);
        requestParams.put("user_id", Utils.getUserId(this.me));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallGet(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                DeliveryPickupAddressActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<ArrayList<Address>>() {
                        }.getType();
                        if (((JsonObject) response.body()).get("data") instanceof JsonObject) {
                            DeliveryPickupAddressActivity.this.binding.recViewAddressList.setVisibility(8);
                            DeliveryPickupAddressActivity.this.binding.txtAddNewAddress.setVisibility(8);
                            DeliveryPickupAddressActivity.this.binding.linNoAddress.setVisibility(0);
                            DeliveryPickupAddressActivity.this.binding.btnAddAddress.setText(DeliveryPickupAddressActivity.this.getString(R.string.add_address));
                            DeliveryPickupAddressActivity.this.binding.imgSkipArrow.setVisibility(8);
                            return;
                        }
                        DeliveryPickupAddressActivity.this.addressArrayList = (ArrayList) gson.fromJson(((JsonObject) response.body()).get("data"), listType);
                        if (DeliveryPickupAddressActivity.this.addressArrayList != null) {
                            BaseActivity.delivery = new Delivery();
                            Utils.setAddressList(DeliveryPickupAddressActivity.this.me, DeliveryPickupAddressActivity.this.addressArrayList);
                            DeliveryPickupAddressActivity.this.setData();
                            return;
                        }
                        DeliveryPickupAddressActivity.this.binding.recViewAddressList.setVisibility(8);
                        DeliveryPickupAddressActivity.this.binding.txtAddNewAddress.setVisibility(8);
                        DeliveryPickupAddressActivity.this.binding.linNoAddress.setVisibility(0);
                        DeliveryPickupAddressActivity.this.binding.btnAddAddress.setText(DeliveryPickupAddressActivity.this.getString(R.string.add_address));
                        DeliveryPickupAddressActivity.this.binding.imgSkipArrow.setVisibility(8);
                        return;
                    }
                    DeliveryPickupAddressActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                        public void onOk() {
                            DeliveryPickupAddressActivity.this.dismissDialog();
                        }
                    });
                    return;
                }
                APIError error = ErrorUtils.parseError(response);
                Utils.printMsg("error message", error.message());
                DeliveryPickupAddressActivity.this.showSimpleDialog(error.message(), new OnDialog() {
                    public void onOk() {
                        DeliveryPickupAddressActivity.this.dismissDialog();
                    }
                });
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                DeliveryPickupAddressActivity.this.showSimpleDialog(t.getMessage(), new OnDialog() {
                    public void onOk() {
                        DeliveryPickupAddressActivity.this.dismissDialog();
                    }
                });
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }

    private void setData() {
        if (this.addressArrayList.size() > 0) {
            this.binding.txtAddNewAddress.setVisibility(0);
            this.binding.recViewAddressList.setVisibility(0);
            this.binding.linNoAddress.setVisibility(8);
            this.binding.btnAddAddress.setText(getString(R.string.next));
            this.binding.imgSkipArrow.setVisibility(0);
            this.listAdapter = new AddressListAdapter(this.me, this.addressArrayList, this.isFromHistory, getIntent().getStringExtra("HistoryType"));
            this.binding.recViewAddressList.setAdapter(this.listAdapter);
            setRedirectClass(DeliveryPickupAddressActivity.class);
            return;
        }
        this.binding.recViewAddressList.setVisibility(8);
        this.binding.txtAddNewAddress.setVisibility(8);
        this.binding.linNoAddress.setVisibility(0);
        this.binding.btnAddAddress.setText(getString(R.string.add_address));
        this.binding.imgSkipArrow.setVisibility(8);
    }
}
