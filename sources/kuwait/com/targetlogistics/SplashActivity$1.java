package kuwait.com.targetlogistics;

import kuwait.com.targetlogistics.common.Utils;

class SplashActivity$1 implements AbstractBaseActivity$onCancel {
    final /* synthetic */ SplashActivity this$0;

    SplashActivity$1(SplashActivity this$0) {
        this.this$0 = this$0;
    }

    public void onCancel() {
        if (!Utils.getLanguage(this.this$0.me).isEmpty()) {
            SplashActivity.access$000(this.this$0);
        }
    }
}
