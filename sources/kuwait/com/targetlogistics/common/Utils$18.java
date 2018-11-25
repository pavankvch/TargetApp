package kuwait.com.targetlogistics.common;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class Utils$18 extends Animation {
    final /* synthetic */ int val$targtetHeight;
    final /* synthetic */ View val$v;

    Utils$18(View view, int i) {
        this.val$v = view;
        this.val$targtetHeight = i;
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        this.val$v.getLayoutParams().height = interpolatedTime == 1.0f ? -2 : (int) (((float) this.val$targtetHeight) * interpolatedTime);
        this.val$v.requestLayout();
    }

    public boolean willChangeBounds() {
        return true;
    }
}
