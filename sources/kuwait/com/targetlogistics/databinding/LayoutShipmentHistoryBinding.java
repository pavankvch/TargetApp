package kuwait.com.targetlogistics.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Biding;

public class LayoutShipmentHistoryBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TextView txtDelivery;
    @NonNull
    public final TextView txtExport;
    @NonNull
    public final TextView txtImport;

    public LayoutShipmentHistoryBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtDelivery = (TextView) bindings[3];
        this.txtDelivery.setTag(null);
        this.txtExport = (TextView) bindings[1];
        this.txtExport.setTag(null);
        this.txtImport = (TextView) bindings[2];
        this.txtImport.setTag(null);
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
        if ((1 & dirtyFlags) != 0) {
            Biding.loadLabel(this.txtDelivery, this.txtDelivery.getResources().getString(R.string.delivery));
            Biding.loadLabel(this.txtExport, this.txtExport.getResources().getString(R.string.export));
            Biding.loadLabel(this.txtImport, this.txtImport.getResources().getString(R.string.Import));
        }
    }

    @NonNull
    public static LayoutShipmentHistoryBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutShipmentHistoryBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (LayoutShipmentHistoryBinding) DataBindingUtil.inflate(inflater, R.layout.layout_shipment_history, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static LayoutShipmentHistoryBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutShipmentHistoryBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_shipment_history, null, false), bindingComponent);
    }

    @NonNull
    public static LayoutShipmentHistoryBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutShipmentHistoryBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/layout_shipment_history_0".equals(view.getTag())) {
            return new LayoutShipmentHistoryBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
