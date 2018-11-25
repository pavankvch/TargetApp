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
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.model.Notification;

public class RawNotificationsListBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    @Nullable
    private Notification mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final TextView txtDateTime;
    @NonNull
    public final TextView txtDescription;
    @NonNull
    public final TextView txtTitle;

    public RawNotificationsListBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds);
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtDateTime = (TextView) bindings[2];
        this.txtDateTime.setTag(null);
        this.txtDescription = (TextView) bindings[3];
        this.txtDescription.setTag(null);
        this.txtTitle = (TextView) bindings[1];
        this.txtTitle.setTag(null);
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
        setData((Notification) variable);
        return true;
    }

    public void setData(@Nullable Notification Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public Notification getData() {
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
        String dataDateTime = null;
        String myApplicationIsLTRDataTitleEnDataTitleAr = null;
        String dataTitleEn = null;
        String dataDescriptionAr = null;
        Notification data = this.mData;
        boolean myApplicationIsLTR = false;
        String dataDescriptionEn = null;
        String dataTitleAr = null;
        String myApplicationIsLTRDataDescriptionEnDataDescriptionAr = null;
        if ((3 & dirtyFlags) != 0) {
            myApplicationIsLTR = MyApplication.isLTR;
            if ((3 & dirtyFlags) != 0) {
                if (myApplicationIsLTR) {
                    dirtyFlags = (dirtyFlags | 8) | 32;
                } else {
                    dirtyFlags = (dirtyFlags | 4) | 16;
                }
            }
            if (data != null) {
                dataDateTime = data.getDateTime();
            }
        }
        if (!((8 & dirtyFlags) == 0 || data == null)) {
            dataTitleEn = data.getTitleEn();
        }
        if (!((16 & dirtyFlags) == 0 || data == null)) {
            dataDescriptionAr = data.getDescriptionAr();
        }
        if (!((32 & dirtyFlags) == 0 || data == null)) {
            dataDescriptionEn = data.getDescriptionEn();
        }
        if (!((4 & dirtyFlags) == 0 || data == null)) {
            dataTitleAr = data.getTitleAr();
        }
        if ((3 & dirtyFlags) != 0) {
            myApplicationIsLTRDataTitleEnDataTitleAr = myApplicationIsLTR ? dataTitleEn : dataTitleAr;
            if (myApplicationIsLTR) {
                myApplicationIsLTRDataDescriptionEnDataDescriptionAr = dataDescriptionEn;
            } else {
                myApplicationIsLTRDataDescriptionEnDataDescriptionAr = dataDescriptionAr;
            }
        }
        if ((3 & dirtyFlags) != 0) {
            TextViewBindingAdapter.setText(this.txtDateTime, dataDateTime);
            TextViewBindingAdapter.setText(this.txtDescription, myApplicationIsLTRDataDescriptionEnDataDescriptionAr);
            TextViewBindingAdapter.setText(this.txtTitle, myApplicationIsLTRDataTitleEnDataTitleAr);
        }
    }

    @NonNull
    public static RawNotificationsListBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawNotificationsListBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (RawNotificationsListBinding) DataBindingUtil.inflate(inflater, R.layout.raw_notifications_list, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static RawNotificationsListBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawNotificationsListBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.raw_notifications_list, null, false), bindingComponent);
    }

    @NonNull
    public static RawNotificationsListBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawNotificationsListBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/raw_notifications_list_0".equals(view.getTag())) {
            return new RawNotificationsListBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
