package kuwait.com.targetlogistics.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import kuwait.com.targetlogistics.MaterialSearchView;
import kuwait.com.targetlogistics.R;

public class ActivityBaseBinding extends ViewDataBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @NonNull
    public final DrawerLayout drawerLayout;
    @NonNull
    public final LinearLayout drawerListLayout;
    @NonNull
    public final RecyclerView drawerRecycler;
    @NonNull
    public final FrameLayout frmContainer;
    @NonNull
    public final ImageView imgAddAddress;
    @NonNull
    public final ImageView imgHeader;
    private long mDirtyFlags = -1;
    @NonNull
    public final MaterialSearchView mSearchView;
    @NonNull
    public final Toolbar mToolbar;
    @NonNull
    public final TextView toolbarTitle;
    @NonNull
    public final TextView txtCall;
    @NonNull
    public final TextView txtHotlineNo;
    @NonNull
    public final TextView txtWhatsapp;
    @NonNull
    public final TextView txtWhatsappNo;

    static {
        sViewsWithIds.put(R.id.mToolbar, 1);
        sViewsWithIds.put(R.id.toolbar_title, 2);
        sViewsWithIds.put(R.id.img_header, 3);
        sViewsWithIds.put(R.id.img_add_address, 4);
        sViewsWithIds.put(R.id.mSearchView, 5);
        sViewsWithIds.put(R.id.frmContainer, 6);
        sViewsWithIds.put(R.id.drawerListLayout, 7);
        sViewsWithIds.put(R.id.drawerRecycler, 8);
        sViewsWithIds.put(R.id.txt_whatsapp, 9);
        sViewsWithIds.put(R.id.txt_whatsapp_no, 10);
        sViewsWithIds.put(R.id.txt_call, 11);
        sViewsWithIds.put(R.id.txt_hotline_no, 12);
    }

    public ActivityBaseBinding(@NonNull DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        Object[] bindings = mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.drawerLayout = (DrawerLayout) bindings[0];
        this.drawerLayout.setTag(null);
        this.drawerListLayout = (LinearLayout) bindings[7];
        this.drawerRecycler = (RecyclerView) bindings[8];
        this.frmContainer = (FrameLayout) bindings[6];
        this.imgAddAddress = (ImageView) bindings[4];
        this.imgHeader = (ImageView) bindings[3];
        this.mSearchView = (MaterialSearchView) bindings[5];
        this.mToolbar = (Toolbar) bindings[1];
        this.toolbarTitle = (TextView) bindings[2];
        this.txtCall = (TextView) bindings[11];
        this.txtHotlineNo = (TextView) bindings[12];
        this.txtWhatsapp = (TextView) bindings[9];
        this.txtWhatsappNo = (TextView) bindings[10];
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
    public static ActivityBaseBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBaseBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent bindingComponent) {
        return (ActivityBaseBinding) DataBindingUtil.inflate(inflater, R.layout.activity_base, root, attachToRoot, bindingComponent);
    }

    @NonNull
    public static ActivityBaseBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBaseBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(R.layout.activity_base, null, false), bindingComponent);
    }

    @NonNull
    public static ActivityBaseBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityBaseBinding bind(@NonNull View view, @Nullable DataBindingComponent bindingComponent) {
        if ("layout/activity_base_0".equals(view.getTag())) {
            return new ActivityBaseBinding(bindingComponent, view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
