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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.model.DrawerItem;

public class RawDrawerLayoutBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = new IncludedLayouts(7);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final ImageView imgRightArrow;
    @Nullable
    public final LayoutLangBinding layoutLang;
    @Nullable
    public final LayoutShipmentHistoryBinding layoutShipmentHistory;
    @NonNull
    public final LinearLayout linLang;
    @NonNull
    public final LinearLayout linShipment;
    @Nullable
    private DrawerItem mData;
    private long mDirtyFlags = -1;
    @NonNull
    private final RelativeLayout mboundView0;
    @NonNull
    public final TextView txtDrawerText;

    static {
        sIncludes.setIncludes(3, new String[]{"layout_shipment_history"}, new int[]{5}, new int[]{R.layout.layout_shipment_history});
        sIncludes.setIncludes(2, new String[]{"layout_lang"}, new int[]{4}, new int[]{R.layout.layout_lang});
        sViewsWithIds.put(R.id.img_right_arrow, 6);
    }

    public RawDrawerLayoutBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 2);
        Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.imgRightArrow = (ImageView) bindings[6];
        this.layoutLang = (LayoutLangBinding) bindings[4];
        setContainedBinding(this.layoutLang);
        this.layoutShipmentHistory = (LayoutShipmentHistoryBinding) bindings[5];
        setContainedBinding(this.layoutShipmentHistory);
        this.linLang = (LinearLayout) bindings[2];
        this.linLang.setTag(null);
        this.linShipment = (LinearLayout) bindings[3];
        this.linShipment.setTag(null);
        this.mboundView0 = (RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtDrawerText = (TextView) bindings[1];
        this.txtDrawerText.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        this.layoutLang.invalidateAll();
        this.layoutShipmentHistory.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
        r6 = this;
        r0 = 1;
        monitor-enter(r6);
        r2 = r6.mDirtyFlags;	 Catch:{ all -> 0x001f }
        r4 = 0;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 == 0) goto L_0x000c;
    L_0x000a:
        monitor-exit(r6);	 Catch:{ all -> 0x001f }
    L_0x000b:
        return r0;
    L_0x000c:
        monitor-exit(r6);	 Catch:{ all -> 0x001f }
        r1 = r6.layoutLang;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x000b;
    L_0x0015:
        r1 = r6.layoutShipmentHistory;
        r1 = r1.hasPendingBindings();
        if (r1 != 0) goto L_0x000b;
    L_0x001d:
        r0 = 0;
        goto L_0x000b;
    L_0x001f:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x001f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kuwait.com.targetlogistics.databinding.RawDrawerLayoutBinding.hasPendingBindings():boolean");
    }

    public boolean setVariable(int variableId, @Nullable Object variable) {
        if (7 != variableId) {
            return false;
        }
        setData((DrawerItem) variable);
        return true;
    }

    public void setData(@Nullable DrawerItem Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Nullable
    public DrawerItem getData() {
        return this.mData;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0:
                return onChangeLayoutLang((LayoutLangBinding) object, fieldId);
            case 1:
                return onChangeLayoutShipmentHistory((LayoutShipmentHistoryBinding) object, fieldId);
            default:
                return false;
        }
    }

    private boolean onChangeLayoutLang(LayoutLangBinding LayoutLang, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeLayoutShipmentHistory(LayoutShipmentHistoryBinding LayoutShipmentHistory, int fieldId) {
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
        String dataName = null;
        DrawerItem data = this.mData;
        if (!((dirtyFlags & 12) == 0 || data == null)) {
            dataName = data.getName();
        }
        if ((dirtyFlags & 12) != 0) {
            TextViewBindingAdapter.setText(this.txtDrawerText, dataName);
        }
        executeBindingsOn(this.layoutLang);
        executeBindingsOn(this.layoutShipmentHistory);
    }

    @NonNull
    public static RawDrawerLayoutBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawDrawerLayoutBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (RawDrawerLayoutBinding) DataBindingUtil.inflate(inflater, R.layout.raw_drawer_layout, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static RawDrawerLayoutBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawDrawerLayoutBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.raw_drawer_layout, null, false), bindingComponent);
    }

    @NonNull
    public static RawDrawerLayoutBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static RawDrawerLayoutBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/raw_drawer_layout_0".equals(view.getTag())) {
            return new RawDrawerLayoutBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
