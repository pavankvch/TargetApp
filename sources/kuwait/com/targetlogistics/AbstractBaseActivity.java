package kuwait.com.targetlogistics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import kuwait.com.targetlogistics.common.OnItemSelected;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.DialogConfirmationBinding;
import kuwait.com.targetlogistics.databinding.DialogParcelTypeBinding;
import kuwait.com.targetlogistics.databinding.DialogSimpleBinding;
import kuwait.com.targetlogistics.databinding.DialogVersionUpdateBinding;
import kuwait.com.targetlogistics.interfaces.OnConfirmationDialog;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.interfaces.OnKeyboardVisibilityListener;
import org.greenrobot.eventbus.EventBus;

public abstract class AbstractBaseActivity extends AppCompatActivity {
    private static final String CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    private static Class<?> redirectClass;
    private AbstractBaseActivity$InternetReceiver internetReceiver;
    private Dialog mDialog;
    private Dialog mParcelTypeDialog;

    public void startActivity(View viewStart, String transactionName, Intent intent) {
        ContextCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, viewStart, transactionName).toBundle());
    }

    protected void onResume() {
        super.onResume();
        if (this.internetReceiver == null) {
            this.internetReceiver = new AbstractBaseActivity$InternetReceiver(this);
            registerReceiver(this.internetReceiver, new IntentFilter(CONNECTIVITY_CHANGE));
        }
    }

    protected void onStop() {
        super.onStop();
        if (this.internetReceiver != null) {
            unregisterReceiver(this.internetReceiver);
            this.internetReceiver = null;
        }
    }

    public static Class<?> getRedirectClass() {
        return redirectClass;
    }

    public static void setRedirectClass(Class<?> redirectClass) {
        redirectClass = redirectClass;
    }

    public void showSimpleDialog(String msg, OnDialog onDialog) {
        Dialog dialog = new Dialog(this);
        DialogSimpleBinding dialogSimpleBinding = DialogSimpleBinding.inflate(getLayoutInflater());
        dialogSimpleBinding.setMsg(msg);
        dialogSimpleBinding.btnOk.setOnClickListener(new AbstractBaseActivity$1(this, dialog, onDialog));
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(dialogSimpleBinding.getRoot());
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(-1, -2);
        window.setGravity(17);
        dialog.show();
        getWindow().getAttributes().dimAmount = 0.2f;
        getWindow().addFlags(2);
    }

    public void showConfirmationDialog(String msg, OnConfirmationDialog confirmationDialog) {
        Dialog dialog = new Dialog(this);
        DialogConfirmationBinding dialogConfirmationBinding = DialogConfirmationBinding.inflate(getLayoutInflater());
        dialogConfirmationBinding.setMsg(msg);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(dialogConfirmationBinding.getRoot());
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(-2, -2);
        window.setGravity(17);
        dialogConfirmationBinding.btnNo.setOnClickListener(new AbstractBaseActivity$2(this, dialog, confirmationDialog));
        dialogConfirmationBinding.btnYes.setOnClickListener(new AbstractBaseActivity$3(this, dialog, confirmationDialog));
        dialog.show();
        getWindow().getAttributes().dimAmount = 0.2f;
        getWindow().addFlags(2);
    }

    public void showVersionDialog(String title, String msg, Activity context, AbstractBaseActivity$onCancel onCancel) {
        Dialog dialog = new Dialog(this);
        DialogVersionUpdateBinding binding = DialogVersionUpdateBinding.inflate(getLayoutInflater());
        binding.setMsg(msg);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(binding.getRoot());
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(-2, -2);
        window.setGravity(17);
        binding.btnUpdate.setOnClickListener(new AbstractBaseActivity$4(this, dialog));
        if (Utils.getForceUpdate(context).equals("1")) {
            binding.btnCancel.setVisibility(8);
            binding.btnUpdate.setLayoutParams(new LayoutParams(-1, -2));
        }
        binding.btnCancel.setOnClickListener(new AbstractBaseActivity$5(this, dialog));
        dialog.show();
        context.getWindow().getAttributes().dimAmount = 0.2f;
        context.getWindow().addFlags(2);
    }

    public void showParcelTypeDialog(ArrayList<?> list, Context context, String title, OnItemSelected onItemSelected) {
        this.mParcelTypeDialog = new Dialog(this);
        DialogParcelTypeBinding binding = DialogParcelTypeBinding.inflate(getLayoutInflater());
        this.mParcelTypeDialog.requestWindowFeature(1);
        this.mParcelTypeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.mParcelTypeDialog.setContentView(binding.getRoot());
        this.mParcelTypeDialog.setCancelable(false);
        Window window = this.mParcelTypeDialog.getWindow();
        window.setLayout(-2, -2);
        window.setGravity(17);
        this.mParcelTypeDialog.show();
        this.mParcelTypeDialog.setCanceledOnTouchOutside(true);
        getWindow().getAttributes().dimAmount = 0.2f;
        getWindow().addFlags(2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, 1, false);
        binding.setData(title);
        binding.executePendingBindings();
        binding.recParcelType.setLayoutManager(layoutManager);
        binding.recParcelType.setAdapter(new AbstractBaseActivity$ParcelTypeAdapter(this, context, list, onItemSelected));
    }

    public void showOnlyProgressDialog() {
        try {
            if (this.mDialog == null || !this.mDialog.isShowing()) {
                this.mDialog = new Dialog(this);
                this.mDialog.requestWindowFeature(1);
                this.mDialog.setCancelable(false);
                this.mDialog.getWindow().setBackgroundDrawableResource(17170445);
                this.mDialog.setContentView(R.layout.custom_only_progress_dialog);
                this.mDialog.getWindow().setLayout(-1, -2);
                this.mDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissDialog() {
        try {
            if (this.mDialog != null && this.mDialog.isShowing()) {
                this.mDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setKeyboardListener(OnKeyboardVisibilityListener listener) {
        View activityRootView = ((ViewGroup) findViewById(16908290)).getChildAt(0);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new AbstractBaseActivity$6(this, activityRootView, listener));
    }

    @SuppressLint({"HardwareIds"})
    public static String getAndroidId(Context mContext) {
        return Secure.getString(mContext.getContentResolver(), "android_id");
    }

    @SafeVarargs
    protected final void onFinishAll(Class<? extends Activity>... aClass) {
        for (Class<? extends Activity> activity : aClass) {
            EventBus.getDefault().post(activity);
        }
    }
}
