package kuwait.com.targetlogistics.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.databinding.adapters.TextViewBindingAdapter;
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
import kuwait.com.targetlogistics.model.Address;

public class ActivityNewAddressBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView btnSaveAddress;
    @NonNull
    public final EditText edtAddressName;
    @NonNull
    public final EditText edtApartment;
    @NonNull
    public final EditText edtBlock;
    @NonNull
    public final EditText edtFloor;
    @NonNull
    public final EditText edtHouse;
    @NonNull
    public final EditText edtJadda;
    @NonNull
    public final EditText edtPhone;
    @NonNull
    public final EditText edtPickupInstructions;
    @NonNull
    public final EditText edtStreetNumber;
    @NonNull
    public final ImageView imgRightArrow;
    @NonNull
    public final LinearLayout linMain;
    @NonNull
    public final LinearLayout linSaveAddress;
    @Nullable
    private Address mData;
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
        sViewsWithIds.put(R.id.view, 10);
        sViewsWithIds.put(R.id.lin_main, 11);
        sViewsWithIds.put(R.id.rel_area, 12);
        sViewsWithIds.put(R.id.tILArea, 13);
        sViewsWithIds.put(R.id.textArea, 14);
        sViewsWithIds.put(R.id.img_right_arrow, 15);
        sViewsWithIds.put(R.id.tIPhone, 16);
        sViewsWithIds.put(R.id.tIAddressName, 17);
        sViewsWithIds.put(R.id.tIBlock, 18);
        sViewsWithIds.put(R.id.tIStreetNumber, 19);
        sViewsWithIds.put(R.id.tIHouse, 20);
        sViewsWithIds.put(R.id.tIApartment, 21);
        sViewsWithIds.put(R.id.tIFloor, 22);
        sViewsWithIds.put(R.id.tIJadda, 23);
        sViewsWithIds.put(R.id.tIPickupInstructions, 24);
        sViewsWithIds.put(R.id.lin_save_address, 25);
        sViewsWithIds.put(R.id.btn_save_address, 26);
    }

    public ActivityNewAddressBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds);
        this.btnSaveAddress = (TextView) bindings[26];
        this.edtAddressName = (EditText) bindings[2];
        this.edtAddressName.setTag(null);
        this.edtApartment = (EditText) bindings[6];
        this.edtApartment.setTag(null);
        this.edtBlock = (EditText) bindings[3];
        this.edtBlock.setTag(null);
        this.edtFloor = (EditText) bindings[7];
        this.edtFloor.setTag(null);
        this.edtHouse = (EditText) bindings[5];
        this.edtHouse.setTag(null);
        this.edtJadda = (EditText) bindings[8];
        this.edtJadda.setTag(null);
        this.edtPhone = (EditText) bindings[1];
        this.edtPhone.setTag(null);
        this.edtPickupInstructions = (EditText) bindings[9];
        this.edtPickupInstructions.setTag(null);
        this.edtStreetNumber = (EditText) bindings[4];
        this.edtStreetNumber.setTag(null);
        this.imgRightArrow = (ImageView) bindings[15];
        this.linMain = (LinearLayout) bindings[11];
        this.linSaveAddress = (LinearLayout) bindings[25];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relArea = (RelativeLayout) bindings[12];
        this.tIAddressName = (TextInputLayout) bindings[17];
        this.tIApartment = (TextInputLayout) bindings[21];
        this.tIBlock = (TextInputLayout) bindings[18];
        this.tIFloor = (TextInputLayout) bindings[22];
        this.tIHouse = (TextInputLayout) bindings[20];
        this.tIJadda = (TextInputLayout) bindings[23];
        this.tILArea = (TextInputLayout) bindings[13];
        this.tIPhone = (TextInputLayout) bindings[16];
        this.tIPickupInstructions = (TextInputLayout) bindings[24];
        this.tIStreetNumber = (TextInputLayout) bindings[19];
        this.textArea = (TextView) bindings[14];
        this.view = (View) bindings[10];
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
        setData((Address) variable);
        return true;
    }

    public void setData(@Nullable Address Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public Address getData() {
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
        String dataPickupHouseno = null;
        String dataPickupJadda = null;
        String dataPickupInstruction = null;
        String dataPhoneNo = null;
        String dataPickupFloor = null;
        String dataPickupAddressname = null;
        String dataPickupBlock = null;
        Address data = this.mData;
        String dataPickupApartment = null;
        String dataPickupStreet = null;
        if (!((3 & dirtyFlags) == 0 || data == null)) {
            dataPickupHouseno = data.getPickupHouseno();
            dataPickupJadda = data.getPickup_Jadda();
            dataPickupInstruction = data.getPickupInstruction();
            dataPhoneNo = data.getPhoneNo();
            dataPickupFloor = data.getPickupFloor();
            dataPickupAddressname = data.getPickupAddressname();
            dataPickupBlock = data.getPickupBlock();
            dataPickupApartment = data.getPickupApartment();
            dataPickupStreet = data.getPickupStreet();
        }
        if ((3 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.edtAddressName, dataPickupAddressname);
            TextViewBindingAdapter.setText(this.edtApartment, dataPickupApartment);
            TextViewBindingAdapter.setText(this.edtBlock, dataPickupBlock);
            TextViewBindingAdapter.setText(this.edtFloor, dataPickupFloor);
            TextViewBindingAdapter.setText(this.edtHouse, dataPickupHouseno);
            TextViewBindingAdapter.setText(this.edtJadda, dataPickupJadda);
            TextViewBindingAdapter.setText(this.edtPhone, dataPhoneNo);
            TextViewBindingAdapter.setText(this.edtPickupInstructions, dataPickupInstruction);
            TextViewBindingAdapter.setText(this.edtStreetNumber, dataPickupStreet);
        }
    }

    @NonNull
    public static ActivityNewAddressBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityNewAddressBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityNewAddressBinding) DataBindingUtil.inflate(inflater, R.layout.activity_new_address, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityNewAddressBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityNewAddressBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_new_address, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityNewAddressBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityNewAddressBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_new_address_0".equals(view.getTag())) {
            return new ActivityNewAddressBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
