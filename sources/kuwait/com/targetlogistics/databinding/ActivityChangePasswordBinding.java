package kuwait.com.targetlogistics.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;

public class ActivityChangePasswordBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView btnUpdatePassword;
    @NonNull
    public final EditText edtConfirmPassword;
    @NonNull
    public final EditText edtNewPassword;
    @NonNull
    public final EditText edtOldPassword;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final RelativeLayout relConfirmPwd;
    @NonNull
    public final RelativeLayout relNewPwd;
    @NonNull
    public final RelativeLayout relOldPwd;
    @NonNull
    public final TextInputLayout tILConfirmPassword;
    @NonNull
    public final TextInputLayout tILNewPassword;
    @NonNull
    public final TextInputLayout tILOldPassword;

    static {
        sViewsWithIds.put(R.id.rel_old_pwd, 1);
        sViewsWithIds.put(R.id.tILOldPassword, 2);
        sViewsWithIds.put(R.id.edtOldPassword, 3);
        sViewsWithIds.put(R.id.rel_new_pwd, 4);
        sViewsWithIds.put(R.id.tILNewPassword, 5);
        sViewsWithIds.put(R.id.edtNewPassword, 6);
        sViewsWithIds.put(R.id.rel_confirm_pwd, 7);
        sViewsWithIds.put(R.id.tILConfirmPassword, 8);
        sViewsWithIds.put(R.id.edtConfirmPassword, 9);
        sViewsWithIds.put(R.id.btn_update_password, 10);
    }

    public ActivityChangePasswordBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.btnUpdatePassword = (TextView) bindings[10];
        this.edtConfirmPassword = (EditText) bindings[9];
        this.edtNewPassword = (EditText) bindings[6];
        this.edtOldPassword = (EditText) bindings[3];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relConfirmPwd = (RelativeLayout) bindings[7];
        this.relNewPwd = (RelativeLayout) bindings[4];
        this.relOldPwd = (RelativeLayout) bindings[1];
        this.tILConfirmPassword = (TextInputLayout) bindings[8];
        this.tILNewPassword = (TextInputLayout) bindings[5];
        this.tILOldPassword = (TextInputLayout) bindings[2];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        return true;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    @NonNull
    public static ActivityChangePasswordBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityChangePasswordBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityChangePasswordBinding) DataBindingUtil.inflate(inflater, R.layout.activity_change_password, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityChangePasswordBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityChangePasswordBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_change_password, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityChangePasswordBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityChangePasswordBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_change_password_0".equals(view.getTag())) {
            return new ActivityChangePasswordBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
