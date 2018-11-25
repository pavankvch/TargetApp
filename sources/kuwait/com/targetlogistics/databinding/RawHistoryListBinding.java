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
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.model.Value;

public class RawHistoryListBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView dashDelivery;
    @NonNull
    public final TextView dashDriver;
    @NonNull
    public final TextView dashOrderId;
    @NonNull
    public final TextView dashParcels;
    @NonNull
    public final TextView dashPickup;
    @NonNull
    public final TextView dashShipmentCost;
    @NonNull
    public final TextView dashWeight;
    @Nullable
    private Value mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final RelativeLayout relDelivery;
    @NonNull
    public final RelativeLayout relDriver;
    @NonNull
    public final RelativeLayout relParcel;
    @NonNull
    public final RelativeLayout relPendingShipmentCost;
    @NonNull
    public final RelativeLayout relWeight;
    @NonNull
    public final TextView txtCity;
    @NonNull
    public final TextView txtCityLabel;
    @NonNull
    public final TextView txtDelivery;
    @NonNull
    public final TextView txtDeliveryLabel;
    @NonNull
    public final TextView txtDriverLabel;
    @NonNull
    public final TextView txtDriverName;
    @NonNull
    public final TextView txtOrderId;
    @NonNull
    public final TextView txtOrderIdLabel;
    @NonNull
    public final TextView txtParcels;
    @NonNull
    public final TextView txtParcelsLabel;
    @NonNull
    public final TextView txtShipmentCost;
    @NonNull
    public final TextView txtShipmentCostLabel;
    @NonNull
    public final TextView txtWeight;
    @NonNull
    public final TextView txtWeightLabel;

    static {
        sViewsWithIds.put(R.id.txt_order_id_label, 7);
        sViewsWithIds.put(R.id.dash_order_id, 8);
        sViewsWithIds.put(R.id.txt_city_label, 9);
        sViewsWithIds.put(R.id.dash_pickup, 10);
        sViewsWithIds.put(R.id.txt_city, 11);
        sViewsWithIds.put(R.id.rel_delivery, 12);
        sViewsWithIds.put(R.id.txt_delivery_label, 13);
        sViewsWithIds.put(R.id.dash_delivery, 14);
        sViewsWithIds.put(R.id.rel_parcel, 15);
        sViewsWithIds.put(R.id.txt_parcels_label, 16);
        sViewsWithIds.put(R.id.dash_parcels, 17);
        sViewsWithIds.put(R.id.rel_weight, 18);
        sViewsWithIds.put(R.id.txt_weight_label, 19);
        sViewsWithIds.put(R.id.dash_weight, 20);
        sViewsWithIds.put(R.id.rel_pending_shipment_cost, 21);
        sViewsWithIds.put(R.id.txt_shipment_cost_label, 22);
        sViewsWithIds.put(R.id.dash_shipment_cost, 23);
        sViewsWithIds.put(R.id.rel_driver, 24);
        sViewsWithIds.put(R.id.txt_driver_label, 25);
        sViewsWithIds.put(R.id.dash_driver, 26);
    }

    public RawHistoryListBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds);
        this.dashDelivery = (TextView) bindings[14];
        this.dashDriver = (TextView) bindings[26];
        this.dashOrderId = (TextView) bindings[8];
        this.dashParcels = (TextView) bindings[17];
        this.dashPickup = (TextView) bindings[10];
        this.dashShipmentCost = (TextView) bindings[23];
        this.dashWeight = (TextView) bindings[20];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relDelivery = (RelativeLayout) bindings[12];
        this.relDriver = (RelativeLayout) bindings[24];
        this.relParcel = (RelativeLayout) bindings[15];
        this.relPendingShipmentCost = (RelativeLayout) bindings[21];
        this.relWeight = (RelativeLayout) bindings[18];
        this.txtCity = (TextView) bindings[11];
        this.txtCityLabel = (TextView) bindings[9];
        this.txtDelivery = (TextView) bindings[2];
        this.txtDelivery.setTag(null);
        this.txtDeliveryLabel = (TextView) bindings[13];
        this.txtDriverLabel = (TextView) bindings[25];
        this.txtDriverName = (TextView) bindings[6];
        this.txtDriverName.setTag(null);
        this.txtOrderId = (TextView) bindings[1];
        this.txtOrderId.setTag(null);
        this.txtOrderIdLabel = (TextView) bindings[7];
        this.txtParcels = (TextView) bindings[3];
        this.txtParcels.setTag(null);
        this.txtParcelsLabel = (TextView) bindings[16];
        this.txtShipmentCost = (TextView) bindings[5];
        this.txtShipmentCost.setTag(null);
        this.txtShipmentCostLabel = (TextView) bindings[22];
        this.txtWeight = (TextView) bindings[4];
        this.txtWeight.setTag(null);
        this.txtWeightLabel = (TextView) bindings[19];
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
        String dataDeliveryArea = null;
        String dataDriverName = null;
        String dataNoOfParcels = null;
        String dataShipmentCost = null;
        String dataIsDeliveryDataDeliveryAreaDataDeliveryCity = null;
        Value data = this.mData;
        String dataOrderId = null;
        String dataDeliveryCity = null;
        boolean dataIsDelivery = false;
        String dataWeight = null;
        if ((3 & dirtyFlags) != 0) {
            if (data != null) {
                dataDriverName = data.getDriverName();
                dataNoOfParcels = data.getNoOfParcels();
                dataShipmentCost = data.getShipmentCost();
                dataOrderId = data.getOrderId();
                dataIsDelivery = data.isDelivery();
                dataWeight = data.getWeight();
            }
            if ((3 & dirtyFlags) != 0) {
                dirtyFlags = dataIsDelivery ? dirtyFlags | 8 : dirtyFlags | 4;
            }
        }
        if (!((8 & dirtyFlags) == 0 || data == null)) {
            dataDeliveryArea = data.getDeliveryArea();
        }
        if (!((4 & dirtyFlags) == 0 || data == null)) {
            dataDeliveryCity = data.getDeliveryCity();
        }
        if ((3 & dirtyFlags) != 0) {
            dataIsDeliveryDataDeliveryAreaDataDeliveryCity = dataIsDelivery ? dataDeliveryArea : dataDeliveryCity;
        }
        if ((3 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.txtDelivery, dataIsDeliveryDataDeliveryAreaDataDeliveryCity);
            TextViewBindingAdapter.setText(this.txtDriverName, dataDriverName);
            TextViewBindingAdapter.setText(this.txtOrderId, dataOrderId);
            TextViewBindingAdapter.setText(this.txtParcels, dataNoOfParcels);
            TextViewBindingAdapter.setText(this.txtShipmentCost, dataShipmentCost);
            TextViewBindingAdapter.setText(this.txtWeight, dataWeight);
        }
    }

    @NonNull
    public static RawHistoryListBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawHistoryListBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (RawHistoryListBinding) DataBindingUtil.inflate(inflater, R.layout.raw_history_list, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static RawHistoryListBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawHistoryListBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.raw_history_list, null, false), bindingComponent);
    }

    @NonNull
    public static RawHistoryListBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawHistoryListBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/raw_history_list_0".equals(view.getTag())) {
            return new RawHistoryListBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
