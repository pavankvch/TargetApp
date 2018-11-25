package kuwait.com.targetlogistics.export;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import java.util.ArrayList;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.databinding.RawExportListBinding;
import kuwait.com.targetlogistics.interfaces.OnConfirmationDialog;
import kuwait.com.targetlogistics.model.HistoryList;

public class ExportAddressListAdapter extends Adapter<ViewHolder> {
    private BaseActivity context;
    private ArrayList<HistoryList> list;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements OnClickListener {
        RawExportListBinding binding;

        public ViewHolder(RawExportListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
            this.binding.imgEdit.setOnClickListener(this);
            this.binding.imgDelete.setOnClickListener(this);
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_delete:
                    ExportAddressListAdapter.this.context.showConfirmationDialog(ExportAddressListAdapter.this.context.getString(R.string.delete_confirmation_message), new OnConfirmationDialog() {
                        public void onYes() {
                            ExportAddressListAdapter.this.list.remove(ViewHolder.this.getAdapterPosition());
                            ExportAddressListAdapter.this.notifyDataSetChanged();
                        }

                        public void onNo() {
                            ExportAddressListAdapter.this.context.dismissDialog();
                        }
                    });
                    return;
                case R.id.img_edit:
                    ExportAddressListAdapter.this.context.startActivity(new Intent(ExportAddressListAdapter.this.context, ExportPickupAddressActivity.class));
                    return;
                default:
                    return;
            }
        }
    }

    public ExportAddressListAdapter(BaseActivity mContext, ArrayList<HistoryList> list) {
        this.context = mContext;
        this.list = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((RawExportListBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.raw_export_list, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setData((HistoryList) this.list.get(position));
        holder.binding.executePendingBindings();
    }

    public int getItemCount() {
        return this.list.size();
    }
}
