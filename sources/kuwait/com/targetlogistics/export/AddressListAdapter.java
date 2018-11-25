package kuwait.com.targetlogistics.export;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import kuwait.com.targetlogistics.AbstractBaseActivity;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.NewAddressActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.APIError;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.RawAddressLayoutBinding;
import kuwait.com.targetlogistics.delivery_logistics.DeliveryDateTimeActivity;
import kuwait.com.targetlogistics.interfaces.OnConfirmationDialog;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.Address;
import kuwait.com.targetlogistics.model.Export;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressListAdapter extends Adapter<ViewHolder> {
    public static Export export = new Export();
    private ArrayList<Address> addressArrayList;
    private String historyType = "";
    private boolean isFromHistory = false;
    private int lastCheckedPosition = -1;
    private BaseActivity mContext;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        RawAddressLayoutBinding binding;

        public ViewHolder(RawAddressLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public AddressListAdapter(BaseActivity me, ArrayList<Address> addressArrayList, boolean isFromHistory, String historyType) {
        this.mContext = me;
        this.addressArrayList = addressArrayList;
        this.isFromHistory = isFromHistory;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((RawAddressLayoutBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mContext), R.layout.raw_address_layout, parent, false));
    }

    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Address address = (Address) this.addressArrayList.get(position);
        holder.binding.setData(address);
        holder.binding.executePendingBindings();
        address.setAddressSelected(position == this.lastCheckedPosition);
        holder.binding.txtSelectAddress.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent;
                boolean z = false;
                AddressListAdapter.this.lastCheckedPosition = holder.getAdapterPosition();
                Address address = (Address) AddressListAdapter.this.addressArrayList.get(AddressListAdapter.this.lastCheckedPosition);
                AddressListAdapter.this.notifyItemRangeChanged(0, AddressListAdapter.this.addressArrayList.size());
                if (address.isAddressSelected()) {
                    holder.binding.txtSelectAddress.setBackground(ContextCompat.getDrawable(AddressListAdapter.this.mContext, R.drawable.shape_button_red));
                    holder.binding.txtSelectAddress.setTextColor(ContextCompat.getColor(AddressListAdapter.this.mContext, R.color.white));
                    holder.binding.imgSelected.setVisibility(0);
                } else {
                    holder.binding.txtSelectAddress.setBackground(ContextCompat.getDrawable(AddressListAdapter.this.mContext, R.drawable.shape_rounded_rect_red));
                    holder.binding.txtSelectAddress.setTextColor(ContextCompat.getColor(AddressListAdapter.this.mContext, R.color.text_color));
                    holder.binding.imgSelected.setVisibility(8);
                }
                if (!address.isAddressSelected()) {
                    z = true;
                }
                address.setAddressSelected(z);
                if (AbstractBaseActivity.getRedirectClass().equals(ExportPickupAddressActivity.class)) {
                    intent = new Intent(AddressListAdapter.this.mContext, ExportPickUpDateTimeActivity.class);
                } else {
                    intent = new Intent(AddressListAdapter.this.mContext, DeliveryDateTimeActivity.class);
                }
                if (address.isAddressSelected()) {
                    if (AbstractBaseActivity.getRedirectClass().equals(ExportPickupAddressActivity.class)) {
                        AddressListAdapter.export.setAddressId(address.getPickupAddressId());
                        intent.putExtra("data", AddressListAdapter.export);
                    } else {
                        BaseActivity.delivery.setAddressId(address.getPickupAddressId());
                    }
                }
                if (AddressListAdapter.this.isFromHistory) {
                    intent.putExtra("HistoryType", AddressListAdapter.this.historyType);
                }
                AddressListAdapter.this.mContext.startActivity(intent);
            }
        });
        if (address.isAddressSelected()) {
            holder.binding.txtSelectAddress.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.shape_button_red));
            holder.binding.txtSelectAddress.setTextColor(ContextCompat.getColor(this.mContext, R.color.white));
            holder.binding.imgSelected.setVisibility(0);
        } else {
            holder.binding.txtSelectAddress.setBackground(ContextCompat.getDrawable(this.mContext, R.drawable.shape_rounded_rect_red));
            holder.binding.txtSelectAddress.setTextColor(ContextCompat.getColor(this.mContext, R.color.text_color));
            holder.binding.imgSelected.setVisibility(8);
        }
        holder.binding.imgEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AddressListAdapter.this.mContext.startActivity(new Intent(AddressListAdapter.this.mContext, NewAddressActivity.class).putExtra("Address", address));
            }
        });
        holder.binding.imgDelete.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AddressListAdapter.this.mContext.showConfirmationDialog(AddressListAdapter.this.mContext.getString(R.string.delete_confirmation_message), new OnConfirmationDialog() {
                    public void onYes() {
                        AddressListAdapter.this.callDeleteAddressAPI(address.getPickupAddressId());
                        AddressListAdapter.this.addressArrayList.remove(holder.getAdapterPosition());
                        AddressListAdapter.this.notifyDataSetChanged();
                    }

                    public void onNo() {
                        AddressListAdapter.this.mContext.dismissDialog();
                    }
                });
            }
        });
    }

    public int getItemCount() {
        return this.isFromHistory ? 1 : this.addressArrayList.size();
    }

    private void callDeleteAddressAPI(String id) {
        this.mContext.showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.DELETE_ADDRESS);
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        requestParams.put("address_id", id);
        requestParams.put("user_id", Utils.getUserId(this.mContext));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                AddressListAdapter.this.mContext.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (!BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        AddressListAdapter.this.mContext.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                            public void onOk() {
                                AddressListAdapter.this.mContext.dismissDialog();
                            }
                        });
                        return;
                    }
                    return;
                }
                APIError error = ErrorUtils.parseError(response);
                Utils.printMsg("error message", error.message());
                AddressListAdapter.this.mContext.showSimpleDialog(error.message(), new OnDialog() {
                    public void onOk() {
                        AddressListAdapter.this.mContext.dismissDialog();
                    }
                });
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                AddressListAdapter.this.mContext.showSimpleDialog(t.getMessage(), new OnDialog() {
                    public void onOk() {
                        AddressListAdapter.this.mContext.dismissDialog();
                    }
                });
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
