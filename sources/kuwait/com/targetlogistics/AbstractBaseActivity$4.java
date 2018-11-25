package kuwait.com.targetlogistics;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

class AbstractBaseActivity$4 implements OnClickListener {
    final /* synthetic */ AbstractBaseActivity this$0;
    final /* synthetic */ Dialog val$dialog;

    AbstractBaseActivity$4(AbstractBaseActivity this$0, Dialog dialog) {
        this.this$0 = this$0;
        this.val$dialog = dialog;
    }

    public void onClick(View v) {
        String appPackageName = this.this$0.getPackageName();
        try {
            this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException e) {
            this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
        this.val$dialog.dismiss();
        this.this$0.finishAffinity();
    }
}
