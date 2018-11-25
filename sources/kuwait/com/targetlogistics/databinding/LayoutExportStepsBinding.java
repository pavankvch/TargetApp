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

public class LayoutExportStepsBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final LinearLayout linStep1;
    private long mDirtyFlags = -1;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TextView txtDeliveryAddress;
    @NonNull
    public final TextView txtParcelInfo;
    @NonNull
    public final TextView txtParcelInfoLabel;
    @NonNull
    public final TextView txtPickUpAddress;
    @NonNull
    public final TextView txtPickupDateTime;
    @NonNull
    public final TextView txtStep3;

    static {
        sViewsWithIds.put(R.id.lin_step1, 1);
        sViewsWithIds.put(R.id.txt_pick_up_address, 2);
        sViewsWithIds.put(R.id.txt_pickup_date_time, 3);
        sViewsWithIds.put(R.id.txt_delivery_address, 4);
        sViewsWithIds.put(R.id.txt_step3, 5);
        sViewsWithIds.put(R.id.txt_parcel_info_label, 6);
        sViewsWithIds.put(R.id.txt_parcel_info, 7);
    }

    public LayoutExportStepsBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.linStep1 = (LinearLayout) bindings[1];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtDeliveryAddress = (TextView) bindings[4];
        this.txtParcelInfo = (TextView) bindings[7];
        this.txtParcelInfoLabel = (TextView) bindings[6];
        this.txtPickUpAddress = (TextView) bindings[2];
        this.txtPickupDateTime = (TextView) bindings[3];
        this.txtStep3 = (TextView) bindings[5];
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
    public static LayoutExportStepsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutExportStepsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (LayoutExportStepsBinding) DataBindingUtil.inflate(inflater, R.layout.layout_export_steps, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static LayoutExportStepsBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutExportStepsBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_export_steps, null, false), bindingComponent);
    }

    @NonNull
    public static LayoutExportStepsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutExportStepsBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/layout_export_steps_0".equals(view.getTag())) {
            return new LayoutExportStepsBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
