package kuwait.com.targetlogistics;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import kuwait.com.targetlogistics.interfaces.OnKeyboardVisibilityListener;

class AbstractBaseActivity$6 implements OnGlobalLayoutListener {
    private final int DefaultKeyboardDP = 100;
    private final int EstimatedKeyboardDP;
    private final Rect r;
    final /* synthetic */ AbstractBaseActivity this$0;
    final /* synthetic */ View val$activityRootView;
    final /* synthetic */ OnKeyboardVisibilityListener val$listener;
    private boolean wasOpened;

    AbstractBaseActivity$6(AbstractBaseActivity this$0, View view, OnKeyboardVisibilityListener onKeyboardVisibilityListener) {
        this.this$0 = this$0;
        this.val$activityRootView = view;
        this.val$listener = onKeyboardVisibilityListener;
        this.EstimatedKeyboardDP = (VERSION.SDK_INT >= 21 ? 48 : 0) + 100;
        this.r = new Rect();
    }

    public void onGlobalLayout() {
        boolean isShown = true;
        int estimatedKeyboardHeight = (int) TypedValue.applyDimension(1, (float) this.EstimatedKeyboardDP, this.val$activityRootView.getResources().getDisplayMetrics());
        this.val$activityRootView.getWindowVisibleDisplayFrame(this.r);
        if (this.val$activityRootView.getRootView().getHeight() - (this.r.bottom - this.r.top) < estimatedKeyboardHeight) {
            isShown = false;
        }
        if (isShown != this.wasOpened) {
            this.wasOpened = isShown;
            if (this.val$listener != null) {
                this.val$listener.onVisibilityChanged(isShown);
            }
        }
    }
}
