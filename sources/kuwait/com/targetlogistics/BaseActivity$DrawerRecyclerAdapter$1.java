package kuwait.com.targetlogistics;

import android.view.View;
import android.view.View.OnClickListener;
import kuwait.com.targetlogistics.BaseActivity.DrawerRecyclerAdapter;
import kuwait.com.targetlogistics.common.Utils;

class BaseActivity$DrawerRecyclerAdapter$1 implements OnClickListener {
    final /* synthetic */ DrawerRecyclerAdapter this$1;

    BaseActivity$DrawerRecyclerAdapter$1(DrawerRecyclerAdapter this$1) {
        this.this$1 = this$1;
    }

    public void onClick(View view) {
        if (Utils.getLanguage(this.this$1.this$0.me).equals("en")) {
            Utils.setLanguage(this.this$1.this$0.me, "ar");
            Utils.setLanguageDirection(this.this$1.this$0.me, "rtl");
            MyApplication.isLTR = Utils.getLanguageDirection(this.this$1.this$0.me).equalsIgnoreCase("ltr");
            BaseActivity.access$200(this.this$1.this$0);
            return;
        }
        Utils.setLanguage(this.this$1.this$0.me, "en");
        Utils.setLanguageDirection(this.this$1.this$0.me, "ltr");
        MyApplication.isLTR = Utils.getLanguageDirection(this.this$1.this$0.me).equalsIgnoreCase("ltr");
        BaseActivity.access$200(this.this$1.this$0);
    }
}
