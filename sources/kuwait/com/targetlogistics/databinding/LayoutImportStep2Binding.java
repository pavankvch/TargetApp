package kuwait.com.targetlogistics.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingListener;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.OnTextChanged;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.model.Parcel;

public class LayoutImportStep2Binding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView btnAddMoreParcel;
    @NonNull
    public final TextView btnRemoveParcel;
    @NonNull
    public final EditText edtCostValue;
    private InverseBindingListener edtCostValueandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(LayoutImportStep2Binding.this.edtCostValue);
            Parcel data = LayoutImportStep2Binding.this.mData;
            if (data != null) {
                Parcel dataSetCostValueJavaLangStringCallbackArg0 = data.setCostValue(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtHeight;
    private InverseBindingListener edtHeightandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(LayoutImportStep2Binding.this.edtHeight);
            Parcel data = LayoutImportStep2Binding.this.mData;
            if (data != null) {
                data.setHeight(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtLength;
    private InverseBindingListener edtLengthandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(LayoutImportStep2Binding.this.edtLength);
            Parcel data = LayoutImportStep2Binding.this.mData;
            if (data != null) {
                data.setLength(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtParcelDetailsDescription;
    private InverseBindingListener edtParcelDetailsDescriptionandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(LayoutImportStep2Binding.this.edtParcelDetailsDescription);
            Parcel data = LayoutImportStep2Binding.this.mData;
            if (data != null) {
                Parcel dataSetParcelDetailDescriptionJavaLangStringCallbackArg0 = data.setParcelDetailDescription(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtQuantity;
    private InverseBindingListener edtQuantityandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(LayoutImportStep2Binding.this.edtQuantity);
            Parcel data = LayoutImportStep2Binding.this.mData;
            if (data != null) {
                Parcel dataSetQuantityJavaLangStringCallbackArg0 = data.setQuantity(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtWeight;
    private InverseBindingListener edtWeightandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(LayoutImportStep2Binding.this.edtWeight);
            Parcel data = LayoutImportStep2Binding.this.mData;
            if (data != null) {
                Parcel dataSetWeightOfParcelJavaLangStringCallbackArg0 = data.setWeightOfParcel(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtWidth;
    private InverseBindingListener edtWidthandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(LayoutImportStep2Binding.this.edtWidth);
            Parcel data = LayoutImportStep2Binding.this.mData;
            if (data != null) {
                data.setWidth(callbackArg_0);
            }
        }
    };
    @NonNull
    public final TextInputLayout layout1;
    @NonNull
    public final TextInputLayout layout2;
    @NonNull
    public final TextInputLayout layout3;
    @NonNull
    public final LinearLayout linMain;
    @Nullable
    private Parcel mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    public final RelativeLayout relParcelType;
    @NonNull
    public final TextInputLayout tILCostValue;
    @NonNull
    public final TextInputLayout tILParcelDetailsDescription;
    @NonNull
    public final TextInputLayout tILQuantity;
    @NonNull
    public final TextInputLayout tILWeight;
    @NonNull
    public final TextView txtParcelType;

    static {
        sViewsWithIds.put(R.id.lin_main, 9);
        sViewsWithIds.put(R.id.rel_parcel_type, 10);
        sViewsWithIds.put(R.id.tILQuantity, 11);
        sViewsWithIds.put(R.id.tILCostValue, 12);
        sViewsWithIds.put(R.id.tILWeight, 13);
        sViewsWithIds.put(R.id.tILParcelDetailsDescription, 14);
        sViewsWithIds.put(R.id.layout1, 15);
        sViewsWithIds.put(R.id.layout2, 16);
        sViewsWithIds.put(R.id.layout3, 17);
        sViewsWithIds.put(R.id.btn_add_more_parcel, 18);
        sViewsWithIds.put(R.id.btn_remove_parcel, 19);
    }

    public LayoutImportStep2Binding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds);
        this.btnAddMoreParcel = (TextView) bindings[18];
        this.btnRemoveParcel = (TextView) bindings[19];
        this.edtCostValue = (EditText) bindings[3];
        this.edtCostValue.setTag(null);
        this.edtHeight = (EditText) bindings[8];
        this.edtHeight.setTag(null);
        this.edtLength = (EditText) bindings[6];
        this.edtLength.setTag(null);
        this.edtParcelDetailsDescription = (EditText) bindings[5];
        this.edtParcelDetailsDescription.setTag(null);
        this.edtQuantity = (EditText) bindings[2];
        this.edtQuantity.setTag(null);
        this.edtWeight = (EditText) bindings[4];
        this.edtWeight.setTag(null);
        this.edtWidth = (EditText) bindings[7];
        this.edtWidth.setTag(null);
        this.layout1 = (TextInputLayout) bindings[15];
        this.layout2 = (TextInputLayout) bindings[16];
        this.layout3 = (TextInputLayout) bindings[17];
        this.linMain = (LinearLayout) bindings[9];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag("1");
        this.relParcelType = (RelativeLayout) bindings[10];
        this.tILCostValue = (TextInputLayout) bindings[12];
        this.tILParcelDetailsDescription = (TextInputLayout) bindings[14];
        this.tILQuantity = (TextInputLayout) bindings[11];
        this.tILWeight = (TextInputLayout) bindings[13];
        this.txtParcelType = (TextView) bindings[1];
        this.txtParcelType.setTag(null);
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
        String dataQuantity = null;
        String dataCostValue = null;
        Parcel data = this.mData;
        String dataParcelDetailDescription = null;
        String dataHeight = null;
        String dataLength = null;
        String dataWeightOfParcel = null;
        String dataWidth = null;
        if (!((3 & dirtyFlags) == 0 || data == null)) {
            dataParcelType = data.getParcelType();
            dataQuantity = data.getQuantity();
            dataCostValue = data.getCostValue();
            dataParcelDetailDescription = data.getParcelDetailDescription();
            dataHeight = data.getHeight();
            dataLength = data.getLength();
            dataWeightOfParcel = data.getWeightOfParcel();
            dataWidth = data.getWidth();
        }
        if ((3 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtCostValue, dataCostValue);
            TextViewBindingAdapter.setText(this.edtHeight, dataHeight);
            TextViewBindingAdapter.setText(this.edtLength, dataLength);
            TextViewBindingAdapter.setText(this.edtParcelDetailsDescription, dataParcelDetailDescription);
            TextViewBindingAdapter.setText(this.edtQuantity, dataQuantity);
            TextViewBindingAdapter.setText(this.edtWeight, dataWeightOfParcel);
            TextViewBindingAdapter.setText(this.edtWidth, dataWidth);
            TextViewBindingAdapter.setText(this.txtParcelType, dataParcelType);
        }
        if ((2 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.edtCostValue, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtCostValueandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtHeight, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtHeightandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtLength, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtLengthandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtParcelDetailsDescription, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtParcelDetailsDescriptionandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtQuantity, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtQuantityandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtWeight, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtWeightandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtWidth, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtWidthandroidTextAttrChanged);
        }
    }

    @NonNull
    public static LayoutImportStep2Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutImportStep2Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (LayoutImportStep2Binding) DataBindingUtil.inflate(inflater, R.layout.layout_import_step2, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static LayoutImportStep2Binding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutImportStep2Binding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.layout_import_step2, null, false), bindingComponent);
    }

    @NonNull
    public static LayoutImportStep2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static LayoutImportStep2Binding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/layout_import_step2_0".equals(view.getTag())) {
            return new LayoutImportStep2Binding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
