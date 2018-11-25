package kuwait.com.targetlogistics;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import kuwait.com.targetlogistics.common.Utils;

class BaseActivity$1 extends ActionBarDrawerToggle {
    final /* synthetic */ BaseActivity this$0;

    BaseActivity$1(BaseActivity this$0, Activity arg0, DrawerLayout arg1, Toolbar arg2, int arg3, int arg4) {
        this.this$0 = this$0;
        super(arg0, arg1, arg2, arg3, arg4);
    }

    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        Utils.hideSoftKeyboard(this.this$0.me);
    }
}
