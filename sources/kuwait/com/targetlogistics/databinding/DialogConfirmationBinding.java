package kuwait.com.targetlogistics.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;

public class DialogConfirmationBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView btnNo;
    @NonNull
    public final TextView btnYes;
    private long mDirtyFlags = -1;
    @Nullable
    private String mMsg;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TextView tvMsg;

    static {
        sViewsWithIds.put(R.id.btnNo, 2);
        sViewsWithIds.put(R.id.btnYes, 3);
    }

    public DialogConfirmationBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.btnNo = (TextView) bindings[2];
        this.btnYes = (TextView) bindings[3];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvMsg = (TextView) bindings[1];
        this.tvMsg.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (13 != variableId) {
            return false;
        }
        setMsg((String) variable);
        return true;
    }

    public void setMsg(@Nullable String Msg) {
        this.mMsg = Msg;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(13);
        super.requestRebind();
    }

    @Nullable
    public String getMsg() {
        return this.mMsg;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String msg = this.mMsg;
        if ((dirtyFlags & 3) != 0) {
        }
        if ((dirtyFlags & 3) != 0) {
            TextViewBindingAdapter.setText(this.tvMsg, msg);
        }
    }

    @NonNull
    public static DialogConfirmationBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DialogConfirmationBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (DialogConfirmationBinding) DataBindingUtil.inflate(inflater, R.layout.dialog_confirmation, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static DialogConfirmationBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DialogConfirmationBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.dialog_confirmation, null, false), bindingComponent);
    }

    @NonNull
    public static DialogConfirmationBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static DialogConfirmationBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/dialog_confirmation_0".equals(view.getTag())) {
            return new DialogConfirmationBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
