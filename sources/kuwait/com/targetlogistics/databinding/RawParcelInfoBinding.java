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
import kuwait.com.targetlogistics.model.Parcel;

public class RawParcelInfoBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView edtLength;
    @NonNull
    public final TextView layout1;
    @NonNull
    public final LinearLayout linMain;
    @Nullable
    private Parcel mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final TextView mboundView7;
    @NonNull
    private final TextView mboundView8;
    @NonNull
    public final TextView textDescriptionLabel;
    @NonNull
    public final TextView txtCostValue;
    @NonNull
    public final TextView txtParcelDescription;
    @NonNull
    public final TextView txtParcelType;
    @NonNull
    public final TextView txtQuantity;
    @NonNull
    public final TextView txtWeight;
    @NonNull
    public final View viewDescription;

    static {
        sViewsWithIds.put(R.id.lin_main, 9);
        sViewsWithIds.put(R.id.text_description_label, 10);
        sViewsWithIds.put(R.id.view_description, 11);
        sViewsWithIds.put(R.id.layout1, 12);
    }

    public RawParcelInfoBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.edtLength = (TextView) bindings[6];
        this.edtLength.setTag(null);
        this.layout1 = (TextView) bindings[12];
        this.linMain = (LinearLayout) bindings[9];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView7 = (TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.textDescriptionLabel = (TextView) bindings[10];
        this.txtCostValue = (TextView) bindings[3];
        this.txtCostValue.setTag(null);
        this.txtParcelDescription = (TextView) bindings[5];
        this.txtParcelDescription.setTag(null);
        this.txtParcelType = (TextView) bindings[1];
        this.txtParcelType.setTag(null);
        this.txtQuantity = (TextView) bindings[2];
        this.txtQuantity.setTag(null);
        this.txtWeight = (TextView) bindings[4];
        this.txtWeight.setTag(null);
        this.viewDescription = (View) bindings[11];
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
        setData((Parcel) variable);
        return true;
    }

    public void setData(@Nullable Parcel Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public Parcel getData() {
        return this.mData;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeData((Parcel) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeData(Parcel Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String dataParcelType = null;
        boolean dataWeightOfParcelEqualsJavaLangString = false;
        boolean dataLengthEqualsJavaLangString = false;
        boolean DataLengthEqualsJavaLangString1 = false;
        String dataQuantity = null;
        boolean DataWeightOfParcelEqualsJavaLangString1 = false;
        String dataHeightJavaLangStringMboundView8AndroidStringCmUnit = null;
        boolean dataHeightEqualsJavaLangString = false;
        boolean dataWidthEqualsJavaLangString = false;
        String dataHeightEqualsJavaLangStringDataHeightJavaLangStringMboundView8AndroidStringCmUnitDataHeight = null;
        String dataCostValue = null;
        String dataLengthEqualsJavaLangStringDataLengthJavaLangStringEdtLengthAndroidStringCmUnitDataLength = null;
        String dataWeightOfParcelEqualsJavaLangStringDataWeightOfParcelJavaLangStringTxtWeightAndroidStringWeightUnitDataWeightOfParcel = null;
        boolean DataHeightEqualsJavaLangString1 = false;
        Parcel data = this.mData;
        String dataParcelDetailDescription = null;
        String dataWeightOfParcelJavaLangStringTxtWeightAndroidStringWeightUnit = null;
        String dataCostValueJavaLangStringTxtCostValueAndroidStringPriceUnit = null;
        boolean DataWidthEqualsJavaLangString1 = false;
        String dataHeight = null;
        String dataLength = null;
        String dataLengthJavaLangStringEdtLengthAndroidStringCmUnit = null;
        String dataWidthJavaLangStringMboundView7AndroidStringCmUnit = null;
        String dataWeightOfParcel = null;
        String dataWidthEqualsJavaLangStringDataWidthJavaLangStringMboundView7AndroidStringCmUnitDataWidth = null;
        String dataWidth = null;
        if ((3 & dirtyFlags) != 0) {
            if (data != null) {
                dataParcelType = data.getParcelType();
                dataQuantity = data.getQuantity();
                dataCostValue = data.getCostValue();
                dataParcelDetailDescription = data.getParcelDetailDescription();
                dataHeight = data.getHeight();
                dataLength = data.getLength();
                dataWeightOfParcel = data.getWeightOfParcel();
                dataWidth = data.getWidth();
            }
            String dataCostValueJavaLangString = dataCostValue + " ";
            if (dataHeight != null) {
                DataHeightEqualsJavaLangString1 = dataHeight.equals("");
            }
            if (dataLength != null) {
                dataLengthEqualsJavaLangString = dataLength.equals("");
            }
            if (dataWeightOfParcel != null) {
                DataWeightOfParcelEqualsJavaLangString1 = dataWeightOfParcel.equals("");
            }
            if (dataWidth != null) {
                dataWidthEqualsJavaLangString = dataWidth.equals("");
            }
            dataCostValueJavaLangStringTxtCostValueAndroidStringPriceUnit = dataCostValueJavaLangString + this.txtCostValue.getResources().getString(R.string.price_unit);
            dataHeightEqualsJavaLangString = !DataHeightEqualsJavaLangString1;
            DataLengthEqualsJavaLangString1 = !dataLengthEqualsJavaLangString;
            dataWeightOfParcelEqualsJavaLangString = !DataWeightOfParcelEqualsJavaLangString1;
            DataWidthEqualsJavaLangString1 = !dataWidthEqualsJavaLangString;
            if ((3 & dirtyFlags) != 0) {
                if (dataHeightEqualsJavaLangString) {
                    dirtyFlags |= 8;
                } else {
                    dirtyFlags |= 4;
                }
            }
            if ((3 & dirtyFlags) != 0) {
                if (DataLengthEqualsJavaLangString1) {
                    dirtyFlags |= 32;
                } else {
                    dirtyFlags |= 16;
                }
            }
            if ((3 & dirtyFlags) != 0) {
                if (dataWeightOfParcelEqualsJavaLangString) {
                    dirtyFlags |= 128;
                } else {
                    dirtyFlags |= 64;
                }
            }
            if ((3 & dirtyFlags) != 0) {
                if (DataWidthEqualsJavaLangString1) {
                    dirtyFlags |= 512;
                } else {
                    dirtyFlags |= 256;
                }
            }
        }
        if ((128 & dirtyFlags) != 0) {
            dataWeightOfParcelJavaLangStringTxtWeightAndroidStringWeightUnit = (dataWeightOfParcel + " ") + this.txtWeight.getResources().getString(R.string.weight_unit);
        }
        if ((8 & dirtyFlags) != 0) {
            dataHeightJavaLangStringMboundView8AndroidStringCmUnit = (dataHeight + " ") + this.mboundView8.getResources().getString(R.string.cm_unit);
        }
        if ((32 & dirtyFlags) != 0) {
            dataLengthJavaLangStringEdtLengthAndroidStringCmUnit = (dataLength + " ") + this.edtLength.getResources().getString(R.string.cm_unit);
        }
        if ((512 & dirtyFlags) != 0) {
            dataWidthJavaLangStringMboundView7AndroidStringCmUnit = (dataWidth + " ") + this.mboundView7.getResources().getString(R.string.cm_unit);
        }
        if ((3 & dirtyFlags) != 0) {
            dataHeightEqualsJavaLangStringDataHeightJavaLangStringMboundView8AndroidStringCmUnitDataHeight = dataHeightEqualsJavaLangString ? dataHeightJavaLangStringMboundView8AndroidStringCmUnit : dataHeight;
            if (DataLengthEqualsJavaLangString1) {
                dataLengthEqualsJavaLangStringDataLengthJavaLangStringEdtLengthAndroidStringCmUnitDataLength = dataLengthJavaLangStringEdtLengthAndroidStringCmUnit;
            } else {
                dataLengthEqualsJavaLangStringDataLengthJavaLangStringEdtLengthAndroidStringCmUnitDataLength = dataLength;
            }
            if (dataWeightOfParcelEqualsJavaLangString) {
                dataWeightOfParcelEqualsJavaLangStringDataWeightOfParcelJavaLangStringTxtWeightAndroidStringWeightUnitDataWeightOfParcel = dataWeightOfParcelJavaLangStringTxtWeightAndroidStringWeightUnit;
            } else {
                dataWeightOfParcelEqualsJavaLangStringDataWeightOfParcelJavaLangStringTxtWeightAndroidStringWeightUnitDataWeightOfParcel = dataWeightOfParcel;
            }
            if (DataWidthEqualsJavaLangString1) {
                dataWidthEqualsJavaLangStringDataWidthJavaLangStringMboundView7AndroidStringCmUnitDataWidth = dataWidthJavaLangStringMboundView7AndroidStringCmUnit;
            } else {
                dataWidthEqualsJavaLangStringDataWidthJavaLangStringMboundView7AndroidStringCmUnitDataWidth = dataWidth;
            }
        }
        if ((3 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtLength, dataLengthEqualsJavaLangStringDataLengthJavaLangStringEdtLengthAndroidStringCmUnitDataLength);
            TextViewBindingAdapter.setText(this.mboundView7, dataWidthEqualsJavaLangStringDataWidthJavaLangStringMboundView7AndroidStringCmUnitDataWidth);
            TextViewBindingAdapter.setText(this.mboundView8, dataHeightEqualsJavaLangStringDataHeightJavaLangStringMboundView8AndroidStringCmUnitDataHeight);
            TextViewBindingAdapter.setText(this.txtCostValue, dataCostValueJavaLangStringTxtCostValueAndroidStringPriceUnit);
            TextViewBindingAdapter.setText(this.txtParcelDescription, dataParcelDetailDescription);
            TextViewBindingAdapter.setText(this.txtParcelType, dataParcelType);
            TextViewBindingAdapter.setText(this.txtQuantity, dataQuantity);
            TextViewBindingAdapter.setText(this.txtWeight, dataWeightOfParcelEqualsJavaLangStringDataWeightOfParcelJavaLangStringTxtWeightAndroidStringWeightUnitDataWeightOfParcel);
        }
    }

    @NonNull
    public static RawParcelInfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawParcelInfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (RawParcelInfoBinding) DataBindingUtil.inflate(inflater, R.layout.raw_parcel_info, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static RawParcelInfoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawParcelInfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.raw_parcel_info, null, false), bindingComponent);
    }

    @NonNull
    public static RawParcelInfoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawParcelInfoBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/raw_parcel_info_0".equals(view.getTag())) {
            return new RawParcelInfoBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
