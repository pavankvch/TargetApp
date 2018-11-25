package kuwait.com.targetlogistics.import_logistics;

import android.content.Intent;
import com.google.gson.JsonObject;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.home.HomeActivity;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ImportStep2Activity$1 implements Callback<JsonObject> {
    final /* synthetic */ ImportStep2Activity this$0;

    ImportStep2Activity$1(ImportStep2Activity this$0) {
        this.this$0 = this$0;
    }

    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        this.this$0.dismissDialog();
        if (response.isSuccessful()) {
            Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
            if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                this.this$0.showSimpleDialog(this.this$0.getString(R.string.order_success_message), new OnDialog() {
                    public void onOk() {
                        ImportStep2Activity$1.this.this$0.startActivity(new Intent(ImportStep2Activity$1.this.this$0.me, HomeActivity.class));
                        ImportStep2Activity$1.this.this$0.finishAffinity();
                    }
                });
                return;
            } else {
                this.this$0.showSimpleDialog(this.this$0.getString(R.string.error_message), new OnDialog() {
                    public void onOk() {
                        ImportStep2Activity$1.this.this$0.dismissDialog();
                    }
                });
                return;
            }
        }
        Utils.printMsg("error message", ErrorUtils.parseError(response).message());
    }

    public void onFailure(Call<JsonObject> call, Throwable t) {
        this.this$0.dismissDialog();
        Utils.printMsg("fail", t.getMessage());
    }
}
