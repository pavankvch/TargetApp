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
import kuwait.com.targetlogistics.model.City;

public class RowSearchableCityListItemBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags = -1;
    @Nullable
    private City mItem;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final TextView mboundView1;

    public RowSearchableCityListItemBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds);
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
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
        if (11 != variableId) {
            return false;
        }
        setItem((City) variable);
        return true;
    }

    public void setItem(@Nullable City Item) {
        this.mItem = Item;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(11);
        super.requestRebind();
    }

    @Nullable
    public City getItem() {
        return this.mItem;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        City item = this.mItem;
        String itemCityName = null;
        if (!((dirtyFlags & 3) == 0 || item == null)) {
            itemCityName = item.getCityName();
        }
        if ((dirtyFlags & 3) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, itemCityName);
        }
    }

    @NonNull
    public static RowSearchableCityListItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RowSearchableCityListItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (RowSearchableCityListItemBinding) DataBindingUtil.inflate(inflater, R.layout.row_searchable_city_list_item, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static RowSearchableCityListItemBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RowSearchableCityListItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.row_searchable_city_list_item, null, false), bindingComponent);
    }

    @NonNull
    public static RowSearchableCityListItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RowSearchableCityListItemBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/row_searchable_city_list_item_0".equals(view.getTag())) {
            return new RowSearchableCityListItemBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
