package kuwait.com.targetlogistics.common;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class Utils$19 extends Animation {
    final /* synthetic */ int val$initialHeight;
    final /* synthetic */ View val$v;

    Utils$19(View view, int i) {
        this.val$v = view;
        this.val$initialHeight = i;
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        if (interpolatedTime == 1.0f) {
            this.val$v.setVisibility(8);
            return;
        }
        this.val$v.getLayoutParams().height = this.val$initialHeight - ((int) (((float) this.val$initialHeight) * interpolatedTime));
        this.val$v.requestLayout();
    }

    public boolean willChangeBounds() {
        return true;
    }
}
