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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.model.Export;

public class ActivityExportDeliveryAddressBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = new IncludedLayouts(27);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView btnBack;
    @NonNull
    public final TextView btnSkip;
    @NonNull
    public final EditText edtAddress1;
    private InverseBindingListener edtAddress1androidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityExportDeliveryAddressBinding.this.edtAddress1);
            Export data = ActivityExportDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setAddress1(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtAddress2;
    private InverseBindingListener edtAddress2androidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityExportDeliveryAddressBinding.this.edtAddress2);
            Export data = ActivityExportDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setAddress2(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtFullName;
    private InverseBindingListener edtFullNameandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityExportDeliveryAddressBinding.this.edtFullName);
            Export data = ActivityExportDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setFullName(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtPhone;
    private InverseBindingListener edtPhoneandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityExportDeliveryAddressBinding.this.edtPhone);
            Export data = ActivityExportDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setPhoneNo(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtPostalCode;
    private InverseBindingListener edtPostalCodeandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityExportDeliveryAddressBinding.this.edtPostalCode);
            Export data = ActivityExportDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setPostCode(callbackArg_0);
            }
        }
    };
    @NonNull
    public final ImageView imgBackArrow;
    @NonNull
    public final ImageView imgSkipArrow;
    @Nullable
    public final LayoutExportStepsBinding layoutSteps;
    @NonNull
    public final LinearLayout linBack;
    @NonNull
    public final LinearLayout linMain;
    @NonNull
    public final LinearLayout linSkip;
    @NonNull
    public final LinearLayout linSkipBack;
    @NonNull
    public final LinearLayout linSteps;
    @Nullable
    private Export mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final RelativeLayout relCity;
    @NonNull
    public final RelativeLayout relCountry;
    @NonNull
    public final TextInputLayout tILAddress1;
    @NonNull
    public final TextInputLayout tILAddress2;
    @NonNull
    public final TextInputLayout tILFullName;
    @NonNull
    public final TextInputLayout tILPhone;
    @NonNull
    public final TextInputLayout tILPostalCode;
    @NonNull
    public final TextView txtCity;
    @NonNull
    public final TextView txtCode;
    @NonNull
    public final TextView txtCountry;
    @NonNull
    public final View view;

    static {
        sIncludes.setIncludes(1, new String[]{"layout_export_steps"}, new int[]{7}, new int[]{R.layout.layout_export_steps});
        sViewsWithIds.put(R.id.view, 8);
        sViewsWithIds.put(R.id.lin_main, 9);
        sViewsWithIds.put(R.id.tILFullName, 10);
        sViewsWithIds.put(R.id.rel_country, 11);
        sViewsWithIds.put(R.id.txtCountry, 12);
        sViewsWithIds.put(R.id.rel_city, 13);
        sViewsWithIds.put(R.id.txtCity, 14);
        sViewsWithIds.put(R.id.txtCode, 15);
        sViewsWithIds.put(R.id.tILPhone, 16);
        sViewsWithIds.put(R.id.tILPostalCode, 17);
        sViewsWithIds.put(R.id.tILAddress1, 18);
        sViewsWithIds.put(R.id.tILAddress2, 19);
        sViewsWithIds.put(R.id.lin_skip_back, 20);
        sViewsWithIds.put(R.id.lin_back, 21);
        sViewsWithIds.put(R.id.img_back_arrow, 22);
        sViewsWithIds.put(R.id.btn_back, 23);
        sViewsWithIds.put(R.id.lin_skip, 24);
        sViewsWithIds.put(R.id.btn_skip, 25);
        sViewsWithIds.put(R.id.img_skip_arrow, 26);
    }

    public ActivityExportDeliveryAddressBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        Object[] bindings = mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds);
        this.btnBack = (TextView) bindings[23];
        this.btnSkip = (TextView) bindings[25];
        this.edtAddress1 = (EditText) bindings[5];
        this.edtAddress1.setTag(null);
        this.edtAddress2 = (EditText) bindings[6];
        this.edtAddress2.setTag(null);
        this.edtFullName = (EditText) bindings[2];
        this.edtFullName.setTag(null);
        this.edtPhone = (EditText) bindings[3];
        this.edtPhone.setTag(null);
        this.edtPostalCode = (EditText) bindings[4];
        this.edtPostalCode.setTag(null);
        this.imgBackArrow = (ImageView) bindings[22];
        this.imgSkipArrow = (ImageView) bindings[26];
        this.layoutSteps = (LayoutExportStepsBinding) bindings[7];
        setContainedBinding(this.layoutSteps);
        this.linBack = (LinearLayout) bindings[21];
        this.linMain = (LinearLayout) bindings[9];
        this.linSkip = (LinearLayout) bindings[24];
        this.linSkipBack = (LinearLayout) bindings[20];
        this.linSteps = (LinearLayout) bindings[1];
        this.linSteps.setTag(null);
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relCity = (RelativeLayout) bindings[13];
        this.relCountry = (RelativeLayout) bindings[11];
        this.tILAddress1 = (TextInputLayout) bindings[18];
        this.tILAddress2 = (TextInputLayout) bindings[19];
        this.tILFullName = (TextInputLayout) bindings[10];
        this.tILPhone = (TextInputLayout) bindings[16];
        this.tILPostalCode = (TextInputLayout) bindings[17];
        this.txtCity = (TextView) bindings[14];
        this.txtCode = (TextView) bindings[15];
        this.txtCountry = (TextView) bindings[12];
        this.view = (View) bindings[8];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        this.layoutSteps.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
        r6 = this;
        r0 = 1;
        monitor-enter(r6);
        r2 = r6.mDirtyFlags;	 Catch:{ all -> 0x0017 }
        r4 = 0;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 == 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r6);	 Catch:{ all -> 0x0017 }
    L_0x000b:
        return r0;
    L_0x000c:
        monitor-exit(r6);	 Catch:{ all -> 0x0017 }
        r1 = r6.layoutSteps;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x000b;
    L_0x0015:
        r0 = 0;
        goto L_0x000b;
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0017 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kuwait.com.targetlogistics.databinding.ActivityExportDeliveryAddressBinding.hasPendingBindings():boolean");
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (7 != variableId) {
            return false;
        }
        setData((Export) variable);
        return true;
    }

    public void setData(@Nullable Export Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public Export getData() {
        return this.mData;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeData((Export) object, fieldId);
            case 1:
                return onChangeLayoutSteps((LayoutExportStepsBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeData(Export Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeLayoutSteps(LayoutExportStepsBinding LayoutSteps, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String dataPhoneNo = null;
        String dataFullName = null;
        String dataAddress1 = null;
        Export data = this.mData;
        String dataPostCode = null;
        String dataAddress2 = null;
        if (!((5 & dirtyFlags) == 0 || data == null)) {
            dataPhoneNo = data.getPhoneNo();
            dataFullName = data.getFullName();
            dataAddress1 = data.getAddress1();
            dataPostCode = data.getPostCode();
            dataAddress2 = data.getAddress2();
        }
        if ((5 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtAddress1, dataAddress1);
            TextViewBindingAdapter.setText(this.edtAddress2, dataAddress2);
            TextViewBindingAdapter.setText(this.edtFullName, dataFullName);
            TextViewBindingAdapter.setText(this.edtPhone, dataPhoneNo);
            TextViewBindingAdapter.setText(this.edtPostalCode, dataPostCode);
        }
        if ((4 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.edtAddress1, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtAddress1androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtAddress2, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtAddress2androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtFullName, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtFullNameandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtPhone, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtPhoneandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtPostalCode, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtPostalCodeandroidTextAttrChanged);
        }
        executeBindingsOn(this.layoutSteps);
    }

    @NonNull
    public static ActivityExportDeliveryAddressBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityExportDeliveryAddressBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityExportDeliveryAddressBinding) DataBindingUtil.inflate(inflater, R.layout.activity_export_delivery_address, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityExportDeliveryAddressBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityExportDeliveryAddressBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_export_delivery_address, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityExportDeliveryAddressBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityExportDeliveryAddressBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_export_delivery_address_0".equals(view.getTag())) {
            return new ActivityExportDeliveryAddressBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
