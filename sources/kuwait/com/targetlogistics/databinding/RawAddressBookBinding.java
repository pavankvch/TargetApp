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
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Biding;
import kuwait.com.targetlogistics.model.Address;

public class RawAddressBookBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final ImageView imgDelete;
    @NonNull
    public final ImageView imgEdit;
    @Nullable
    private Address mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final RelativeLayout relDelete;
    @NonNull
    public final RelativeLayout relEdit;
    @NonNull
    public final TextView txtAddress;
    @NonNull
    public final TextView txtColon;
    @NonNull
    public final TextView txtDelete;
    @NonNull
    public final TextView txtEdit;
    @NonNull
    public final TextView txtPhoneLabel;
    @NonNull
    public final TextView txtPhoneNo;

    static {
        sViewsWithIds.put(R.id.txt_colon, 4);
        sViewsWithIds.put(R.id.rel_edit, 5);
        sViewsWithIds.put(R.id.img_edit, 6);
        sViewsWithIds.put(R.id.txt_edit, 7);
        sViewsWithIds.put(R.id.rel_delete, 8);
        sViewsWithIds.put(R.id.txt_delete, 9);
        sViewsWithIds.put(R.id.img_delete, 10);
    }

    public RawAddressBookBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.imgDelete = (ImageView) bindings[10];
        this.imgEdit = (ImageView) bindings[6];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relDelete = (RelativeLayout) bindings[8];
        this.relEdit = (RelativeLayout) bindings[5];
        this.txtAddress = (TextView) bindings[1];
        this.txtAddress.setTag(null);
        this.txtColon = (TextView) bindings[4];
        this.txtDelete = (TextView) bindings[9];
        this.txtEdit = (TextView) bindings[7];
        this.txtPhoneLabel = (TextView) bindings[2];
        this.txtPhoneLabel.setTag(null);
        this.txtPhoneNo = (TextView) bindings[3];
        this.txtPhoneNo.setTag(null);
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
        String dataPhoneNo = null;
        Address data = this.mData;
        String dataAddress = null;
        if (!((dirtyFlags & 3) == 0 || data == null)) {
            dataPhoneNo = data.getPhoneNo();
            dataAddress = data.getAddress();
        }
        if ((dirtyFlags & 3) != 0) {
            TextViewBindingAdapter.setText(this.txtAddress, dataAddress);
            TextViewBindingAdapter.setText(this.txtPhoneNo, dataPhoneNo);
        }
        if ((2 & dirtyFlags) != 0) {
            Biding.loadLabel(this.txtPhoneLabel, this.txtPhoneLabel.getResources().getString(R.string.phone));
        }
    }

    @NonNull
    public static RawAddressBookBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawAddressBookBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (RawAddressBookBinding) DataBindingUtil.inflate(inflater, R.layout.raw_address_book, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static RawAddressBookBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawAddressBookBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.raw_address_book, null, false), bindingComponent);
    }

    @NonNull
    public static RawAddressBookBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawAddressBookBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/raw_address_book_0".equals(view.getTag())) {
            return new RawAddressBookBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
