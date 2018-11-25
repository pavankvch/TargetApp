package kuwait.com.targetlogistics.history;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.gms.plus.PlusShare;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityHistoryDetailBinding;
import kuwait.com.targetlogistics.databinding.RawParcelInfoBinding;
import kuwait.com.targetlogistics.home.TrackDeliveryActivity;
import kuwait.com.targetlogistics.interfaces.OnConfirmationDialog;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.OrderData;
import kuwait.com.targetlogistics.model.Parcel;
import kuwait.com.targetlogistics.model.ShipmentData;
import kuwait.com.targetlogistics.model.Value;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryDetailActivity extends BaseActivity {
    ParcelInfoAdapter adapter;
    ActivityHistoryDetailBinding binding;
    private String from;
    private String functionName;
    private boolean isPending = false;
    private ArrayList<Parcel> list = new ArrayList();
    private OrderData orderData;
    private String orderId;
    private Value value;

    class ParcelInfoAdapter extends Adapter<ViewHolder> {

        public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
            RawParcelInfoBinding binding;

            public ViewHolder(RawParcelInfoBinding itemView) {
                super(itemView.getRoot());
                this.binding = itemView;
                if (HistoryDetailActivity.this.from.equals("Export")) {
                    this.binding.textDescriptionLabel.setVisibility(8);
                    this.binding.txtParcelDescription.setVisibility(8);
                    this.binding.viewDescription.setVisibility(8);
                }
            }
        }

        ParcelInfoAdapter() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder((RawParcelInfoBinding) DataBindingUtil.inflate(LayoutInflater.from(HistoryDetailActivity.this.me), R.layout.raw_parcel_info, parent, false));
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            Parcel parcel = (Parcel) HistoryDetailActivity.this.list.get(position);
            parcel.setParcelType(Utils.getParcelType(parcel.getParcelType(), HistoryDetailActivity.this.me));
            holder.binding.setData(parcel);
            holder.binding.executePendingBindings();
        }

        public int getItemCount() {
            return HistoryDetailActivity.this.list.size();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityHistoryDetailBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        if (getIntent().hasExtra("data")) {
            this.value = (Value) getIntent().getSerializableExtra("data");
        }
        if (getIntent().hasExtra("isPending")) {
            this.isPending = getIntent().getBooleanExtra("isPending", false);
        }
        if (getRedirectClass().equals(TrackDeliveryActivity.class)) {
            if (Utils.getOrderType(this.me).equals("1")) {
                this.from = "Export";
                this.functionName = BaseUrl.GET_EXPORT_HISTORY_DETAIL;
                setTitle(getString(R.string.export_history));
            } else {
                this.from = "Delivery";
                this.functionName = BaseUrl.GET_DELIVERY_HISTORY_DETAIL;
                setTitle(getString(R.string.delivery_history));
            }
            this.orderId = Utils.getOrderId(this.me);
        } else {
            this.orderId = this.value.getOrderId();
        }
        if (getIntent().hasExtra("HistoryType")) {
            this.from = getIntent().getStringExtra("HistoryType");
            String str = this.from;
            boolean z = true;
            switch (str.hashCode()) {
                case -2100928571:
                    if (str.equals("Import")) {
                        z = true;
                        break;
                    }
                    break;
                case 888111124:
                    if (str.equals("Delivery")) {
                        z = true;
                        break;
                    }
                    break;
                case 2089680852:
                    if (str.equals("Export")) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    setTitle(getString(R.string.export_history));
                    this.functionName = BaseUrl.GET_EXPORT_HISTORY_DETAIL;
                    break;
                case true:
                    setTitle(getString(R.string.import_history));
                    this.functionName = BaseUrl.GET_IMPORT_HISTORY_DETAIL;
                    this.binding.linDelivery.setVisibility(8);
                    this.binding.txtDateTime.setVisibility(8);
                    this.binding.txtCancelOrder.setVisibility(8);
                    break;
                case true:
                    setTitle(getString(R.string.delivery_history));
                    this.functionName = BaseUrl.GET_DELIVERY_HISTORY_DETAIL;
                    this.binding.linVehicleInfo.setVisibility(0);
                    break;
            }
        }
        callHistoryDetailAPI();
        this.binding.recParcelInfo.setLayoutManager(new LinearLayoutManager(this.me));
        this.binding.recParcelInfo.setNestedScrollingEnabled(false);
        this.binding.txtDeliveryInProgress.setOnClickListener(this);
        this.binding.txtCancelOrder.setOnClickListener(this);
        this.binding.imgCopyToClipboard.setOnClickListener(this);
    }

    private void setData() {
        if (this.from.equalsIgnoreCase("Delivery")) {
            this.binding.txtCountry.setVisibility(8);
            this.binding.txtCountryLabel.setVisibility(8);
            this.binding.txtCityLabel.setText(getResources().getString(R.string.area));
            this.orderData.setDeliveryCity(Utils.getAreaName(this.orderData.getDeliveryCity(), this.me));
            this.orderData.setPickupAddress(this.orderData.getDeliveryAddress());
            this.orderData.setDeliveryAddress1(this.orderData.getDeliveryaddressformat());
        } else {
            this.orderData.setDeliveryCity(Utils.getCityName(this.orderData.getDeliveryCity(), this.me));
            this.orderData.setDeliveryCountry(Utils.getCountryName(this.orderData.getDeliveryCountry(), this.me));
        }
        if (!this.from.equalsIgnoreCase("Import")) {
            if (this.orderData.getOrderStatus().equals("0")) {
                this.binding.txtCancelOrder.setVisibility(0);
            } else {
                this.binding.txtCancelOrder.setVisibility(8);
            }
        }
        if (this.from.equalsIgnoreCase("Import")) {
            if (MyApplication.isLTR) {
                this.orderData.setAddress(getResources().getString(R.string.full_Name) + this.orderData.getPickupFullName() + ", " + getResources().getString(R.string.country) + ":" + this.orderData.getCountryName() + ", " + getResources().getString(R.string.city) + ":" + this.orderData.getCityName() + ", " + getResources().getString(R.string.address1) + ":" + this.orderData.getPickupAddress1() + ", " + getResources().getString(R.string.address2) + ":" + this.orderData.getPickupAddress2());
            } else {
                this.orderData.setAddressAr(getResources().getString(R.string.full_Name) + this.orderData.getPickupFullName() + ", " + getResources().getString(R.string.country) + ":" + this.orderData.getCountryName() + ", " + getResources().getString(R.string.city) + ":" + this.orderData.getCityName() + ", " + getResources().getString(R.string.address1) + ":" + this.orderData.getPickupAddress1() + ", " + getResources().getString(R.string.address2) + ":" + this.orderData.getPickupAddress2());
            }
            this.binding.linDate.setVisibility(8);
        }
        if (this.from.equalsIgnoreCase("Export")) {
            this.orderData.setDeliveryAddress1(this.orderData.getDeliveryAddress1() + ", " + this.orderData.getDeliveryAddress2());
        }
        if (this.orderData.getTotalWeight().equalsIgnoreCase("0.000")) {
            this.binding.linWeight.setVisibility(8);
        }
        if (this.orderData.getShippingCost().equalsIgnoreCase("0.000")) {
            this.binding.linTotalCost.setVisibility(8);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_copy_to_clipboard:
                copyToClipBoard();
                return;
            case R.id.txt_cancel_order:
                showConfirmationDialog(getString(R.string.are_you_sure_want_to_cancel_order), new OnConfirmationDialog() {
                    public void onYes() {
                        HistoryDetailActivity.this.dismissDialog();
                        HistoryDetailActivity.this.callCancelOrderAPI();
                    }

                    public void onNo() {
                        HistoryDetailActivity.this.dismissDialog();
                    }
                });
                return;
            case R.id.txt_delivery_in_progress:
                if (this.isPending) {
                    startActivity(new Intent(this, TrackDeliveryActivity.class));
                    finish();
                    return;
                }
                startActivity(new Intent(this, TrackShipmentActivity.class).putExtra(PlusShare.KEY_CALL_TO_ACTION_URL, this.orderData.getShipmentData().getShipmentTracking() != null ? this.orderData.getShipmentData().getShipmentTracking() : ""));
                return;
            default:
                super.onClick(view);
                return;
        }
    }

    public void callHistoryDetailAPI() {
        this.me.showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", this.functionName);
        requestParams.put("order_id", this.orderId);
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallGet(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                HistoryDetailActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        JsonObject jsonObject = ((JsonObject) response.body()).getAsJsonObject("data");
                        Gson gson = new Gson();
                        HistoryDetailActivity.this.orderData = (OrderData) gson.fromJson(jsonObject.get("order_data"), new TypeToken<OrderData>() {
                        }.getType());
                        if (HistoryDetailActivity.this.orderData != null) {
                            HistoryDetailActivity.this.setData();
                            if (HistoryDetailActivity.this.orderData.getPickupDatetime() != null) {
                                HistoryDetailActivity.this.binding.txtDateTime.setText(HistoryDetailActivity.this.orderData.getPickupDatetime());
                            }
                            if (HistoryDetailActivity.this.orderData.getOrderDatetime() != null) {
                                HistoryDetailActivity.this.binding.txtOrderDateTime.setText(Utils.ServerformatDate(HistoryDetailActivity.this.orderData.getOrderDatetime()));
                            }
                        }
                        HistoryDetailActivity.this.list = (ArrayList) gson.fromJson(jsonObject.get("parcel_data"), new TypeToken<ArrayList<Parcel>>() {
                        }.getType());
                        if (HistoryDetailActivity.this.list == null || HistoryDetailActivity.this.list.size() <= 0) {
                            HistoryDetailActivity.this.binding.txtParcelInfo.setVisibility(8);
                        } else {
                            HistoryDetailActivity.this.setAdapter();
                        }
                        Type listType = new TypeToken<ShipmentData>() {
                        }.getType();
                        if (HistoryDetailActivity.this.isPending && HistoryDetailActivity.this.orderData.getOrderStatus().equals("4")) {
                            HistoryDetailActivity.this.binding.txtDeliveryInProgress.setVisibility(0);
                        } else {
                            HistoryDetailActivity.this.binding.txtDeliveryInProgress.setVisibility(8);
                        }
                        ShipmentData shipmentData = (ShipmentData) gson.fromJson(jsonObject.get("shipment_data"), listType);
                        if (shipmentData == null || shipmentData.getShipmentCompany() == null) {
                            HistoryDetailActivity.this.binding.linShipmentInfo.setVisibility(8);
                        } else {
                            HistoryDetailActivity.this.binding.linShipmentInfo.setVisibility(0);
                            HistoryDetailActivity.this.orderData.setShipmentData(shipmentData);
                            if (!(HistoryDetailActivity.this.isPending || shipmentData.getShipmentTracking().isEmpty())) {
                                HistoryDetailActivity.this.binding.txtDeliveryInProgress.setVisibility(0);
                                HistoryDetailActivity.this.binding.txtDeliveryInProgress.setText(HistoryDetailActivity.this.getResources().getString(R.string.track_shipment));
                            }
                        }
                        HistoryDetailActivity.this.binding.setData(HistoryDetailActivity.this.orderData);
                        HistoryDetailActivity.this.binding.executePendingBindings();
                        return;
                    }
                    return;
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                HistoryDetailActivity.this.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }

    public void callCancelOrderAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.CANCEL_ORDER);
        requestParams.put("order_status", "11");
        requestParams.put("order_id", this.value.getOrderId());
        requestParams.put("user_id", Utils.getUserId(this.me));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                HistoryDetailActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        HistoryDetailActivity.this.showSimpleDialog(HistoryDetailActivity.this.getString(R.string.order_deleted), new OnDialog() {
                            public void onOk() {
                                HistoryDetailActivity.this.dismissDialog();
                                HistoryDetailActivity.this.finish();
                            }
                        });
                        return;
                    }
                    return;
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                HistoryDetailActivity.this.me.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }

    private void setAdapter() {
        this.adapter = new ParcelInfoAdapter();
        this.binding.recParcelInfo.setAdapter(this.adapter);
    }

    private void copyToClipBoard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService("clipboard");
        ClipData clip = ClipData.newPlainText("TrackingNumber", this.binding.txtShipmentId.getText().toString());
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
        }
        Toast.makeText(this.me, getString(R.string.copied), 0).show();
    }
}
