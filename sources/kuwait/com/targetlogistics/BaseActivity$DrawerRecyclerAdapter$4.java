package kuwait.com.targetlogistics;

import android.content.Intent;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import kuwait.com.targetlogistics.BaseActivity.DrawerRecyclerAdapter;
import kuwait.com.targetlogistics.history.HistoryActivity;

class BaseActivity$DrawerRecyclerAdapter$4 implements OnClickListener {
    final /* synthetic */ DrawerRecyclerAdapter this$1;
    final /* synthetic */ ViewHolder val$holder;

    BaseActivity$DrawerRecyclerAdapter$4(DrawerRecyclerAdapter this$1, ViewHolder viewHolder) {
        this.this$1 = this$1;
        this.val$holder = viewHolder;
    }

    public void onClick(View view) {
        ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) this.val$holder).binding.linShipment.setVisibility(8);
        this.this$1.this$0.startActivity(new Intent(this.this$1.this$0.me, HistoryActivity.class).putExtra("HistoryType", "Delivery"));
        this.this$1.this$0.closeDrawer();
    }
}
