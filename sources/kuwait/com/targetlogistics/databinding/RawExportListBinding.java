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
import kuwait.com.targetlogistics.model.HistoryList;

public class RawExportListBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView dashDelivery;
    @NonNull
    public final TextView dashParcels;
    @NonNull
    public final TextView dashPickup;
    @NonNull
    public final TextView dashWeight;
    @NonNull
    public final ImageView imgDelete;
    @NonNull
    public final ImageView imgEdit;
    @Nullable
    private HistoryList mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final TextView txtCity;
    @NonNull
    public final TextView txtCityLabel;
    @NonNull
    public final TextView txtDelivery;
    @NonNull
    public final TextView txtDeliveryLabel;
    @NonNull
    public final TextView txtParcels;
    @NonNull
    public final TextView txtParcelsLabel;
    @NonNull
    public final TextView txtWeight;
    @NonNull
    public final TextView txtWeightLabel;

    static {
        sViewsWithIds.put(R.id.txt_city_label, 5);
        sViewsWithIds.put(R.id.dash_pickup, 6);
        sViewsWithIds.put(R.id.img_edit, 7);
        sViewsWithIds.put(R.id.txt_delivery_label, 8);
        sViewsWithIds.put(R.id.dash_delivery, 9);
        sViewsWithIds.put(R.id.txt_parcels_label, 10);
        sViewsWithIds.put(R.id.dash_parcels, 11);
        sViewsWithIds.put(R.id.txt_weight_label, 12);
        sViewsWithIds.put(R.id.dash_weight, 13);
        sViewsWithIds.put(R.id.img_delete, 14);
    }

    public RawExportListBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds);
        this.dashDelivery = (TextView) bindings[9];
        this.dashParcels = (TextView) bindings[11];
        this.dashPickup = (TextView) bindings[6];
        this.dashWeight = (TextView) bindings[13];
        this.imgDelete = (ImageView) bindings[14];
        this.imgEdit = (ImageView) bindings[7];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtCity = (TextView) bindings[1];
        this.txtCity.setTag(null);
        this.txtCityLabel = (TextView) bindings[5];
        this.txtDelivery = (TextView) bindings[2];
        this.txtDelivery.setTag(null);
        this.txtDeliveryLabel = (TextView) bindings[8];
        this.txtParcels = (TextView) bindings[3];
        this.txtParcels.setTag(null);
        this.txtParcelsLabel = (TextView) bindings[10];
        this.txtWeight = (TextView) bindings[4];
        this.txtWeight.setTag(null);
        this.txtWeightLabel = (TextView) bindings[12];
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
        setData((HistoryList) variable);
        return true;
    }

    public void setData(@Nullable HistoryList Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public HistoryList getData() {
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
        String dataNoOfParcels = null;
        String dataPickupCity = null;
        HistoryList data = this.mData;
        String dataDeliveryCity = null;
        String dataWeight = null;
        if (!((dirtyFlags & 3) == 0 || data == null)) {
            dataNoOfParcels = data.getNoOfParcels();
            dataPickupCity = data.getPickupCity();
            dataDeliveryCity = data.getDeliveryCity();
            dataWeight = data.getWeight();
        }
        if ((dirtyFlags & 3) != 0) {
            TextViewBindingAdapter.setText(this.txtCity, dataPickupCity);
            TextViewBindingAdapter.setText(this.txtDelivery, dataDeliveryCity);
            TextViewBindingAdapter.setText(this.txtParcels, dataNoOfParcels);
            TextViewBindingAdapter.setText(this.txtWeight, dataWeight);
        }
    }

    @NonNull
    public static RawExportListBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawExportListBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (RawExportListBinding) DataBindingUtil.inflate(inflater, R.layout.raw_export_list, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static RawExportListBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawExportListBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.raw_export_list, null, false), bindingComponent);
    }

    @NonNull
    public static RawExportListBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawExportListBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/raw_export_list_0".equals(view.getTag())) {
            return new RawExportListBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
