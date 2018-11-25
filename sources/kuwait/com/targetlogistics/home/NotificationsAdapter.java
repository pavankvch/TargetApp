package kuwait.com.targetlogistics.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.databinding.RawNotificationsListBinding;
import kuwait.com.targetlogistics.model.Notification;

public class NotificationsAdapter extends Adapter<VeiwHolder> {
    Context context;
    ArrayList<Notification> list;

    public class VeiwHolder extends ViewHolder {
        RawNotificationsListBinding binding;

        public VeiwHolder(RawNotificationsListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public NotificationsAdapter(BaseActivity context, ArrayList<Notification> list) {
        this.context = context;
        this.list = list;
    }

    public VeiwHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VeiwHolder((RawNotificationsListBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.raw_notifications_list, parent, false));
    }

    public void onBindViewHolder(VeiwHolder holder, int position) {
        Notification notification = (Notification) this.list.get(position);
        notification.setDescriptionEn(Html.fromHtml(notification.getDescriptionEn()).toString());
        notification.setDescriptionAr(Html.fromHtml(notification.getDescriptionAr()).toString());
        holder.binding.setData(notification);
        holder.binding.executePendingBindings();
    }

    public int getItemCount() {
        return this.list.size();
    }
}
