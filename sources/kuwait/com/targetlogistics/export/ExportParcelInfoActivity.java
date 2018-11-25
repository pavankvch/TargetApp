package kuwait.com.targetlogistics.export;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.OnItemSelected;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityExportParcelInfoBinding;
import kuwait.com.targetlogistics.databinding.LayoutExportStep3Binding;
import kuwait.com.targetlogistics.history.HistoryActivity;
import kuwait.com.targetlogistics.home.HomeActivity;
import kuwait.com.targetlogistics.interfaces.OnConfirmationDialog;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.Export;
import kuwait.com.targetlogistics.model.Parcel;
import kuwait.com.targetlogistics.model.ParcelType;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExportParcelInfoActivity extends BaseActivity {
    private ParcelInfoAdapter adapter;
    ActivityExportParcelInfoBinding binding;
    private Export export;
    private boolean isFromHistory = false;
    private JsonArray jsonParcelInfoArray;
    ArrayList<Parcel> list = new ArrayList();
    private ArrayList<ParcelType> parcelTypeArrayList = new ArrayList();

    class ParcelInfoAdapter extends Adapter<ViewHolder> {

        public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
            LayoutExportStep3Binding binding;

            public ViewHolder(LayoutExportStep3Binding itemView) {
                super(itemView.getRoot());
                this.binding = itemView;
            }
        }

        ParcelInfoAdapter() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder((LayoutExportStep3Binding) DataBindingUtil.inflate(LayoutInflater.from(ExportParcelInfoActivity.this.me), R.layout.layout_export_step_3, parent, false));
        }

        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Parcel parcel = (Parcel) ExportParcelInfoActivity.this.list.get(position);
            holder.binding.txtParcelType.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ExportParcelInfoActivity.this.showParcelTypeDialog(Utils.getParcelTypeList(ExportParcelInfoActivity.this.me), ExportParcelInfoActivity.this.me, ExportParcelInfoActivity.this.getString(R.string.select_parcel_type), new OnItemSelected() {
                        public void onItemSelected(Object o) {
                            if (o != null) {
                                parcel.setParcelType(o.toString());
                                parcel.setParcelTypeId(ExportParcelInfoActivity.this.getParcelTypeId(o.toString()));
                                holder.binding.txtParcelType.setText(o.toString());
                            }
                        }
                    });
                }
            });
            if (ExportParcelInfoActivity.this.list.size() == 1) {
                holder.binding.btnAddMoreParcel.setVisibility(0);
                holder.binding.btnRemoveParcel.setVisibility(8);
            } else if (position == ExportParcelInfoActivity.this.list.size() - 1) {
                holder.binding.btnAddMoreParcel.setVisibility(0);
                holder.binding.btnRemoveParcel.setVisibility(0);
            } else {
                holder.binding.btnAddMoreParcel.setVisibility(8);
                holder.binding.btnRemoveParcel.setVisibility(0);
            }
            holder.binding.btnAddMoreParcel.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (holder.binding.btnAddMoreParcel.getText().toString().equals(ExportParcelInfoActivity.this.getString(R.string.remove_parcel))) {
                        ExportParcelInfoActivity.this.list.remove(holder.getAdapterPosition());
                        ParcelInfoAdapter.this.notifyDataSetChanged();
                    } else if (holder.binding.txtParcelType.getText().toString().isEmpty()) {
                        ExportParcelInfoActivity.this.showSnackBar(ExportParcelInfoActivity.this.me, ExportParcelInfoActivity.this.getString(R.string.parcel_type_validation));
                    } else if (holder.binding.edtQuantity.getText().toString().isEmpty()) {
                        ExportParcelInfoActivity.this.showSnackBar(ExportParcelInfoActivity.this.me, ExportParcelInfoActivity.this.getString(R.string.quantity_validation));
                    } else if (holder.binding.edtCostValue.getText().toString().isEmpty()) {
                        ExportParcelInfoActivity.this.showSnackBar(ExportParcelInfoActivity.this.me, ExportParcelInfoActivity.this.getString(R.string.cost_validation));
                    } else {
                        parcel.setAdded(true);
                        ExportParcelInfoActivity.this.list.add(new Parcel());
                        ParcelInfoAdapter.this.notifyDataSetChanged();
                    }
                }
            });
            holder.binding.btnRemoveParcel.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    ExportParcelInfoActivity.this.list.remove(position);
                    ParcelInfoAdapter.this.notifyDataSetChanged();
                }
            });
            holder.binding.setData(parcel);
            holder.binding.executePendingBindings();
        }

        public int getItemCount() {
            return ExportParcelInfoActivity.this.list.size();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityExportParcelInfoBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.export));
        if (getIntent().hasExtra("data")) {
            this.export = (Export) getIntent().getSerializableExtra("data");
        }
        if (getIntent().hasExtra("HistoryType")) {
            this.isFromHistory = true;
            this.binding.btnSkip.setText(getString(R.string.close));
        }
        this.binding.layoutSteps.txtParcelInfoLabel.setBackground(ContextCompat.getDrawable(this.me, R.drawable.ic_round_shape_red));
        this.binding.layoutSteps.txtStep3.setText(getString(R.string.shipping_to));
        this.binding.linBack.setOnClickListener(this);
        this.binding.linSkip.setOnClickListener(this);
        this.binding.recParcelInfo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.adapter = new ParcelInfoAdapter();
        this.binding.recParcelInfo.setAdapter(this.adapter);
        this.list.add(new Parcel());
        setRedirectClass(ExportParcelInfoActivity.class);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.lin_back:
                super.onBackPressed();
                return;
            case R.id.lin_skip:
                if (getRedirectClass().equals(HistoryActivity.class)) {
                    startActivity(new Intent(this.me, HistoryActivity.class).putExtra("HistoryType", "Export").setFlags(67108864));
                    onFinishAll(new Class[]{ExportPickupAddressActivity.class, ExportDeliveryAddressActivity.class, ExportPickUpDateTimeActivity.class});
                    finish();
                    return;
                } else if (this.list.size() <= 0 || this.list.size() == 1) {
                    Parcel parcel = (Parcel) this.list.get(this.list.size() - 1);
                    if (parcel.getParcelType() == null || parcel.getParcelType().isEmpty()) {
                        showSnackBar(this.me, getString(R.string.parcel_type_validation));
                        return;
                    } else if (parcel.getQuantity() == null || parcel.getQuantity().isEmpty()) {
                        showSnackBar(this.me, getString(R.string.quantity_validation));
                        return;
                    } else if (parcel.getCostValue() == null || parcel.getCostValue().isEmpty()) {
                        showSnackBar(this.me, getString(R.string.cost_validation));
                        return;
                    } else {
                        setParcelInfo();
                        return;
                    }
                } else {
                    setParcelInfo();
                    return;
                }
            default:
                return;
        }
    }

    private void callExportOrderAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.EXPORT_ORDER);
        requestParams.put("user_id", Utils.getUserInfo(this.me).getId());
        requestParams.put("delivery_fullname", this.export.getFullName() == null ? "" : this.export.getFullName());
        requestParams.put("delivery_country", this.export.getCountryId() == null ? "" : this.export.getCountryId());
        requestParams.put("delivery_phone", this.export.getPhoneNo() == null ? "" : this.export.getPhoneNo());
        requestParams.put("delivery_city", this.export.getCityId() == null ? "" : this.export.getCityId());
        requestParams.put("delivery_postcode", this.export.getPostCode() == null ? "" : this.export.getPostCode());
        requestParams.put("delivery_address1", this.export.getAddress1() == null ? "" : this.export.getAddress1());
        requestParams.put("delivery_address2", this.export.getAddress2() == null ? "" : this.export.getAddress2());
        requestParams.put("pickup_datetime", this.export.getPickupDateTIme() == null ? "" : this.export.getPickupDateTIme());
        requestParams.put("address_id", this.export.getAddressId() == null ? "" : this.export.getAddressId());
        requestParams.put("parcelinfo", String.valueOf(this.jsonParcelInfoArray));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ExportParcelInfoActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        ExportParcelInfoActivity.this.showConfirmationDialog(ExportParcelInfoActivity.this.getString(R.string.do_you_want_to_continue), new OnConfirmationDialog() {
                            public void onYes() {
                                EventBus.getDefault().post(new Intent().putExtra("ReorderExport", true));
                                ExportParcelInfoActivity.this.finish();
                            }

                            public void onNo() {
                                ExportParcelInfoActivity.this.startActivity(new Intent(ExportParcelInfoActivity.this.me, HomeActivity.class));
                                ExportParcelInfoActivity.this.finishAffinity();
                            }
                        });
                        return;
                    } else {
                        ExportParcelInfoActivity.this.showSimpleDialog(ExportParcelInfoActivity.this.getString(R.string.error_message), new OnDialog() {
                            public void onOk() {
                                ExportParcelInfoActivity.this.dismissDialog();
                            }
                        });
                        return;
                    }
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                ExportParcelInfoActivity.this.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }

    private void setParcelInfo() {
        this.jsonParcelInfoArray = new JsonArray();
        for (int i = 0; i < this.list.size(); i++) {
            Parcel parcel = (Parcel) this.list.get(i);
            JsonElement jsonObject = new JsonObject();
            if (!((parcel.getParcelTypeId() == null || parcel.getParcelTypeId().isEmpty()) && ((parcel.getQuantity() == null || parcel.getQuantity().isEmpty()) && ((parcel.getCostValue() == null || parcel.getCostValue().isEmpty()) && ((parcel.getWeightOfParcel() == null || parcel.getWeightOfParcel().isEmpty()) && ((parcel.getLength() == null || parcel.getLength().isEmpty()) && ((parcel.getHeight() == null || parcel.getHeight().isEmpty()) && (parcel.getWidth() == null || parcel.getWidth().isEmpty())))))))) {
                jsonObject.addProperty("parcel_type", parcel.getParcelTypeId());
                jsonObject.addProperty("parcel_quantity", parcel.getQuantity());
                jsonObject.addProperty("parcel_cost_value", parcel.getCostValue());
                jsonObject.addProperty("parcel_weight", parcel.getWeightOfParcel());
                jsonObject.addProperty("parcel_length", parcel.getLength());
                jsonObject.addProperty("parcel_width", parcel.getWidth());
                jsonObject.addProperty("parcel_height", parcel.getHeight());
                this.jsonParcelInfoArray.add(jsonObject);
            }
        }
        callExportOrderAPI();
        Utils.printMsg("parcelInfo", this.jsonParcelInfoArray + "");
    }

    private void setData() {
        this.parcelTypeArrayList.clear();
        ParcelType parcelType = new ParcelType();
        parcelType.setId("1");
        parcelType.setParcelType("Type1");
        this.parcelTypeArrayList.add(parcelType);
        parcelType = new ParcelType();
        parcelType.setId("2");
        parcelType.setParcelType("Type2");
        this.parcelTypeArrayList.add(parcelType);
        parcelType = new ParcelType();
        parcelType.setId("3");
        parcelType.setParcelType("Type3");
        this.parcelTypeArrayList.add(parcelType);
        parcelType = new ParcelType();
        parcelType.setId("4");
        parcelType.setParcelType("Type4");
        this.parcelTypeArrayList.add(parcelType);
    }

    private String getParcelTypeId(String name) {
        String parcelTypeId = "";
        if (Utils.getParcelTypeList(this.me).size() > 0) {
            Iterator it = Utils.getParcelTypeList(this.me).iterator();
            while (it.hasNext()) {
                ParcelType parcel = (ParcelType) it.next();
                if (parcel.getParcelType().equalsIgnoreCase(name)) {
                    parcelTypeId = parcel.getId();
                }
            }
        }
        return parcelTypeId;
    }
}
