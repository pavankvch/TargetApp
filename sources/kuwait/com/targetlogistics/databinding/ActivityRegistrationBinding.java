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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;

public class ActivityRegistrationBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final EditText edtEmail;
    @NonNull
    public final EditText edtFullName;
    @NonNull
    public final EditText edtMobile;
    @NonNull
    public final EditText edtPassword;
    @NonNull
    public final ImageView imgRightArrow;
    private long mDirtyFlags = -1;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TextInputLayout tILEmail;
    @NonNull
    public final TextInputLayout tILFullName;
    @NonNull
    public final TextInputLayout tILMobile;
    @NonNull
    public final TextInputLayout tILPassword;
    @NonNull
    public final TextView tvArea;
    @NonNull
    public final TextView tvLogin;
    @NonNull
    public final TextView tvRegister;
    @NonNull
    public final TextView tvTermsCondition;
    @NonNull
    public final View vPassword;

    static {
        sViewsWithIds.put(R.id.tILFullName, 1);
        sViewsWithIds.put(R.id.edtFullName, 2);
        sViewsWithIds.put(R.id.tILEmail, 3);
        sViewsWithIds.put(R.id.edtEmail, 4);
        sViewsWithIds.put(R.id.tILPassword, 5);
        sViewsWithIds.put(R.id.edtPassword, 6);
        sViewsWithIds.put(R.id.vPassword, 7);
        sViewsWithIds.put(R.id.tILMobile, 8);
        sViewsWithIds.put(R.id.edtMobile, 9);
        sViewsWithIds.put(R.id.tvArea, 10);
        sViewsWithIds.put(R.id.img_right_arrow, 11);
        sViewsWithIds.put(R.id.tvRegister, 12);
        sViewsWithIds.put(R.id.tvTermsCondition, 13);
        sViewsWithIds.put(R.id.tvLogin, 14);
    }

    public ActivityRegistrationBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds);
        this.edtEmail = (EditText) bindings[4];
        this.edtFullName = (EditText) bindings[2];
        this.edtMobile = (EditText) bindings[9];
        this.edtPassword = (EditText) bindings[6];
        this.imgRightArrow = (ImageView) bindings[11];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tILEmail = (TextInputLayout) bindings[3];
        this.tILFullName = (TextInputLayout) bindings[1];
        this.tILMobile = (TextInputLayout) bindings[8];
        this.tILPassword = (TextInputLayout) bindings[5];
        this.tvArea = (TextView) bindings[10];
        this.tvLogin = (TextView) bindings[14];
        this.tvRegister = (TextView) bindings[12];
        this.tvTermsCondition = (TextView) bindings[13];
        this.vPassword = (View) bindings[7];
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
    public static ActivityRegistrationBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRegistrationBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityRegistrationBinding) DataBindingUtil.inflate(inflater, R.layout.activity_registration, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityRegistrationBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRegistrationBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_registration, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityRegistrationBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityRegistrationBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_registration_0".equals(view.getTag())) {
            return new ActivityRegistrationBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
