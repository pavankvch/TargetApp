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
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;

public class ActivityMyProfileBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView btnUpdateProfile;
    @NonNull
    public final EditText edtEmailAddress;
    @NonNull
    public final EditText edtFullName;
    @NonNull
    public final EditText edtMobileNo;
    @NonNull
    public final ImageView imgRightArrow;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final RelativeLayout relArea;
    @NonNull
    public final TextInputLayout tIEmailAddress;
    @NonNull
    public final TextInputLayout tIFullName;
    @NonNull
    public final TextInputLayout tILArea;
    @NonNull
    public final TextInputLayout tIMobileNo;
    @NonNull
    public final TextView textArea;

    static {
        sViewsWithIds.put(R.id.tIFullName, 1);
        sViewsWithIds.put(R.id.edtFullName, 2);
        sViewsWithIds.put(R.id.tIEmailAddress, 3);
        sViewsWithIds.put(R.id.edtEmailAddress, 4);
        sViewsWithIds.put(R.id.tIMobileNo, 5);
        sViewsWithIds.put(R.id.edtMobileNo, 6);
        sViewsWithIds.put(R.id.rel_area, 7);
        sViewsWithIds.put(R.id.tILArea, 8);
        sViewsWithIds.put(R.id.textArea, 9);
        sViewsWithIds.put(R.id.img_right_arrow, 10);
        sViewsWithIds.put(R.id.btn_update_profile, 11);
    }

    public ActivityMyProfileBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds);
        this.btnUpdateProfile = (TextView) bindings[11];
        this.edtEmailAddress = (EditText) bindings[4];
        this.edtFullName = (EditText) bindings[2];
        this.edtMobileNo = (EditText) bindings[6];
        this.imgRightArrow = (ImageView) bindings[10];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relArea = (RelativeLayout) bindings[7];
        this.tIEmailAddress = (TextInputLayout) bindings[3];
        this.tIFullName = (TextInputLayout) bindings[1];
        this.tILArea = (TextInputLayout) bindings[8];
        this.tIMobileNo = (TextInputLayout) bindings[5];
        this.textArea = (TextView) bindings[9];
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
    public static ActivityMyProfileBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMyProfileBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityMyProfileBinding) DataBindingUtil.inflate(inflater, R.layout.activity_my_profile, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityMyProfileBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMyProfileBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_my_profile, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityMyProfileBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityMyProfileBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_my_profile_0".equals(view.getTag())) {
            return new ActivityMyProfileBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
