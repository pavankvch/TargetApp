package kuwait.com.targetlogistics.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import kuwait.com.targetlogistics.AbstractBaseActivity;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityRegistrationBinding;
import kuwait.com.targetlogistics.home.HomeActivity;
import kuwait.com.targetlogistics.home.TermsConditionsActivity;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.Area;
import kuwait.com.targetlogistics.model.UserInfo;
import kuwait.com.targetlogistics.searchable.SearchActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends BaseActivity {
    private static int searchResult = 101;
    private String areaId;
    ActivityRegistrationBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityRegistrationBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        setFullScreen();
        init();
    }

    public void init() {
        Utils.textUnderLine(this.binding.tvLogin);
        Utils.textUnderLine(this.binding.tvTermsCondition);
        Utils.setFonts(this.binding.tILPassword);
        this.binding.tvLogin.setOnClickListener(this.me);
        this.binding.tvRegister.setOnClickListener(this.me);
        this.binding.tvArea.setOnClickListener(this.me);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvArea:
                startActivityForResult(new Intent(this.me, SearchActivity.class), searchResult);
                setRedirectClass(RegistrationActivity.class);
                return;
            case R.id.tvLogin:
                finish();
                return;
            case R.id.tvRegister:
                Validation();
                return;
            case R.id.tvTermsCondition:
                startActivity(new Intent(this.me, TermsConditionsActivity.class));
                return;
            default:
                super.onClick(view);
                return;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == -1 && requestCode == searchResult) {
            Area data1 = (Area) data.getSerializableExtra("Area");
            this.binding.tvArea.setText(data1.getAreaName());
            this.areaId = Utils.getAreaId(data1.getAreaName(), this.me);
            Utils.printMsg("AreaId", this.areaId);
        }
    }

    private void Validation() {
        if (this.binding.edtFullName.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_full_name));
            this.binding.edtFullName.requestFocus();
        } else if (this.binding.edtFullName.getText().toString().length() < 3) {
            showSnackBar(this.me, getString(R.string.more_then_charecters));
            this.binding.edtFullName.requestFocus();
        } else if (this.binding.edtEmail.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.enter_email_address));
            this.binding.edtEmail.requestFocus();
        } else if (!Utils.isValidEmail(this.binding.edtEmail.getText().toString())) {
            showSnackBar(this.me, getString(R.string.enter_valid_email_address));
            this.binding.edtEmail.requestFocus();
        } else if (this.binding.edtPassword.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.enter_password));
            this.binding.edtPassword.requestFocus();
        } else if (this.binding.edtPassword.getText().toString().length() < 5) {
            showSnackBar(this.me, getString(R.string.password_length));
            this.binding.edtPassword.requestFocus();
        } else if (this.binding.edtMobile.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_mobile_no));
            this.binding.edtMobile.requestFocus();
        } else if (this.binding.edtMobile.getText().toString().length() < 7 || this.binding.edtMobile.getText().toString().length() > 10) {
            showSnackBar(this.me, getString(R.string.please_enter_valid_mob_no));
            this.binding.edtMobile.requestFocus();
        } else if (this.binding.tvArea.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_select_area));
        } else {
            callRegistrationAPI();
        }
    }

    private void callRegistrationAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.REGISTRATION);
        requestParams.put("fullname", this.binding.edtFullName.getText().toString().trim());
        requestParams.put("email", this.binding.edtEmail.getText().toString().trim());
        requestParams.put(PhoneAuthProvider.PROVIDER_ID, this.binding.edtMobile.getText().toString().trim());
        requestParams.put(EmailAuthProvider.PROVIDER_ID, this.binding.edtPassword.getText().toString().trim());
        requestParams.put("areaid", this.areaId);
        requestParams.put("imei", AbstractBaseActivity.getAndroidId(MyApplication.getInstance()));
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        requestParams.put("device_type", "a");
        requestParams.put("device_id", Utils.getGCMToken(MyApplication.getInstance()));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                RegistrationActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        UserInfo userInfo = (UserInfo) new Gson().fromJson(((JsonObject) response.body()).getAsJsonObject("data").getAsJsonObject("user_data"), new TypeToken<UserInfo>() {
                        }.getType());
                        if (userInfo != null) {
                            Utils.setUserId(RegistrationActivity.this.me, userInfo.getId());
                        }
                        MyApplication.getInstance().callInitialization();
                        RegistrationActivity.this.startActivity(new Intent(RegistrationActivity.this.me, HomeActivity.class));
                        RegistrationActivity.this.finish();
                        return;
                    }
                    RegistrationActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                        public void onOk() {
                            RegistrationActivity.this.dismissDialog();
                        }
                    });
                    return;
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                RegistrationActivity.this.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
