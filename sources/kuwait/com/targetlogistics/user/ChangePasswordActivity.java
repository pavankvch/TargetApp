package kuwait.com.targetlogistics.user;

import android.os.Bundle;
import android.view.View;
import com.google.gson.JsonObject;
import java.util.HashMap;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityChangePasswordBinding;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity {
    ActivityChangePasswordBinding binding;
    private boolean isNewPasswordShow = false;
    private boolean isOldPasswordShow = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityChangePasswordBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.change_pwd));
        Utils.setFonts(this.binding.tILOldPassword);
        Utils.setFonts(this.binding.tILNewPassword);
        Utils.setFonts(this.binding.tILConfirmPassword);
        this.binding.btnUpdatePassword.setOnClickListener(this);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_update_password:
                validation();
                return;
            default:
                return;
        }
    }

    private void validation() {
        if (this.binding.edtOldPassword.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_old_pwd));
            this.binding.edtOldPassword.requestFocus();
        } else if (this.binding.edtNewPassword.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_new_pwd));
            this.binding.edtNewPassword.requestFocus();
        } else if (this.binding.edtNewPassword.getText().toString().length() < 5) {
            showSnackBar(this.me, getString(R.string.password_length));
            this.binding.edtNewPassword.requestFocus();
        } else if (this.binding.edtConfirmPassword.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_confirm_pwd));
            this.binding.edtConfirmPassword.requestFocus();
        } else if (this.binding.edtConfirmPassword.getText().toString().equals(this.binding.edtNewPassword.getText().toString())) {
            callChangePasswordAPI();
        } else {
            showSnackBar(this.me, getString(R.string.password_not_match));
            this.binding.edtConfirmPassword.requestFocus();
        }
    }

    private void callChangePasswordAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.CHANGE_PASSWORD);
        requestParams.put("user_id", Utils.getUserId(this.me));
        requestParams.put("new_password", this.binding.edtNewPassword.getText().toString().trim());
        requestParams.put("confirm_new_password", this.binding.edtConfirmPassword.getText().toString().trim());
        requestParams.put("old_password", this.binding.edtOldPassword.getText().toString().trim());
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ChangePasswordActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        ChangePasswordActivity.this.showSimpleDialog(ChangePasswordActivity.this.getString(R.string.your_password_updated), new OnDialog() {
                            public void onOk() {
                                ChangePasswordActivity.this.finish();
                            }
                        });
                        return;
                    } else {
                        ChangePasswordActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                            public void onOk() {
                                ChangePasswordActivity.this.dismissDialog();
                            }
                        });
                        return;
                    }
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                ChangePasswordActivity.this.dismissDialog();
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
