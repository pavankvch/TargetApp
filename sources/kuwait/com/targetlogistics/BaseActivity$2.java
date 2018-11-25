package kuwait.com.targetlogistics;

import android.content.Intent;
import com.google.gson.JsonObject;
import kuwait.com.targetlogistics.api.APIError;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.user.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class BaseActivity$2 implements Callback<JsonObject> {
    final /* synthetic */ BaseActivity this$0;

    BaseActivity$2(BaseActivity this$0) {
        this.this$0 = this$0;
    }

    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        this.this$0.dismissDialog();
        if (response.isSuccessful()) {
            Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
            if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                Utils.setUserId(this.this$0.me, "");
                this.this$0.startActivity(new Intent(this.this$0.me, LoginActivity.class));
                this.this$0.finishAffinity();
                return;
            }
            this.this$0.showSimpleDialog(((JsonObject) response.body()).get("message").getAsString(), new OnDialog() {
                public void onOk() {
                    BaseActivity$2.this.this$0.dismissDialog();
                }
            });
            return;
        }
        APIError error = ErrorUtils.parseError(response);
        Utils.printMsg("error message", error.message());
        this.this$0.showSimpleDialog(error.message(), new OnDialog() {
            public void onOk() {
                BaseActivity$2.this.this$0.dismissDialog();
            }
        });
    }

    public void onFailure(Call<JsonObject> call, Throwable t) {
        this.this$0.showSimpleDialog(t.getMessage(), new OnDialog() {
            public void onOk() {
                BaseActivity$2.this.this$0.dismissDialog();
            }
        });
        Utils.printMsg("fail", t.getMessage());
    }
}
