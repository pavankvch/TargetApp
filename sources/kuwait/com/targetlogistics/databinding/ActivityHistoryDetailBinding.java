package kuwait.com.targetlogistics.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Biding;
import kuwait.com.targetlogistics.model.OrderData;
import kuwait.com.targetlogistics.model.ShipmentData;

public class ActivityHistoryDetailBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final ImageView imgCopyToClipboard;
    @NonNull
    public final LinearLayout linButtons;
    @NonNull
    public final LinearLayout linDate;
    @NonNull
    public final LinearLayout linDelivery;
    @NonNull
    public final LinearLayout linOrderDate;
    @NonNull
    public final LinearLayout linOrederId;
    @NonNull
    public final LinearLayout linShipmentId;
    @NonNull
    public final LinearLayout linShipmentInfo;
    @NonNull
    public final LinearLayout linStatus;
    @NonNull
    public final LinearLayout linTotalCost;
    @NonNull
    public final LinearLayout linVehicleInfo;
    @NonNull
    public final LinearLayout linWeight;
    @Nullable
    private OrderData mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final RecyclerView recParcelInfo;
    @NonNull
    public final LinearLayout relDelivery;
    @NonNull
    public final TextView txtAddress;
    @NonNull
    public final TextView txtAddress1;
    @NonNull
    public final TextView txtCancelOrder;
    @NonNull
    public final TextView txtCity;
    @NonNull
    public final TextView txtCityLabel;
    @NonNull
    public final TextView txtColon;
    @NonNull
    public final TextView txtCompanyName;
    @NonNull
    public final TextView txtCountry;
    @NonNull
    public final TextView txtCountryLabel;
    @NonNull
    public final TextView txtDateLabel;
    @NonNull
    public final TextView txtDateTime;
    @NonNull
    public final TextView txtDelAddress1;
    @NonNull
    public final TextView txtDelAddress1Label;
    @NonNull
    public final TextView txtDelArea;
    @NonNull
    public final TextView txtDelAreaLabel;
    @NonNull
    public final TextView txtDelFullName;
    @NonNull
    public final TextView txtDelFullNameLabel;
    @NonNull
    public final TextView txtDelPhone;
    @NonNull
    public final TextView txtDelPhoneLabel;
    @NonNull
    public final TextView txtDeliveryAddress;
    @NonNull
    public final TextView txtDeliveryInProgress;
    @NonNull
    public final TextView txtFullName;
    @NonNull
    public final TextView txtOrderDateLabel;
    @NonNull
    public final TextView txtOrderDateTime;
    @NonNull
    public final TextView txtOrderId;
    @NonNull
    public final TextView txtOrderIdLabel;
    @NonNull
    public final TextView txtParcelInfo;
    @NonNull
    public final TextView txtPhone;
    @NonNull
    public final TextView txtPhoneLabel;
    @NonNull
    public final TextView txtPhoneNo;
    @NonNull
    public final TextView txtShipmentId;
    @NonNull
    public final TextView txtShipmentIdLable;
    @NonNull
    public final TextView txtStatus;
    @NonNull
    public final TextView txtStatusLabel;
    @NonNull
    public final TextView txtTotalCost;
    @NonNull
    public final TextView txtTotalCostLabel;
    @NonNull
    public final TextView txtTotalWeight;
    @NonNull
    public final TextView txtTotalWeightLabel;
    @NonNull
    public final TextView txtVehicleType;
    @NonNull
    public final TextView txtVehicleTypeLabel;

    static {
        sViewsWithIds.put(R.id.txt_colon, 20);
        sViewsWithIds.put(R.id.lin_delivery, 21);
        sViewsWithIds.put(R.id.txt_country_label, 22);
        sViewsWithIds.put(R.id.txt_city_label, 23);
        sViewsWithIds.put(R.id.rel_delivery, 24);
        sViewsWithIds.put(R.id.txt_delivery_address, 25);
        sViewsWithIds.put(R.id.txt_del_full_name_label, 26);
        sViewsWithIds.put(R.id.txt_del_phone_label, 27);
        sViewsWithIds.put(R.id.txt_del_area_label, 28);
        sViewsWithIds.put(R.id.txt_del_address1_label, 29);
        sViewsWithIds.put(R.id.txt_parcel_info, 30);
        sViewsWithIds.put(R.id.rec_parcel_info, 31);
        sViewsWithIds.put(R.id.lin_oreder_id, 32);
        sViewsWithIds.put(R.id.txt_order_id_label, 33);
        sViewsWithIds.put(R.id.lin_order_date, 34);
        sViewsWithIds.put(R.id.txt_order_date_label, 35);
        sViewsWithIds.put(R.id.txt_order_date_time, 36);
        sViewsWithIds.put(R.id.lin_date, 37);
        sViewsWithIds.put(R.id.txt_date_label, 38);
        sViewsWithIds.put(R.id.txt_date_time, 39);
        sViewsWithIds.put(R.id.lin_total_cost, 40);
        sViewsWithIds.put(R.id.txt_total_cost_label, 41);
        sViewsWithIds.put(R.id.lin_status, 42);
        sViewsWithIds.put(R.id.txt_status_label, 43);
        sViewsWithIds.put(R.id.lin_weight, 44);
        sViewsWithIds.put(R.id.txt_total_weight_label, 45);
        sViewsWithIds.put(R.id.lin_vehicle_info, 46);
        sViewsWithIds.put(R.id.txt_vehicle_type_label, 47);
        sViewsWithIds.put(R.id.lin_shipment_info, 48);
        sViewsWithIds.put(R.id.lin_shipment_id, 49);
        sViewsWithIds.put(R.id.txt_shipment_id_lable, 50);
        sViewsWithIds.put(R.id.img_copy_to_clipboard, 51);
        sViewsWithIds.put(R.id.lin_buttons, 52);
        sViewsWithIds.put(R.id.txt_delivery_in_progress, 53);
        sViewsWithIds.put(R.id.txt_cancel_order, 54);
    }

    public ActivityHistoryDetailBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 55, sIncludes, sViewsWithIds);
        this.imgCopyToClipboard = (ImageView) bindings[51];
        this.linButtons = (LinearLayout) bindings[52];
        this.linDate = (LinearLayout) bindings[37];
        this.linDelivery = (LinearLayout) bindings[21];
        this.linOrderDate = (LinearLayout) bindings[34];
        this.linOrederId = (LinearLayout) bindings[32];
        this.linShipmentId = (LinearLayout) bindings[49];
        this.linShipmentInfo = (LinearLayout) bindings[48];
        this.linStatus = (LinearLayout) bindings[42];
        this.linTotalCost = (LinearLayout) bindings[40];
        this.linVehicleInfo = (LinearLayout) bindings[46];
        this.linWeight = (LinearLayout) bindings[44];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recParcelInfo = (RecyclerView) bindings[31];
        this.relDelivery = (LinearLayout) bindings[24];
        this.txtAddress = (TextView) bindings[1];
        this.txtAddress.setTag(null);
        this.txtAddress1 = (TextView) bindings[8];
        this.txtAddress1.setTag(null);
        this.txtCancelOrder = (TextView) bindings[54];
        this.txtCity = (TextView) bindings[7];
        this.txtCity.setTag(null);
        this.txtCityLabel = (TextView) bindings[23];
        this.txtColon = (TextView) bindings[20];
        this.txtCompanyName = (TextView) bindings[18];
        this.txtCompanyName.setTag(null);
        this.txtCountry = (TextView) bindings[6];
        this.txtCountry.setTag(null);
        this.txtCountryLabel = (TextView) bindings[22];
        this.txtDateLabel = (TextView) bindings[38];
        this.txtDateTime = (TextView) bindings[39];
        this.txtDelAddress1 = (TextView) bindings[12];
        this.txtDelAddress1.setTag(null);
        this.txtDelAddress1Label = (TextView) bindings[29];
        this.txtDelArea = (TextView) bindings[11];
        this.txtDelArea.setTag(null);
        this.txtDelAreaLabel = (TextView) bindings[28];
        this.txtDelFullName = (TextView) bindings[9];
        this.txtDelFullName.setTag(null);
        this.txtDelFullNameLabel = (TextView) bindings[26];
        this.txtDelPhone = (TextView) bindings[10];
        this.txtDelPhone.setTag(null);
        this.txtDelPhoneLabel = (TextView) bindings[27];
        this.txtDeliveryAddress = (TextView) bindings[25];
        this.txtDeliveryInProgress = (TextView) bindings[53];
        this.txtFullName = (TextView) bindings[4];
        this.txtFullName.setTag(null);
        this.txtOrderDateLabel = (TextView) bindings[35];
        this.txtOrderDateTime = (TextView) bindings[36];
        this.txtOrderId = (TextView) bindings[13];
        this.txtOrderId.setTag(null);
        this.txtOrderIdLabel = (TextView) bindings[33];
        this.txtParcelInfo = (TextView) bindings[30];
        this.txtPhone = (TextView) bindings[5];
        this.txtPhone.setTag(null);
        this.txtPhoneLabel = (TextView) bindings[2];
        this.txtPhoneLabel.setTag(null);
        this.txtPhoneNo = (TextView) bindings[3];
        this.txtPhoneNo.setTag(null);
        this.txtShipmentId = (TextView) bindings[19];
        this.txtShipmentId.setTag(null);
        this.txtShipmentIdLable = (TextView) bindings[50];
        this.txtStatus = (TextView) bindings[15];
        this.txtStatus.setTag(null);
        this.txtStatusLabel = (TextView) bindings[43];
        this.txtTotalCost = (TextView) bindings[14];
        this.txtTotalCost.setTag(null);
        this.txtTotalCostLabel = (TextView) bindings[41];
        this.txtTotalWeight = (TextView) bindings[16];
        this.txtTotalWeight.setTag(null);
        this.txtTotalWeightLabel = (TextView) bindings[45];
        this.txtVehicleType = (TextView) bindings[17];
        this.txtVehicleType.setTag(null);
        this.txtVehicleTypeLabel = (TextView) bindings[47];
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
        setData((OrderData) variable);
        return true;
    }

    public void setData(@Nullable OrderData Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public OrderData getData() {
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
        String dataOrderStatusText = null;
        String dataShippingCostJavaLangStringTxtTotalCostAndroidStringPriceUnit = null;
        String dataDeliveryCountry = null;
        String dataTotalWeightJavaLangStringTxtTotalWeightAndroidStringWeightUnit = null;
        String dataVehicleName = null;
        String dataGetShipmentDataShipmentReference = null;
        ShipmentData dataGetShipmentData = null;
        String dataGetShipmentDataShipmentCompany = null;
        String dataDeliveryPhone = null;
        OrderData data = this.mData;
        String dataPickupPhoneNo = null;
        String dataOrderId = null;
        String dataDeliveryCity = null;
        String dataShippingCost = null;
        String dataTotalWeight = null;
        String dataDeliveryFullName = null;
        String dataAddress = null;
        String dataDeliveryAddress1 = null;
        if ((3 & dirtyFlags) != 0) {
            if (data != null) {
                dataOrderStatusText = data.getOrderStatusText();
                dataDeliveryCountry = data.getDeliveryCountry();
                dataVehicleName = data.getVehicleName();
                dataGetShipmentData = data.getShipmentData();
                dataDeliveryPhone = data.getDeliveryPhone();
                dataPickupPhoneNo = data.getPickupPhoneNo();
                dataOrderId = data.getOrderId();
                dataDeliveryCity = data.getDeliveryCity();
                dataShippingCost = data.getShippingCost();
                dataTotalWeight = data.getTotalWeight();
                dataDeliveryFullName = data.getDeliveryFullName();
                dataAddress = data.getAddress();
                dataDeliveryAddress1 = data.getDeliveryAddress1();
            }
            if (dataGetShipmentData != null) {
                dataGetShipmentDataShipmentReference = dataGetShipmentData.getShipmentReference();
                dataGetShipmentDataShipmentCompany = dataGetShipmentData.getShipmentCompany();
            }
            String dataShippingCostJavaLangString = dataShippingCost + " ";
            String dataTotalWeightJavaLangString = dataTotalWeight + " ";
            dataShippingCostJavaLangStringTxtTotalCostAndroidStringPriceUnit = dataShippingCostJavaLangString + this.txtTotalCost.getResources().getString(R.string.price_unit);
            dataTotalWeightJavaLangStringTxtTotalWeightAndroidStringWeightUnit = dataTotalWeightJavaLangString + this.txtTotalWeight.getResources().getString(R.string.weight_unit);
        }
        if ((3 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.txtAddress, dataAddress);
            TextViewBindingAdapter.setText(this.txtAddress1, dataDeliveryAddress1);
            TextViewBindingAdapter.setText(this.txtCity, dataDeliveryCity);
            TextViewBindingAdapter.setText(this.txtCompanyName, dataGetShipmentDataShipmentCompany);
            TextViewBindingAdapter.setText(this.txtCountry, dataDeliveryCountry);
            TextViewBindingAdapter.setText(this.txtDelAddress1, dataDeliveryAddress1);
            TextViewBindingAdapter.setText(this.txtDelArea, dataDeliveryCity);
            TextViewBindingAdapter.setText(this.txtDelFullName, dataDeliveryFullName);
            TextViewBindingAdapter.setText(this.txtDelPhone, dataDeliveryPhone);
            TextViewBindingAdapter.setText(this.txtFullName, dataDeliveryFullName);
            TextViewBindingAdapter.setText(this.txtOrderId, dataOrderId);
            TextViewBindingAdapter.setText(this.txtPhone, dataDeliveryPhone);
            TextViewBindingAdapter.setText(this.txtPhoneNo, dataPickupPhoneNo);
            TextViewBindingAdapter.setText(this.txtShipmentId, dataGetShipmentDataShipmentReference);
            TextViewBindingAdapter.setText(this.txtStatus, dataOrderStatusText);
            TextViewBindingAdapter.setText(this.txtTotalCost, dataShippingCostJavaLangStringTxtTotalCostAndroidStringPriceUnit);
            TextViewBindingAdapter.setText(this.txtTotalWeight, dataTotalWeightJavaLangStringTxtTotalWeightAndroidStringWeightUnit);
            TextViewBindingAdapter.setText(this.txtVehicleType, dataVehicleName);
        }
        if ((2 & dirtyFlags) != 0) {
            Biding.loadLabel(this.txtPhoneLabel, this.txtPhoneLabel.getResources().getString(R.string.phone));
        }
    }

    @NonNull
    public static ActivityHistoryDetailBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityHistoryDetailBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityHistoryDetailBinding) DataBindingUtil.inflate(inflater, R.layout.activity_history_detail, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityHistoryDetailBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityHistoryDetailBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_history_detail, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityHistoryDetailBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityHistoryDetailBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_history_detail_0".equals(view.getTag())) {
            return new ActivityHistoryDetailBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
