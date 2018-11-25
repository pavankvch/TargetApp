package kuwait.com.targetlogistics.history;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import java.util.ArrayList;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.databinding.RawHistoryHeaderBinding;
import kuwait.com.targetlogistics.databinding.RawHistoryListBinding;
import kuwait.com.targetlogistics.model.Value;

public class HistoryAdapter extends Adapter<ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private Context context;
    private String currentFragment;
    private String from;
    private ArrayList<Value> historyList;
    private boolean isPending = false;

    class HeaderViewHolder extends ViewHolder {
        RawHistoryHeaderBinding binding;

        public HeaderViewHolder(RawHistoryHeaderBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    class HistoryListViewHolder extends ViewHolder {
        RawHistoryListBinding binding;

        public HistoryListViewHolder(RawHistoryListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public HistoryAdapter(ArrayList<Value> historyLists, Context context, String currentFragment, String from) {
        this.historyList = historyLists;
        this.context = context;
        this.currentFragment = currentFragment;
        this.from = from;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new HeaderViewHolder((RawHistoryHeaderBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.raw_history_header, parent, false));
        }
        if (viewType != 1) {
            return null;
        }
        RawHistoryListBinding rawHistoryList = (RawHistoryListBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.raw_history_list, parent, false);
        if (this.currentFragment.equals("ExportCompleted") || this.currentFragment.equals("DeliveryCompleted") || this.currentFragment.equals("ImportCompleted")) {
            rawHistoryList.relDriver.setVisibility(0);
            this.isPending = false;
        } else {
            this.isPending = true;
            rawHistoryList.relDriver.setVisibility(8);
            rawHistoryList.relPendingShipmentCost.setVisibility(8);
            rawHistoryList.relWeight.setVisibility(8);
        }
        if (this.from.equals("Import")) {
            rawHistoryList.relDelivery.setVisibility(8);
        } else {
            rawHistoryList.relDelivery.setVisibility(0);
        }
        if (this.from.equals("Delivery")) {
            rawHistoryList.relParcel.setVisibility(8);
        } else {
            rawHistoryList.relParcel.setVisibility(0);
        }
        return new HistoryListViewHolder(rawHistoryList);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).binding.setData((Value) this.historyList.get(position));
            ((HeaderViewHolder) holder).binding.executePendingBindings();
        } else if (holder instanceof HistoryListViewHolder) {
            final Value value = (Value) this.historyList.get(position);
            ((HistoryListViewHolder) holder).binding.setData(value);
            ((HistoryListViewHolder) holder).binding.executePendingBindings();
            if (this.from.equalsIgnoreCase("Import")) {
                ((HistoryListViewHolder) holder).binding.txtCity.setText(value.getDeliveryCity());
            } else {
                ((HistoryListViewHolder) holder).binding.txtCity.setText(value.getPickupCity());
            }
            ((HistoryListViewHolder) holder).binding.getRoot().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    HistoryAdapter.this.context.startActivity(new Intent(HistoryAdapter.this.context, HistoryDetailActivity.class).putExtra("HistoryType", HistoryAdapter.this.from).putExtra("data", value).putExtra("isPending", HistoryAdapter.this.isPending));
                }
            });
        }
    }

    public int getItemCount() {
        return this.historyList.size();
    }

    public int getItemViewType(int position) {
        if (((Value) this.historyList.get(position)).isHeader()) {
            return 0;
        }
        return 1;
    }
}
