package kuwait.com.targetlogistics.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;

public class ActivitySearchBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final EditText edtSearch;
    @NonNull
    public final ImageView imgBack;
    @NonNull
    public final ImageView imgSearch;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final RelativeLayout rlSearch;
    @NonNull
    public final RecyclerView rvSearchable;
    @NonNull
    public final TextView tvNoRecordFound;

    static {
        sViewsWithIds.put(R.id.rlSearch, 2);
        sViewsWithIds.put(R.id.imgBack, 3);
        sViewsWithIds.put(R.id.edtSearch, 4);
        sViewsWithIds.put(R.id.imgSearch, 5);
        sViewsWithIds.put(R.id.rvSearchable, 6);
    }

    public ActivitySearchBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.edtSearch = (EditText) bindings[4];
        this.imgBack = (ImageView) bindings[3];
        this.imgSearch = (ImageView) bindings[5];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.rlSearch = (RelativeLayout) bindings[2];
        this.rvSearchable = (RecyclerView) bindings[6];
        this.tvNoRecordFound = (TextView) bindings[1];
        this.tvNoRecordFound.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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
        return true;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        if ((1 & dirtyFlags) != 0) {
            this.tvNoRecordFound.setText(this.tvNoRecordFound.getResources().getString(R.string.no_record_found));
        }
    }

    @NonNull
    public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivitySearchBinding) DataBindingUtil.inflate(inflater, R.layout.activity_search, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySearchBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_search, null, false), bindingComponent);
    }

    @NonNull
    public static ActivitySearchBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivitySearchBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_search_0".equals(view.getTag())) {
            return new ActivitySearchBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
