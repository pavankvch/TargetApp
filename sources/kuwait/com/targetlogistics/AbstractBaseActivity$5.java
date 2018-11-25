package kuwait.com.targetlogistics;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class AbstractBaseActivity$5 implements OnClickListener {
    final /* synthetic */ AbstractBaseActivity this$0;
    final /* synthetic */ Dialog val$dialog;

    AbstractBaseActivity$5(AbstractBaseActivity this$0, Dialog dialog) {
        this.this$0 = this$0;
        this.val$dialog = dialog;
    }

    public void onClick(View view) {
        this.val$dialog.dismiss();
    }
}
