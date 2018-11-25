package kuwait.com.targetlogistics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kuwait.com.targetlogistics.common.NoInternetActivity;
import kuwait.com.targetlogistics.common.Utils;

public class AbstractBaseActivity$InternetReceiver extends BroadcastReceiver {
    final /* synthetic */ AbstractBaseActivity this$0;

    public AbstractBaseActivity$InternetReceiver(AbstractBaseActivity this$0) {
        this.this$0 = this$0;
    }

    public void onReceive(Context context, Intent intent) {
        if (Utils.checkConnectivity(context)) {
            if (this.this$0 instanceof NoInternetActivity) {
                this.this$0.finish();
            }
        } else if (!(this.this$0 instanceof NoInternetActivity)) {
            this.this$0.startActivity(new Intent(this.this$0, NoInternetActivity.class));
        }
    }
}
