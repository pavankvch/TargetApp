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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.model.VehicleType;

public class RawVehicleTypeBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final ImageView cbVehicleType;
    @Nullable
    private VehicleType mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final TextView mboundView1;
    @NonNull
    public final LinearLayout relCheckbox;

    static {
        sViewsWithIds.put(R.id.cb_vehicle_type, 2);
    }

    public RawVehicleTypeBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.cbVehicleType = (ImageView) bindings[2];
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.relCheckbox = (LinearLayout) bindings[0];
        this.relCheckbox.setTag(null);
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
        setData((VehicleType) variable);
        return true;
    }

    public void setData(@Nullable VehicleType Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public VehicleType getData() {
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
        String dataVehicleName = null;
        VehicleType data = this.mData;
        if (!((dirtyFlags & 3) == 0 || data == null)) {
            dataVehicleName = data.getVehicleName();
        }
        if ((dirtyFlags & 3) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, dataVehicleName);
        }
    }

    @NonNull
    public static RawVehicleTypeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawVehicleTypeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (RawVehicleTypeBinding) DataBindingUtil.inflate(inflater, R.layout.raw_vehicle_type, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static RawVehicleTypeBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawVehicleTypeBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.raw_vehicle_type, null, false), bindingComponent);
    }

    @NonNull
    public static RawVehicleTypeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawVehicleTypeBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/raw_vehicle_type_0".equals(view.getTag())) {
            return new RawVehicleTypeBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
