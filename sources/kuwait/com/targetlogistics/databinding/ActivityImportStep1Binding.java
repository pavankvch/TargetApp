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
import kuwait.com.targetlogistics.model.Import;

public class ActivityImportStep1Binding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = new IncludedLayouts(23);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView btnNext;
    @NonNull
    public final EditText edtAddress1;
    private InverseBindingListener edtAddress1androidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityImportStep1Binding.this.edtAddress1);
            Import data = ActivityImportStep1Binding.this.mData;
            if (data != null) {
                data.setAddress1(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtAddress2;
    private InverseBindingListener edtAddress2androidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityImportStep1Binding.this.edtAddress2);
            Import data = ActivityImportStep1Binding.this.mData;
            if (data != null) {
                data.setAddress2(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtFullName;
    private InverseBindingListener edtFullNameandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityImportStep1Binding.this.edtFullName);
            Import data = ActivityImportStep1Binding.this.mData;
            if (data != null) {
                data.setName(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtPhone;
    private InverseBindingListener edtPhoneandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityImportStep1Binding.this.edtPhone);
            Import data = ActivityImportStep1Binding.this.mData;
            if (data != null) {
                data.setPhoneNo(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtPostalCode;
    private InverseBindingListener edtPostalCodeandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityImportStep1Binding.this.edtPostalCode);
            Import data = ActivityImportStep1Binding.this.mData;
            if (data != null) {
                data.setPostCode(callbackArg_0);
            }
        }
    };
    @NonNull
    public final ImageView imgNextArrow;
    @Nullable
    public final LayoutImportStepsBinding layoutSteps;
    @NonNull
    public final LinearLayout linMain;
    @NonNull
    public final LinearLayout linNext;
    @NonNull
    public final LinearLayout linSteps;
    @Nullable
    private Import mData;
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
        sIncludes.setIncludes(1, new String[]{"layout_import_steps"}, new int[]{7}, new int[]{R.layout.layout_import_steps});
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
        sViewsWithIds.put(R.id.lin_next, 20);
        sViewsWithIds.put(R.id.btn_next, 21);
        sViewsWithIds.put(R.id.img_next_arrow, 22);
    }

    public ActivityImportStep1Binding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        Object[] bindings = mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds);
        this.btnNext = (TextView) bindings[21];
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
        this.imgNextArrow = (ImageView) bindings[22];
        this.layoutSteps = (LayoutImportStepsBinding) bindings[7];
        setContainedBinding(this.layoutSteps);
        this.linMain = (LinearLayout) bindings[9];
        this.linNext = (LinearLayout) bindings[20];
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
            this.mDirtyFlags = 128;
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
        throw new UnsupportedOperationException("Method not decompiled: kuwait.com.targetlogistics.databinding.ActivityImportStep1Binding.hasPendingBindings():boolean");
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (7 != variableId) {
            return false;
        }
        setData((Import) variable);
        return true;
    }

    public void setData(@Nullable Import Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public Import getData() {
        return this.mData;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeData((Import) object, fieldId);
            case 1:
                return onChangeLayoutSteps((LayoutImportStepsBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeData(Import Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId == 14) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (fieldId == 17) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (fieldId == 19) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (fieldId == 1) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (fieldId != 2) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
    }

    private boolean onChangeLayoutSteps(LayoutImportStepsBinding LayoutSteps, int fieldId) {
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
        String dataName = null;
        String dataAddress1 = null;
        Import data = this.mData;
        String dataPostCode = null;
        String dataAddress2 = null;
        if ((253 & dirtyFlags) != 0) {
            if (!((137 & dirtyFlags) == 0 || data == null)) {
                dataPhoneNo = data.getPhoneNo();
            }
            if (!((133 & dirtyFlags) == 0 || data == null)) {
                dataName = data.getName();
            }
            if (!((161 & dirtyFlags) == 0 || data == null)) {
                dataAddress1 = data.getAddress1();
            }
            if (!((145 & dirtyFlags) == 0 || data == null)) {
                dataPostCode = data.getPostCode();
            }
            if (!((193 & dirtyFlags) == 0 || data == null)) {
                dataAddress2 = data.getAddress2();
            }
        }
        if ((161 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtAddress1, dataAddress1);
        }
        if ((128 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.edtAddress1, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtAddress1androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtAddress2, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtAddress2androidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtFullName, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtFullNameandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtPhone, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtPhoneandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtPostalCode, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtPostalCodeandroidTextAttrChanged);
        }
        if ((193 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtAddress2, dataAddress2);
        }
        if ((133 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtFullName, dataName);
        }
        if ((137 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtPhone, dataPhoneNo);
        }
        if ((145 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtPostalCode, dataPostCode);
        }
        executeBindingsOn(this.layoutSteps);
    }

    @NonNull
    public static ActivityImportStep1Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityImportStep1Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityImportStep1Binding) DataBindingUtil.inflate(inflater, R.layout.activity_import_step1, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityImportStep1Binding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityImportStep1Binding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_import_step1, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityImportStep1Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityImportStep1Binding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_import_step1_0".equals(view.getTag())) {
            return new ActivityImportStep1Binding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
