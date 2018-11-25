package kuwait.com.targetlogistics;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import kuwait.com.targetlogistics.interfaces.OnConfirmationDialog;

class AbstractBaseActivity$2 implements OnClickListener {
    final /* synthetic */ AbstractBaseActivity this$0;
    final /* synthetic */ OnConfirmationDialog val$confirmationDialog;
    final /* synthetic */ Dialog val$dialog;

    AbstractBaseActivity$2(AbstractBaseActivity this$0, Dialog dialog, OnConfirmationDialog onConfirmationDialog) {
        this.this$0 = this$0;
        this.val$dialog = dialog;
        this.val$confirmationDialog = onConfirmationDialog;
    }

    public void onClick(View v) {
        this.val$dialog.dismiss();
        if (this.val$confirmationDialog != null) {
            this.val$confirmationDialog.onNo();
        }
    }
}
