package kuwait.com.targetlogistics.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;

public class ActivityTrackerBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final FrameLayout frmMap;
    @NonNull
    public final ImageView imgClose;
    @NonNull
    public final LinearLayout llEta;
    private long mDirtyFlags = -1;
    @NonNull
    public final ProgressBar mProg;
    @NonNull
    private final PercentRelativeLayout mboundView0;
    @NonNull
    public final TextView tvCallCenter;
    @NonNull
    public final TextView tvETA;
    @NonNull
    public final TextView tvMins;
    @NonNull
    public final TextView tvViewOrder;

    static {
        sViewsWithIds.put(R.id.frm_map, 1);
        sViewsWithIds.put(R.id.tvViewOrder, 2);
        sViewsWithIds.put(R.id.tvCallCenter, 3);
        sViewsWithIds.put(R.id.imgClose, 4);
        sViewsWithIds.put(R.id.mProg, 5);
        sViewsWithIds.put(R.id.ll_eta, 6);
        sViewsWithIds.put(R.id.tvETA, 7);
        sViewsWithIds.put(R.id.tvMins, 8);
    }

    public ActivityTrackerBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.frmMap = (FrameLayout) bindings[1];
        this.imgClose = (ImageView) bindings[4];
        this.llEta = (LinearLayout) bindings[6];
        this.mProg = (ProgressBar) bindings[5];
        this.mboundView0 = (PercentRelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvCallCenter = (TextView) bindings[3];
        this.tvETA = (TextView) bindings[7];
        this.tvMins = (TextView) bindings[8];
        this.tvViewOrder = (TextView) bindings[2];
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
    }

    @NonNull
    public static ActivityTrackerBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityTrackerBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityTrackerBinding) DataBindingUtil.inflate(inflater, R.layout.activity_tracker, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityTrackerBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityTrackerBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_tracker, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityTrackerBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityTrackerBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_tracker_0".equals(view.getTag())) {
            return new ActivityTrackerBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
