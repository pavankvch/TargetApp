package kuwait.com.targetlogistics.delivery_logistics;

import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import java.util.ArrayList;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.RawVehicleTypeBinding;
import kuwait.com.targetlogistics.model.VehicleType;

public class VehicleTypeAdapter extends Adapter<ViewHolder> {
    private boolean isFromHistory = false;
    private int lastPosition = -1;
    private BaseActivity mContext;
    private ArrayList<VehicleType> vehicleTypeList;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        RawVehicleTypeBinding binding;

        public ViewHolder(RawVehicleTypeBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public VehicleTypeAdapter(BaseActivity mContext, ArrayList<VehicleType> vehicleTypeList, boolean isFromHistory) {
        this.mContext = mContext;
        this.vehicleTypeList = vehicleTypeList;
        this.isFromHistory = isFromHistory;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((RawVehicleTypeBinding) DataBindingUtil.inflate(LayoutInflater.from(this.mContext), R.layout.raw_vehicle_type, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        final VehicleType vehicleType = (VehicleType) this.vehicleTypeList.get(position);
        holder.binding.setData(vehicleType);
        holder.binding.executePendingBindings();
        if (this.lastPosition == position) {
            holder.binding.cbVehicleType.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.ic_selected));
        } else {
            holder.binding.cbVehicleType.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.ic_unselected));
        }
        if (BaseActivity.delivery.getVehicleId() != null && vehicleType.getVehicleId().equals(BaseActivity.delivery.getVehicleId())) {
            holder.binding.cbVehicleType.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.ic_selected));
        }
        if (this.isFromHistory) {
            Utils.setEnable(holder.binding.getRoot(), false);
        } else {
            holder.binding.getRoot().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    VehicleTypeAdapter.this.lastPosition = position;
                    BaseActivity.delivery.setVehicleId(vehicleType.getVehicleId());
                    VehicleTypeAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }

    public int getItemCount() {
        return this.vehicleTypeList.size();
    }
}
