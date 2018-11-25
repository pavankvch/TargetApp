package kuwait.com.targetlogistics;

import android.content.Intent;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import kuwait.com.targetlogistics.BaseActivity.DrawerRecyclerAdapter;
import kuwait.com.targetlogistics.home.AboutAppActivity;
import kuwait.com.targetlogistics.home.AddressBookActivity;
import kuwait.com.targetlogistics.home.ContactUsActivity;
import kuwait.com.targetlogistics.home.NotificationsActivity;
import kuwait.com.targetlogistics.home.PriceListActivity;
import kuwait.com.targetlogistics.home.TermsConditionsActivity;
import kuwait.com.targetlogistics.interfaces.OnConfirmationDialog;
import kuwait.com.targetlogistics.user.ChangePasswordActivity;
import kuwait.com.targetlogistics.user.MyProfileActivity;

class BaseActivity$DrawerRecyclerAdapter$5 implements OnClickListener {
    final /* synthetic */ DrawerRecyclerAdapter this$1;
    final /* synthetic */ ViewHolder val$holder;

    BaseActivity$DrawerRecyclerAdapter$5(DrawerRecyclerAdapter this$1, ViewHolder viewHolder) {
        this.this$1 = this$1;
        this.val$holder = viewHolder;
    }

    public void onClick(View v) {
        switch (BaseActivity$3.$SwitchMap$kuwait$com$targetlogistics$model$DrawerEnum[((BaseActivity$DrawerRecyclerAdapter$ViewHolder) this.val$holder).binding.getData().getDrawerEnum().ordinal()]) {
            case 1:
                this.this$1.this$0.closeDrawer();
                return;
            case 2:
                this.this$1.this$0.startActivity(new Intent(this.this$1.this$0.me, MyProfileActivity.class));
                this.this$1.this$0.closeDrawer();
                return;
            case 3:
                this.this$1.this$0.startActivity(new Intent(this.this$1.this$0.me, AddressBookActivity.class));
                this.this$1.this$0.closeDrawer();
                return;
            case 4:
                this.this$1.this$0.startActivity(new Intent(this.this$1.this$0.me, PriceListActivity.class));
                this.this$1.this$0.closeDrawer();
                return;
            case 5:
                this.this$1.this$0.startActivity(new Intent(this.this$1.this$0.me, ChangePasswordActivity.class));
                this.this$1.this$0.closeDrawer();
                return;
            case 7:
                this.this$1.this$0.startActivity(new Intent(this.this$1.this$0.me, NotificationsActivity.class));
                this.this$1.this$0.closeDrawer();
                return;
            case 8:
                this.this$1.this$0.startActivity(new Intent(this.this$1.this$0.me, AboutAppActivity.class));
                this.this$1.this$0.closeDrawer();
                return;
            case 9:
                this.this$1.this$0.startActivity(new Intent(this.this$1.this$0.me, TermsConditionsActivity.class));
                this.this$1.this$0.closeDrawer();
                return;
            case 10:
                this.this$1.this$0.startActivity(new Intent(this.this$1.this$0.me, ContactUsActivity.class));
                this.this$1.this$0.closeDrawer();
                return;
            case 11:
                this.this$1.this$0.closeDrawer();
                this.this$1.this$0.showConfirmationDialog(this.this$1.this$0.getString(R.string.are_you_sure_want_to_logout), new OnConfirmationDialog() {
                    public void onYes() {
                        BaseActivity.access$300(BaseActivity$DrawerRecyclerAdapter$5.this.this$1.this$0);
                    }

                    public void onNo() {
                        BaseActivity$DrawerRecyclerAdapter$5.this.this$1.this$0.dismissDialog();
                    }
                });
                return;
            case 12:
                if (((BaseActivity$DrawerRecyclerAdapter$ViewHolder) this.val$holder).binding.linShipment.getVisibility() == 0) {
                    this.this$1.this$0.closeDrawer();
                    return;
                } else {
                    ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) this.val$holder).binding.linShipment.setVisibility(0);
                    return;
                }
            default:
                return;
        }
    }
}
