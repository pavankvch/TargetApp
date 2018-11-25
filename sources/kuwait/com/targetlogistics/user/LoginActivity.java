package kuwait.com.targetlogistics.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.auth.EmailAuthProvider;
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
import kuwait.com.targetlogistics.databinding.ActivityLoginBinding;
import kuwait.com.targetlogistics.home.HomeActivity;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.UserInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    private boolean isPasswordShow = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityLoginBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        this.me = this;
        setFullScreen();
        Utils.textUnderLine(this.binding.tvForgotPassword);
        Utils.textUnderLine(this.binding.tvRegister);
        Utils.setFonts(this.binding.tILPassword);
        this.binding.tvLogin.setOnClickListener(this.me);
        this.binding.tvRegister.setOnClickListener(this.me);
        this.binding.tvForgotPassword.setOnClickListener(this.me);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvForgotPassword:
                startActivity(new Intent(this.me, ForgotPasswordActivity.class));
                return;
            case R.id.tvLogin:
                Validation();
                return;
            case R.id.tvRegister:
                startActivity(new Intent(this.me, RegistrationActivity.class));
                return;
            default:
                super.onClick(view);
                return;
        }
    }

    private void Validation() {
        if (this.binding.edtEmail.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.enter_email_address));
            this.binding.edtEmail.requestFocus();
        } else if (!Utils.isValidEmail(this.binding.edtEmail.getText().toString())) {
            showSnackBar(this.me, getString(R.string.enter_valid_email_address));
            this.binding.edtEmail.requestFocus();
        } else if (this.binding.edtPassword.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.enter_password));
            this.binding.edtPassword.requestFocus();
        } else {
            callLoginAPI();
        }
    }

    private void callLoginAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", "login");
        requestParams.put("email", this.binding.edtEmail.getText().toString().trim());
        requestParams.put(EmailAuthProvider.PROVIDER_ID, this.binding.edtPassword.getText().toString().trim());
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        requestParams.put("imei", AbstractBaseActivity.getAndroidId(MyApplication.getInstance()));
        requestParams.put("device_type", "a");
        requestParams.put("device_id", Utils.getGCMToken(MyApplication.getInstance()));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                LoginActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        UserInfo userInfo = (UserInfo) new Gson().fromJson(((JsonObject) response.body()).getAsJsonObject("data").getAsJsonObject("user_data"), new TypeToken<UserInfo>() {
                        }.getType());
                        if (userInfo != null) {
                            Utils.setUserId(LoginActivity.this.me, userInfo.getId());
                            Utils.setUserInfo(LoginActivity.this.me, userInfo);
                        }
                        MyApplication.getInstance().callInitialization();
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this.me, HomeActivity.class));
                        LoginActivity.this.finish();
                        return;
                    }
                    LoginActivity.this.showSimpleDialog(LoginActivity.this.getString(R.string.invalid_username_or_password), new OnDialog() {
                        public void onOk() {
                            LoginActivity.this.dismissDialog();
                        }
                    });
                    return;
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                LoginActivity.this.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
