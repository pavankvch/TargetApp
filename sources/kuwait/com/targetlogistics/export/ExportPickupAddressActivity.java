package kuwait.com.targetlogistics.export;

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
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.Address;
import kuwait.com.targetlogistics.model.Export;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExportPickupAddressActivity extends BaseActivity {
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
        this.addressArrayList.addAll(Utils.getAddressList(this.me));
        setTitle(getString(R.string.export));
        if (getIntent().hasExtra("HistoryType")) {
            this.isFromHistory = true;
            Utils.setEnable(this.binding.linMain, false);
            this.binding.txtAddNewAddress.setVisibility(8);
        } else {
            Utils.setEnable(this.binding.linMain, true);
        }
        this.binding.layoutSteps.txtPickUpAddress.setBackground(ContextCompat.getDrawable(this.me, R.drawable.ic_round_shape_red));
        this.binding.layoutSteps.txtStep3.setText(getString(R.string.shipping_to));
        LayoutManager manager = new LinearLayoutManager(this.me, 1, false);
        this.binding.recViewAddressList.setHasFixedSize(true);
        this.binding.recViewAddressList.setNestedScrollingEnabled(false);
        this.binding.recViewAddressList.setLayoutManager(manager);
        this.binding.recViewAddressList.setItemAnimator(null);
        this.binding.recViewAddressList.setNestedScrollingEnabled(false);
        this.binding.linSaveAddress.setOnClickListener(this);
        this.binding.txtAddNewAddress.setOnClickListener(this);
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
                } else if (AddressListAdapter.export.getAddressId() != null) {
                    startActivity(new Intent(this.me, ExportPickUpDateTimeActivity.class));
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

    private void setData() {
        if (this.addressArrayList.size() > 0) {
            this.binding.txtAddNewAddress.setVisibility(0);
            this.binding.recViewAddressList.setVisibility(0);
            this.binding.linNoAddress.setVisibility(8);
            this.binding.btnAddAddress.setText(getString(R.string.next));
            this.binding.imgSkipArrow.setVisibility(0);
            this.listAdapter = new AddressListAdapter(this.me, this.addressArrayList, this.isFromHistory, getIntent().getStringExtra("HistoryType"));
            this.binding.recViewAddressList.setAdapter(this.listAdapter);
            setRedirectClass(ExportPickupAddressActivity.class);
            return;
        }
        this.binding.recViewAddressList.setVisibility(8);
        this.binding.txtAddNewAddress.setVisibility(8);
        this.binding.linNoAddress.setVisibility(0);
        this.binding.btnAddAddress.setText(getString(R.string.add_address));
        this.binding.imgSkipArrow.setVisibility(8);
    }

    protected void onDestroy() {
        super.onDestroy();
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
                ExportPickupAddressActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<ArrayList<Address>>() {
                        }.getType();
                        if (((JsonObject) response.body()).get("data") instanceof JsonObject) {
                            ExportPickupAddressActivity.this.binding.recViewAddressList.setVisibility(8);
                            ExportPickupAddressActivity.this.binding.txtAddNewAddress.setVisibility(8);
                            ExportPickupAddressActivity.this.binding.linNoAddress.setVisibility(0);
                            ExportPickupAddressActivity.this.binding.btnAddAddress.setText(ExportPickupAddressActivity.this.getString(R.string.add_address));
                            ExportPickupAddressActivity.this.binding.imgSkipArrow.setVisibility(8);
                            return;
                        }
                        ExportPickupAddressActivity.this.addressArrayList = (ArrayList) gson.fromJson(((JsonObject) response.body()).get("data"), listType);
                        if (ExportPickupAddressActivity.this.addressArrayList != null) {
                            AddressListAdapter.export = new Export();
                            Utils.setAddressList(ExportPickupAddressActivity.this.me, ExportPickupAddressActivity.this.addressArrayList);
                            ExportPickupAddressActivity.this.setData();
                            return;
                        }
                        ExportPickupAddressActivity.this.binding.recViewAddressList.setVisibility(8);
                        ExportPickupAddressActivity.this.binding.txtAddNewAddress.setVisibility(8);
                        ExportPickupAddressActivity.this.binding.linNoAddress.setVisibility(0);
                        ExportPickupAddressActivity.this.binding.btnAddAddress.setText(ExportPickupAddressActivity.this.getString(R.string.add_address));
                        ExportPickupAddressActivity.this.binding.imgSkipArrow.setVisibility(8);
                        return;
                    }
                    ExportPickupAddressActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                        public void onOk() {
                            ExportPickupAddressActivity.this.dismissDialog();
                        }
                    });
                    return;
                }
                APIError error = ErrorUtils.parseError(response);
                Utils.printMsg("error message", error.message());
                ExportPickupAddressActivity.this.showSimpleDialog(error.message(), new OnDialog() {
                    public void onOk() {
                        ExportPickupAddressActivity.this.dismissDialog();
                    }
                });
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                ExportPickupAddressActivity.this.showSimpleDialog(t.getMessage(), new OnDialog() {
                    public void onOk() {
                        ExportPickupAddressActivity.this.dismissDialog();
                    }
                });
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
