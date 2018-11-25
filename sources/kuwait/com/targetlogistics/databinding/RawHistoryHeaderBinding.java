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
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.model.Value;

public class RawHistoryHeaderBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @Nullable
    private Value mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final TextView txtDate;

    public RawHistoryHeaderBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtDate = (TextView) bindings[1];
        this.txtDate.setTag(null);
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
        if (7 != variableId) {
            return false;
        }
        setData((Value) variable);
        return true;
    }

    public void setData(@Nullable Value Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public Value getData() {
        return this.mData;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Value data = this.mData;
        String dataDate = null;
        if (!((dirtyFlags & 3) == 0 || data == null)) {
            dataDate = data.getDate();
        }
        if ((dirtyFlags & 3) != 0) {
            TextViewBindingAdapter.setText(this.txtDate, dataDate);
        }
    }

    @NonNull
    public static RawHistoryHeaderBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawHistoryHeaderBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (RawHistoryHeaderBinding) DataBindingUtil.inflate(inflater, R.layout.raw_history_header, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static RawHistoryHeaderBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawHistoryHeaderBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.raw_history_header, null, false), bindingComponent);
    }

    @NonNull
    public static RawHistoryHeaderBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawHistoryHeaderBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/raw_history_header_0".equals(view.getTag())) {
            return new RawHistoryHeaderBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
