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
import android.support.v4.media.session.PlaybackStateCompat;
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
import kuwait.com.targetlogistics.model.Delivery;

public class ActivityDeliveryAddressBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = new IncludedLayouts(34);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView btnBack;
    @NonNull
    public final TextView btnSkip;
    @NonNull
    public final EditText edtAddressName;
    private InverseBindingListener edtAddressNameandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityDeliveryAddressBinding.this.edtAddressName);
            Delivery data = ActivityDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setFullName(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtApartment;
    private InverseBindingListener edtApartmentandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityDeliveryAddressBinding.this.edtApartment);
            Delivery data = ActivityDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setApartment(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtBlock;
    private InverseBindingListener edtBlockandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityDeliveryAddressBinding.this.edtBlock);
            Delivery data = ActivityDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setBlock(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtFloor;
    private InverseBindingListener edtFloorandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityDeliveryAddressBinding.this.edtFloor);
            Delivery data = ActivityDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setFloor(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtHouse;
    private InverseBindingListener edtHouseandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityDeliveryAddressBinding.this.edtHouse);
            Delivery data = ActivityDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setHouse(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtJadda;
    private InverseBindingListener edtJaddaandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityDeliveryAddressBinding.this.edtJadda);
            Delivery data = ActivityDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setJadda(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtPhone;
    private InverseBindingListener edtPhoneandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityDeliveryAddressBinding.this.edtPhone);
            Delivery data = ActivityDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setPhoneNo(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtPickupInstructions;
    private InverseBindingListener edtPickupInstructionsandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityDeliveryAddressBinding.this.edtPickupInstructions);
            Delivery data = ActivityDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setPickupInstruction(callbackArg_0);
            }
        }
    };
    @NonNull
    public final EditText edtStreetNumber;
    private InverseBindingListener edtStreetNumberandroidTextAttrChanged = new InverseBindingListener() {
        public void onChange() {
            String callbackArg_0 = TextViewBindingAdapter.getTextString(ActivityDeliveryAddressBinding.this.edtStreetNumber);
            Delivery data = ActivityDeliveryAddressBinding.this.mData;
            if (data != null) {
                data.setStreet(callbackArg_0);
            }
        }
    };
    @NonNull
    public final ImageView imgBackArrow;
    @NonNull
    public final ImageView imgRightArrow;
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
    private Delivery mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final RelativeLayout relArea;
    @NonNull
    public final TextInputLayout tIAddressName;
    @NonNull
    public final TextInputLayout tIApartment;
    @NonNull
    public final TextInputLayout tIBlock;
    @NonNull
    public final TextInputLayout tIFloor;
    @NonNull
    public final TextInputLayout tIHouse;
    @NonNull
    public final TextInputLayout tIJadda;
    @NonNull
    public final TextInputLayout tILArea;
    @NonNull
    public final TextInputLayout tIPhone;
    @NonNull
    public final TextInputLayout tIPickupInstructions;
    @NonNull
    public final TextInputLayout tIStreetNumber;
    @NonNull
    public final TextView textArea;
    @NonNull
    public final View view;

    static {
        sIncludes.setIncludes(1, new String[]{"layout_export_steps"}, new int[]{11}, new int[]{R.layout.layout_export_steps});
        sViewsWithIds.put(R.id.view, 12);
        sViewsWithIds.put(R.id.lin_main, 13);
        sViewsWithIds.put(R.id.rel_area, 14);
        sViewsWithIds.put(R.id.tILArea, 15);
        sViewsWithIds.put(R.id.textArea, 16);
        sViewsWithIds.put(R.id.img_right_arrow, 17);
        sViewsWithIds.put(R.id.tIPhone, 18);
        sViewsWithIds.put(R.id.tIAddressName, 19);
        sViewsWithIds.put(R.id.tIBlock, 20);
        sViewsWithIds.put(R.id.tIStreetNumber, 21);
        sViewsWithIds.put(R.id.tIHouse, 22);
        sViewsWithIds.put(R.id.tIApartment, 23);
        sViewsWithIds.put(R.id.tIFloor, 24);
        sViewsWithIds.put(R.id.tIJadda, 25);
        sViewsWithIds.put(R.id.tIPickupInstructions, 26);
        sViewsWithIds.put(R.id.lin_skip_back, 27);
        sViewsWithIds.put(R.id.lin_back, 28);
        sViewsWithIds.put(R.id.img_back_arrow, 29);
        sViewsWithIds.put(R.id.btn_back, 30);
        sViewsWithIds.put(R.id.lin_skip, 31);
        sViewsWithIds.put(R.id.btn_skip, 32);
        sViewsWithIds.put(R.id.img_skip_arrow, 33);
    }

    public ActivityDeliveryAddressBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        Object[] bindings = mapBindings(bindingComponent, root, 34, sIncludes, sViewsWithIds);
        this.btnBack = (TextView) bindings[30];
        this.btnSkip = (TextView) bindings[32];
        this.edtAddressName = (EditText) bindings[3];
        this.edtAddressName.setTag(null);
        this.edtApartment = (EditText) bindings[7];
        this.edtApartment.setTag(null);
        this.edtBlock = (EditText) bindings[4];
        this.edtBlock.setTag(null);
        this.edtFloor = (EditText) bindings[8];
        this.edtFloor.setTag(null);
        this.edtHouse = (EditText) bindings[6];
        this.edtHouse.setTag(null);
        this.edtJadda = (EditText) bindings[9];
        this.edtJadda.setTag(null);
        this.edtPhone = (EditText) bindings[2];
        this.edtPhone.setTag(null);
        this.edtPickupInstructions = (EditText) bindings[10];
        this.edtPickupInstructions.setTag(null);
        this.edtStreetNumber = (EditText) bindings[5];
        this.edtStreetNumber.setTag(null);
        this.imgBackArrow = (ImageView) bindings[29];
        this.imgRightArrow = (ImageView) bindings[17];
        this.imgSkipArrow = (ImageView) bindings[33];
        this.layoutSteps = (LayoutExportStepsBinding) bindings[11];
        setContainedBinding(this.layoutSteps);
        this.linBack = (LinearLayout) bindings[28];
        this.linMain = (LinearLayout) bindings[13];
        this.linSkip = (LinearLayout) bindings[31];
        this.linSkipBack = (LinearLayout) bindings[27];
        this.linSteps = (LinearLayout) bindings[1];
        this.linSteps.setTag(null);
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relArea = (RelativeLayout) bindings[14];
        this.tIAddressName = (TextInputLayout) bindings[19];
        this.tIApartment = (TextInputLayout) bindings[23];
        this.tIBlock = (TextInputLayout) bindings[20];
        this.tIFloor = (TextInputLayout) bindings[24];
        this.tIHouse = (TextInputLayout) bindings[22];
        this.tIJadda = (TextInputLayout) bindings[25];
        this.tILArea = (TextInputLayout) bindings[15];
        this.tIPhone = (TextInputLayout) bindings[18];
        this.tIPickupInstructions = (TextInputLayout) bindings[26];
        this.tIStreetNumber = (TextInputLayout) bindings[21];
        this.textArea = (TextView) bindings[16];
        this.view = (View) bindings[12];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
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
        throw new UnsupportedOperationException("Method not decompiled: kuwait.com.targetlogistics.databinding.ActivityDeliveryAddressBinding.hasPendingBindings():boolean");
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (7 != variableId) {
            return false;
        }
        setData((Delivery) variable);
        return true;
    }

    public void setData(@Nullable Delivery Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public Delivery getData() {
        return this.mData;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeData((Delivery) object, fieldId);
            case 1:
                return onChangeLayoutSteps((LayoutExportStepsBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeData(Delivery Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (fieldId == 17) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (fieldId == 9) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (fieldId == 4) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (fieldId == 20) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (fieldId == 10) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (fieldId == 3) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (fieldId == 8) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (fieldId == 12) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (fieldId != 18) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
            return true;
        }
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
        String dataPickupInstruction = null;
        String dataPhoneNo = null;
        String dataFullName = null;
        String dataHouse = null;
        String dataApartment = null;
        String dataBlock = null;
        Delivery data = this.mData;
        String dataFloor = null;
        String dataJadda = null;
        String dataStreet = null;
        if ((4093 & dirtyFlags) != 0) {
            if (!((3073 & dirtyFlags) == 0 || data == null)) {
                dataPickupInstruction = data.getPickupInstruction();
            }
            if (!((2053 & dirtyFlags) == 0 || data == null)) {
                dataPhoneNo = data.getPhoneNo();
            }
            if (!((2057 & dirtyFlags) == 0 || data == null)) {
                dataFullName = data.getFullName();
            }
            if (!((2113 & dirtyFlags) == 0 || data == null)) {
                dataHouse = data.getHouse();
            }
            if (!((2177 & dirtyFlags) == 0 || data == null)) {
                dataApartment = data.getApartment();
            }
            if (!((2065 & dirtyFlags) == 0 || data == null)) {
                dataBlock = data.getBlock();
            }
            if (!((2305 & dirtyFlags) == 0 || data == null)) {
                dataFloor = data.getFloor();
            }
            if (!((2561 & dirtyFlags) == 0 || data == null)) {
                dataJadda = data.getJadda();
            }
            if (!((2081 & dirtyFlags) == 0 || data == null)) {
                dataStreet = data.getStreet();
            }
        }
        if ((2057 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtAddressName, dataFullName);
        }
        if ((PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH & dirtyFlags) != 0) {
            TextViewBindingAdapter.setTextWatcher(this.edtAddressName, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtAddressNameandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtApartment, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtApartmentandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtBlock, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtBlockandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtFloor, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtFloorandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtHouse, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtHouseandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtJadda, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtJaddaandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtPhone, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtPhoneandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtPickupInstructions, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtPickupInstructionsandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(this.edtStreetNumber, (BeforeTextChanged) null, (OnTextChanged) null, (AfterTextChanged) null, this.edtStreetNumberandroidTextAttrChanged);
        }
        if ((2177 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtApartment, dataApartment);
        }
        if ((2065 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtBlock, dataBlock);
        }
        if ((2305 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtFloor, dataFloor);
        }
        if ((2113 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtHouse, dataHouse);
        }
        if ((2561 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtJadda, dataJadda);
        }
        if ((2053 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtPhone, dataPhoneNo);
        }
        if ((3073 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtPickupInstructions, dataPickupInstruction);
        }
        if ((2081 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtStreetNumber, dataStreet);
        }
        executeBindingsOn(this.layoutSteps);
    }

    @NonNull
    public static ActivityDeliveryAddressBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDeliveryAddressBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityDeliveryAddressBinding) DataBindingUtil.inflate(inflater, R.layout.activity_delivery_address, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityDeliveryAddressBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDeliveryAddressBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_delivery_address, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityDeliveryAddressBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDeliveryAddressBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_delivery_address_0".equals(view.getTag())) {
            return new ActivityDeliveryAddressBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
