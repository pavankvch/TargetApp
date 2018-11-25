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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;

public class ActivityExportParcelInfoBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = new IncludedLayouts(14);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView btnBack;
    @NonNull
    public final TextView btnSkip;
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
    public final LinearLayout linParcelInfoView;
    @NonNull
    public final LinearLayout linSkip;
    @NonNull
    public final LinearLayout linSkipBack;
    @NonNull
    public final LinearLayout linSteps;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final RecyclerView recParcelInfo;
    @NonNull
    public final View view;

    static {
        sIncludes.setIncludes(1, new String[]{"layout_export_steps"}, new int[]{2}, new int[]{R.layout.layout_export_steps});
        sViewsWithIds.put(R.id.view, 3);
        sViewsWithIds.put(R.id.rec_parcel_info, 4);
        sViewsWithIds.put(R.id.lin_main, 5);
        sViewsWithIds.put(R.id.lin_parcel_info_view, 6);
        sViewsWithIds.put(R.id.lin_skip_back, 7);
        sViewsWithIds.put(R.id.lin_back, 8);
        sViewsWithIds.put(R.id.img_back_arrow, 9);
        sViewsWithIds.put(R.id.btn_back, 10);
        sViewsWithIds.put(R.id.lin_skip, 11);
        sViewsWithIds.put(R.id.btn_skip, 12);
        sViewsWithIds.put(R.id.img_skip_arrow, 13);
    }

    public ActivityExportParcelInfoBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        Object[] bindings = mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds);
        this.btnBack = (TextView) bindings[10];
        this.btnSkip = (TextView) bindings[12];
        this.imgBackArrow = (ImageView) bindings[9];
        this.imgSkipArrow = (ImageView) bindings[13];
        this.layoutSteps = (LayoutExportStepsBinding) bindings[2];
        setContainedBinding(this.layoutSteps);
        this.linBack = (LinearLayout) bindings[8];
        this.linMain = (LinearLayout) bindings[5];
        this.linParcelInfoView = (LinearLayout) bindings[6];
        this.linSkip = (LinearLayout) bindings[11];
        this.linSkipBack = (LinearLayout) bindings[7];
        this.linSteps = (LinearLayout) bindings[1];
        this.linSteps.setTag(null);
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recParcelInfo = (RecyclerView) bindings[4];
        this.view = (View) bindings[3];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        throw new UnsupportedOperationException("Method not decompiled: kuwait.com.targetlogistics.databinding.ActivityExportParcelInfoBinding.hasPendingBindings():boolean");
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        return true;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLayoutSteps((LayoutExportStepsBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLayoutSteps(LayoutExportStepsBinding LayoutSteps, int fieldId) {
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
        executeBindingsOn(this.layoutSteps);
    }

    @NonNull
    public static ActivityExportParcelInfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityExportParcelInfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityExportParcelInfoBinding) DataBindingUtil.inflate(inflater, R.layout.activity_export_parcel_info, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityExportParcelInfoBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityExportParcelInfoBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_export_parcel_info, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityExportParcelInfoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityExportParcelInfoBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_export_parcel_info_0".equals(view.getTag())) {
            return new ActivityExportParcelInfoBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
