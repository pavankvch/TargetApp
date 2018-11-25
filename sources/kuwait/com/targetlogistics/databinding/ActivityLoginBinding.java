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
import android.widget.LinearLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;

public class ActivityLoginBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final EditText edtEmail;
    @NonNull
    public final EditText edtPassword;
    private long mDirtyFlags = -1;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TextInputLayout tILEmail;
    @NonNull
    public final TextInputLayout tILPassword;
    @NonNull
    public final TextView tvForgotPassword;
    @NonNull
    public final TextView tvLogin;
    @NonNull
    public final TextView tvRegister;
    @NonNull
    public final View vPassword;

    static {
        sViewsWithIds.put(R.id.tILEmail, 1);
        sViewsWithIds.put(R.id.edtEmail, 2);
        sViewsWithIds.put(R.id.tILPassword, 3);
        sViewsWithIds.put(R.id.edtPassword, 4);
        sViewsWithIds.put(R.id.vPassword, 5);
        sViewsWithIds.put(R.id.tvForgotPassword, 6);
        sViewsWithIds.put(R.id.tvLogin, 7);
        sViewsWithIds.put(R.id.tvRegister, 8);
    }

    public ActivityLoginBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.edtEmail = (EditText) bindings[2];
        this.edtPassword = (EditText) bindings[4];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tILEmail = (TextInputLayout) bindings[1];
        this.tILPassword = (TextInputLayout) bindings[3];
        this.tvForgotPassword = (TextView) bindings[6];
        this.tvLogin = (TextView) bindings[7];
        this.tvRegister = (TextView) bindings[8];
        this.vPassword = (View) bindings[5];
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
    public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityLoginBinding) DataBindingUtil.inflate(inflater, R.layout.activity_login, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_login, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityLoginBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityLoginBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_login_0".equals(view.getTag())) {
            return new ActivityLoginBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
