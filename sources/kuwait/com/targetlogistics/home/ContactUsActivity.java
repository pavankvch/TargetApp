package kuwait.com.targetlogistics.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.JsonObject;
import java.util.HashMap;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.APIError;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityContactUsBinding;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends BaseActivity {
    ActivityContactUsBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityContactUsBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.contact_us));
        this.binding.btnSubmit.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                Validation();
                return;
            default:
                super.onClick(view);
                return;
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void Validation() {
        if (this.binding.edtFullName.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_full_name));
        } else if (this.binding.edtEmailAddress.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.enter_email_address));
        } else if (!Utils.isValidEmail(this.binding.edtEmailAddress.getText().toString())) {
            showSnackBar(this.me, getString(R.string.enter_valid_email_address));
        } else if (this.binding.edtMobileNo.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_mobile_no));
        } else if (this.binding.edtMobileNo.getText().toString().length() < 7) {
            showSnackBar(this.me, getString(R.string.please_enter_valid_mob_no));
        } else if (this.binding.edtMessage.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_message));
        } else {
            callContactUsAPI();
        }
        this.binding.edtMessage.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (view.getId() == R.id.edtMessage) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & 255) {
                        case 1:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });
    }

    private void callContactUsAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.CONTACT_US);
        requestParams.put("fullname", this.binding.edtFullName.getText().toString().trim());
        requestParams.put("email", this.binding.edtEmailAddress.getText().toString().trim());
        requestParams.put(PhoneAuthProvider.PROVIDER_ID, this.binding.edtMobileNo.getText().toString().trim());
        requestParams.put("message", this.binding.edtMessage.getText().toString().trim());
        requestParams.put("user_id", Utils.getUserId(this.me));
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ContactUsActivity.this.dismissDialog();
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        ContactUsActivity.this.showSimpleDialog(ContactUsActivity.this.getString(R.string.your_form_submitted), new OnDialog() {
                            public void onOk() {
                                ContactUsActivity.this.finish();
                            }
                        });
                        return;
                    } else {
                        ContactUsActivity.this.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                            public void onOk() {
                                ContactUsActivity.this.dismissDialog();
                            }
                        });
                        return;
                    }
                }
                APIError error = ErrorUtils.parseError(response);
                Utils.printMsg("error message", error.message());
                ContactUsActivity.this.showSimpleDialog(error.message(), new OnDialog() {
                    public void onOk() {
                        ContactUsActivity.this.dismissDialog();
                    }
                });
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                ContactUsActivity.this.showSimpleDialog(t.getMessage(), new OnDialog() {
                    public void onOk() {
                        ContactUsActivity.this.dismissDialog();
                    }
                });
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
