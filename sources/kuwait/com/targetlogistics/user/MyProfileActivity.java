package kuwait.com.targetlogistics.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.APIError;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityMyProfileBinding;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.Area;
import kuwait.com.targetlogistics.model.UserInfo;
import kuwait.com.targetlogistics.searchable.SearchActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileActivity extends BaseActivity {
    private static int searchResult = 101;
    private String areaId;
    ActivityMyProfileBinding binding;
    private UserInfo userInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMyProfileBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.my_profile));
        this.binding.btnUpdateProfile.setOnClickListener(this);
        this.binding.textArea.setOnClickListener(this);
        callGetProfileAPI();
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_update_profile:
                Validation();
                return;
            case R.id.textArea:
                startActivityForResult(new Intent(this.me, SearchActivity.class), searchResult);
                setRedirectClass(MyProfileActivity.class);
                return;
            default:
                return;
        }
    }

    private void Validation() {
        if (this.binding.edtFullName.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_full_name));
            this.binding.edtFullName.requestFocus();
        } else if (this.binding.edtMobileNo.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_mobile_no));
            this.binding.edtMobileNo.requestFocus();
        } else if (this.binding.edtMobileNo.getText().toString().length() < 7) {
            showSnackBar(this.me, getString(R.string.please_enter_valid_mob_no));
            this.binding.edtMobileNo.requestFocus();
        } else if (this.binding.textArea.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_select_area));
            this.binding.textArea.requestFocus();
        } else {
            callEditProfileAPI();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == -1 && requestCode == searchResult) {
            Area data1 = (Area) data.getSerializableExtra("Area");
            this.binding.textArea.setText(data1.getAreaName());
            for (int i = 0; i < Utils.getAreaList(this.me).size(); i++) {
                Area area = (Area) Utils.getAreaList(this.me).get(i);
                if (area.getAreaName().equalsIgnoreCase(data1.getAreaName())) {
                    this.areaId = area.getAreaId();
                    Utils.printMsg("AreaId", this.areaId);
                }
            }
        }
    }

    private void callGetProfileAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.GET_PROFILE);
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        requestParams.put("user_id", Utils.getUserId(this.me));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallGet(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                MyProfileActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        MyProfileActivity.this.userInfo = (UserInfo) new Gson().fromJson(((JsonObject) response.body()).getAsJsonObject("data").getAsJsonObject("user_data"), new TypeToken<UserInfo>() {
                        }.getType());
                        if (MyProfileActivity.this.userInfo != null) {
                            Utils.setUserId(MyProfileActivity.this.me, MyProfileActivity.this.userInfo.getId());
                            Utils.setUserInfo(MyProfileActivity.this.me, MyProfileActivity.this.userInfo);
                            MyProfileActivity.this.setData();
                            return;
                        }
                        return;
                    }
                    MyProfileActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                        public void onOk() {
                            MyProfileActivity.this.dismissDialog();
                        }
                    });
                    return;
                }
                APIError error = ErrorUtils.parseError(response);
                Utils.printMsg("error message", error.message());
                MyProfileActivity.this.showSimpleDialog(error.message(), new OnDialog() {
                    public void onOk() {
                        MyProfileActivity.this.dismissDialog();
                    }
                });
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                MyProfileActivity.this.showSimpleDialog(t.getMessage(), new OnDialog() {
                    public void onOk() {
                        MyProfileActivity.this.dismissDialog();
                    }
                });
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }

    private void setData() {
        this.binding.edtEmailAddress.setText(Utils.getUserInfo(this.me).getEmail());
        this.binding.edtFullName.setText(this.userInfo.getFullname());
        this.binding.edtMobileNo.setText(this.userInfo.getPhone());
        this.areaId = this.userInfo.getAreaid();
        this.binding.textArea.setText(Utils.getAreaName(this.areaId, this.me));
    }

    private void callEditProfileAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.EDIT_PROFILE);
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        requestParams.put("user_id", Utils.getUserId(this.me));
        requestParams.put("fullname", this.binding.edtFullName.getText().toString().trim());
        requestParams.put("email", this.binding.edtEmailAddress.getText().toString().trim());
        requestParams.put(PhoneAuthProvider.PROVIDER_ID, this.binding.edtMobileNo.getText().toString().trim());
        requestParams.put("areaid", this.areaId);
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                MyProfileActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<UserInfo>() {
                        }.getType();
                        JsonObject jsonObject = ((JsonObject) response.body()).getAsJsonObject("data");
                        MyProfileActivity.this.showSimpleDialog(MyProfileActivity.this.getString(R.string.your_profile_updated), new OnDialog() {
                            public void onOk() {
                                MyProfileActivity.this.finish();
                            }
                        });
                        return;
                    }
                    MyProfileActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                        public void onOk() {
                            MyProfileActivity.this.dismissDialog();
                        }
                    });
                    return;
                }
                APIError error = ErrorUtils.parseError(response);
                Utils.printMsg("error message", error.message());
                MyProfileActivity.this.showSimpleDialog(error.message(), new OnDialog() {
                    public void onOk() {
                        MyProfileActivity.this.dismissDialog();
                    }
                });
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                MyProfileActivity.this.showSimpleDialog(t.getMessage(), new OnDialog() {
                    public void onOk() {
                        MyProfileActivity.this.dismissDialog();
                    }
                });
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
