package kuwait.com.targetlogistics;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import kuwait.com.targetlogistics.interfaces.OnDialog;

class AbstractBaseActivity$1 implements OnClickListener {
    final /* synthetic */ AbstractBaseActivity this$0;
    final /* synthetic */ Dialog val$dialog;
    final /* synthetic */ OnDialog val$onDialog;

    AbstractBaseActivity$1(AbstractBaseActivity this$0, Dialog dialog, OnDialog onDialog) {
        this.this$0 = this$0;
        this.val$dialog = dialog;
        this.val$onDialog = onDialog;
    }

    public void onClick(View view) {
        this.val$dialog.dismiss();
        if (this.val$onDialog != null) {
            this.val$onDialog.onOk();
        }
    }
}
