package kuwait.com.targetlogistics.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.NewAddressActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.APIError;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityAddressbookBinding;
import kuwait.com.targetlogistics.databinding.RawAddressBookBinding;
import kuwait.com.targetlogistics.interfaces.OnConfirmationDialog;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.Address;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressBookActivity extends BaseActivity {
    AddressBookAdapter adapter;
    private ArrayList<Address> addressArrayList;
    ActivityAddressbookBinding binding;

    public class AddressBookAdapter extends Adapter<VeiwHolder> {

        public class VeiwHolder extends ViewHolder {
            RawAddressBookBinding binding;

            public VeiwHolder(RawAddressBookBinding itemView) {
                super(itemView.getRoot());
                this.binding = itemView;
            }
        }

        public VeiwHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VeiwHolder((RawAddressBookBinding) DataBindingUtil.inflate(LayoutInflater.from(AddressBookActivity.this.me), R.layout.raw_address_book, parent, false));
        }

        public void onBindViewHolder(final VeiwHolder holder, int position) {
            final Address address = (Address) AddressBookActivity.this.addressArrayList.get(position);
            holder.binding.setData(address);
            holder.binding.executePendingBindings();
            holder.binding.relEdit.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AddressBookActivity.this.startActivity(new Intent(AddressBookActivity.this.me, NewAddressActivity.class).putExtra("Address", address));
                }
            });
            holder.binding.relDelete.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AddressBookActivity.this.showConfirmationDialog(AddressBookActivity.this.getString(R.string.delete_confirmation_message), new OnConfirmationDialog() {
                        public void onYes() {
                            AddressBookActivity.this.callDeleteAddressAPI(address.getPickupAddressId());
                            AddressBookActivity.this.addressArrayList.remove(holder.getAdapterPosition());
                            Utils.setAddressList(AddressBookActivity.this.me, AddressBookActivity.this.addressArrayList);
                            if (AddressBookActivity.this.addressArrayList.size() <= 0) {
                                AddressBookActivity.this.binding.recAddressList.setVisibility(8);
                                AddressBookActivity.this.binding.linNoAddress.setVisibility(0);
                                return;
                            }
                            AddressBookAdapter.this.notifyDataSetChanged();
                        }

                        public void onNo() {
                            AddressBookActivity.this.dismissDialog();
                        }
                    });
                }
            });
        }

        public int getItemCount() {
            return AddressBookActivity.this.addressArrayList.size();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAddressbookBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.address_book));
        this.addressArrayList = new ArrayList();
        this.addressArrayList.addAll(Utils.getAddressList(this.me));
        this.binding.recAddressList.setLayoutManager(new LinearLayoutManager(this.me, 1, false));
        this.binding.recAddressList.setHasFixedSize(true);
        this.adapter = new AddressBookAdapter();
        this.binding.recAddressList.setAdapter(this.adapter);
        this.binding.btnAddAddress.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_address:
                startActivity(new Intent(this.me, NewAddressActivity.class));
                return;
            default:
                super.onClick(view);
                return;
        }
    }

    protected void onResume() {
        super.onResume();
        callGetAddressAPI();
    }

    private void callDeleteAddressAPI(String id) {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.DELETE_ADDRESS);
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        requestParams.put("address_id", id);
        requestParams.put("user_id", Utils.getUserId(this.me));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                AddressBookActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (!BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        AddressBookActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                            public void onOk() {
                                AddressBookActivity.this.dismissDialog();
                            }
                        });
                        return;
                    }
                    return;
                }
                APIError error = ErrorUtils.parseError(response);
                Utils.printMsg("error message", error.message());
                AddressBookActivity.this.showSimpleDialog(error.message(), new OnDialog() {
                    public void onOk() {
                        AddressBookActivity.this.dismissDialog();
                    }
                });
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                AddressBookActivity.this.showSimpleDialog(t.getMessage(), new OnDialog() {
                    public void onOk() {
                        AddressBookActivity.this.dismissDialog();
                    }
                });
                Utils.printMsg("fail", t.getMessage());
            }
        });
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
                AddressBookActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<ArrayList<Address>>() {
                        }.getType();
                        if (((JsonObject) response.body()).get("data") instanceof JsonObject) {
                            AddressBookActivity.this.binding.recAddressList.setVisibility(8);
                            AddressBookActivity.this.binding.linNoAddress.setVisibility(0);
                            return;
                        }
                        AddressBookActivity.this.addressArrayList = (ArrayList) gson.fromJson(((JsonObject) response.body()).get("data"), listType);
                        if (AddressBookActivity.this.addressArrayList.size() == 0) {
                            AddressBookActivity.this.binding.recAddressList.setVisibility(8);
                            AddressBookActivity.this.binding.linNoAddress.setVisibility(0);
                            return;
                        }
                        Utils.setAddressList(AddressBookActivity.this.me, AddressBookActivity.this.addressArrayList);
                        AddressBookActivity.this.binding.recAddressList.setVisibility(0);
                        AddressBookActivity.this.binding.linNoAddress.setVisibility(8);
                        AddressBookActivity.this.adapter.notifyDataSetChanged();
                        return;
                    }
                    AddressBookActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                        public void onOk() {
                            AddressBookActivity.this.dismissDialog();
                        }
                    });
                    return;
                }
                APIError error = ErrorUtils.parseError(response);
                Utils.printMsg("error message", error.message());
                AddressBookActivity.this.showSimpleDialog(error.message(), new OnDialog() {
                    public void onOk() {
                        AddressBookActivity.this.dismissDialog();
                    }
                });
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                AddressBookActivity.this.showSimpleDialog(t.getMessage(), new OnDialog() {
                    public void onOk() {
                        AddressBookActivity.this.dismissDialog();
                    }
                });
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
