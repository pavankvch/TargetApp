package kuwait.com.targetlogistics.user;

import android.os.Bundle;
import android.view.View;
import com.google.gson.JsonObject;
import java.util.HashMap;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityForgotPasswordBinding;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity {
    private ActivityForgotPasswordBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        this.me = this;
        setToolBar(this.baseBinding.mToolbar);
        setTitle(getString(R.string.forgot_password_title));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                validation();
                break;
        }
        super.onClick(view);
    }

    private void validation() {
        if (this.binding.edtEmail.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.enter_email_address));
            this.binding.edtEmail.requestFocus();
        } else if (Utils.isValidEmail(this.binding.edtEmail.getText().toString())) {
            callForgotPasswordAPI();
        } else {
            showSnackBar(this.me, getString(R.string.enter_valid_email_address));
            this.binding.edtEmail.requestFocus();
        }
    }

    private void callForgotPasswordAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.FORGOT_PASSWORD);
        requestParams.put("email", this.binding.edtEmail.getText().toString().trim());
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ForgotPasswordActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        ForgotPasswordActivity.this.showSimpleDialog(ForgotPasswordActivity.this.getString(R.string.password_rest_link), new OnDialog() {
                            public void onOk() {
                                ForgotPasswordActivity.this.finish();
                            }
                        });
                        return;
                    } else {
                        ForgotPasswordActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                            public void onOk() {
                                ForgotPasswordActivity.this.dismissDialog();
                            }
                        });
                        return;
                    }
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                ForgotPasswordActivity.this.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
