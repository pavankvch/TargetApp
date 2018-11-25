package kuwait.com.targetlogistics.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.stacktips.view.CustomCalendarView;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.model.Export;

public class ActivityPickupDateTimeBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = new IncludedLayouts(22);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final TextView btnBack;
    @NonNull
    public final TextView btnSkip;
    @NonNull
    public final CustomCalendarView calendarView;
    @NonNull
    public final EditText edtHours;
    @NonNull
    public final EditText edtMins;
    @NonNull
    public final ImageView imgBackArrow;
    @NonNull
    public final ImageView imgSkipArrow;
    @Nullable
    public final LayoutExportStepsBinding layoutSteps;
    @NonNull
    public final LinearLayout linBack;
    @NonNull
    public final LinearLayout linSkip;
    @NonNull
    public final LinearLayout linSkipBack;
    @NonNull
    public final LinearLayout linSteps;
    @NonNull
    public final LinearLayout linTime;
    @Nullable
    private Export mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final RelativeLayout relTimeSlot;
    @NonNull
    public final ScrollView scrollView;
    @NonNull
    public final TextView txtAM;
    @NonNull
    public final TextView txtNote;
    @NonNull
    public final TextView txtPM;
    @NonNull
    public final TextView txtPickupTime;
    @NonNull
    public final TextView txtTimeSlot;
    @NonNull
    public final View view;

    static {
        sIncludes.setIncludes(1, new String[]{"layout_export_steps"}, new int[]{2}, new int[]{R.layout.layout_export_steps});
        sViewsWithIds.put(R.id.view, 3);
        sViewsWithIds.put(R.id.scroll_view, 4);
        sViewsWithIds.put(R.id.calendarView, 5);
        sViewsWithIds.put(R.id.txt_pickup_time, 6);
        sViewsWithIds.put(R.id.lin_time, 7);
        sViewsWithIds.put(R.id.edt_hours, 8);
        sViewsWithIds.put(R.id.edt_mins, 9);
        sViewsWithIds.put(R.id.txt_AM, 10);
        sViewsWithIds.put(R.id.txt_PM, 11);
        sViewsWithIds.put(R.id.rel_time_slot, 12);
        sViewsWithIds.put(R.id.txt_time_slot, 13);
        sViewsWithIds.put(R.id.txt_note, 14);
        sViewsWithIds.put(R.id.lin_skip_back, 15);
        sViewsWithIds.put(R.id.lin_back, 16);
        sViewsWithIds.put(R.id.img_back_arrow, 17);
        sViewsWithIds.put(R.id.btn_back, 18);
        sViewsWithIds.put(R.id.lin_skip, 19);
        sViewsWithIds.put(R.id.btn_skip, 20);
        sViewsWithIds.put(R.id.img_skip_arrow, 21);
    }

    public ActivityPickupDateTimeBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        Object[] bindings = mapBindings(bindingComponent, root, 22, sIncludes, sViewsWithIds);
        this.btnBack = (TextView) bindings[18];
        this.btnSkip = (TextView) bindings[20];
        this.calendarView = (CustomCalendarView) bindings[5];
        this.edtHours = (EditText) bindings[8];
        this.edtMins = (EditText) bindings[9];
        this.imgBackArrow = (ImageView) bindings[17];
        this.imgSkipArrow = (ImageView) bindings[21];
        this.layoutSteps = (LayoutExportStepsBinding) bindings[2];
        setContainedBinding(this.layoutSteps);
        this.linBack = (LinearLayout) bindings[16];
        this.linSkip = (LinearLayout) bindings[19];
        this.linSkipBack = (LinearLayout) bindings[15];
        this.linSteps = (LinearLayout) bindings[1];
        this.linSteps.setTag(null);
        this.linTime = (LinearLayout) bindings[7];
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relTimeSlot = (RelativeLayout) bindings[12];
        this.scrollView = (ScrollView) bindings[4];
        this.txtAM = (TextView) bindings[10];
        this.txtNote = (TextView) bindings[14];
        this.txtPM = (TextView) bindings[11];
        this.txtPickupTime = (TextView) bindings[6];
        this.txtTimeSlot = (TextView) bindings[13];
        this.view = (View) bindings[3];
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
        throw new UnsupportedOperationException("Method not decompiled: kuwait.com.targetlogistics.databinding.ActivityPickupDateTimeBinding.hasPendingBindings():boolean");
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (7 != variableId) {
            return false;
        }
        setData((Export) variable);
        return true;
    }

    public void setData(@Nullable Export Data) {
        this.mData = Data;
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
        executeBindingsOn(this.layoutSteps);
    }

    @NonNull
    public static ActivityPickupDateTimeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityPickupDateTimeBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityPickupDateTimeBinding) DataBindingUtil.inflate(inflater, R.layout.activity_pickup_date_time, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityPickupDateTimeBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityPickupDateTimeBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_pickup_date_time, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityPickupDateTimeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityPickupDateTimeBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_pickup_date_time_0".equals(view.getTag())) {
            return new ActivityPickupDateTimeBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
