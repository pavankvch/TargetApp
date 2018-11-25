package kuwait.com.targetlogistics.import_logistics;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.OnItemSelected;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.LayoutImportStep2Binding;
import kuwait.com.targetlogistics.model.Parcel;

class ImportStep2Activity$ParcelInfoAdapter extends Adapter<ViewHolder> {
    final /* synthetic */ ImportStep2Activity this$0;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        LayoutImportStep2Binding binding;

        public ViewHolder(LayoutImportStep2Binding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    ImportStep2Activity$ParcelInfoAdapter(ImportStep2Activity this$0) {
        this.this$0 = this$0;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((LayoutImportStep2Binding) DataBindingUtil.inflate(LayoutInflater.from(this.this$0.me), R.layout.layout_import_step2, parent, false));
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Parcel parcel = (Parcel) ImportStep2Activity.access$000(this.this$0).get(position);
        holder.binding.edtParcelDetailsDescription.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (view.getId() == R.id.edtParcelDetailsDescription) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & 255) {
                        case 1:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });
        holder.binding.txtParcelType.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ImportStep2Activity$ParcelInfoAdapter.this.this$0.showParcelTypeDialog(Utils.getParcelTypeList(ImportStep2Activity$ParcelInfoAdapter.this.this$0.me), ImportStep2Activity$ParcelInfoAdapter.this.this$0.me, ImportStep2Activity$ParcelInfoAdapter.this.this$0.getString(R.string.select_parcel_type), new OnItemSelected() {
                    public void onItemSelected(Object o) {
                        if (o != null) {
                            parcel.setParcelType(o.toString());
                            parcel.setParcelTypeId(ImportStep2Activity.access$100(ImportStep2Activity$ParcelInfoAdapter.this.this$0, o.toString()));
                            holder.binding.txtParcelType.setText(o.toString());
                        }
                    }
                });
            }
        });
        if (ImportStep2Activity.access$000(this.this$0).size() == 1) {
            holder.binding.btnAddMoreParcel.setVisibility(0);
            holder.binding.btnRemoveParcel.setVisibility(8);
        } else if (position == ImportStep2Activity.access$000(this.this$0).size() - 1) {
            holder.binding.btnAddMoreParcel.setVisibility(0);
            holder.binding.btnRemoveParcel.setVisibility(0);
        } else {
            holder.binding.btnAddMoreParcel.setVisibility(8);
            holder.binding.btnRemoveParcel.setVisibility(0);
        }
        holder.binding.btnAddMoreParcel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (holder.binding.btnAddMoreParcel.getText().toString().equals(ImportStep2Activity$ParcelInfoAdapter.this.this$0.getString(R.string.remove_parcel))) {
                    ImportStep2Activity.access$000(ImportStep2Activity$ParcelInfoAdapter.this.this$0).remove(holder.getAdapterPosition());
                    ImportStep2Activity$ParcelInfoAdapter.this.notifyDataSetChanged();
                } else if (holder.binding.txtParcelType.getText().toString().isEmpty()) {
                    ImportStep2Activity$ParcelInfoAdapter.this.this$0.showSnackBar(ImportStep2Activity$ParcelInfoAdapter.this.this$0.me, ImportStep2Activity$ParcelInfoAdapter.this.this$0.getString(R.string.parcel_type_validation));
                } else if (holder.binding.edtQuantity.getText().toString().isEmpty()) {
                    ImportStep2Activity$ParcelInfoAdapter.this.this$0.showSnackBar(ImportStep2Activity$ParcelInfoAdapter.this.this$0.me, ImportStep2Activity$ParcelInfoAdapter.this.this$0.getString(R.string.quantity_validation));
                } else if (holder.binding.edtCostValue.getText().toString().isEmpty()) {
                    ImportStep2Activity$ParcelInfoAdapter.this.this$0.showSnackBar(ImportStep2Activity$ParcelInfoAdapter.this.this$0.me, ImportStep2Activity$ParcelInfoAdapter.this.this$0.getString(R.string.cost_validation));
                } else {
                    parcel.setAdded(true);
                    ImportStep2Activity.access$000(ImportStep2Activity$ParcelInfoAdapter.this.this$0).add(new Parcel());
                    ImportStep2Activity$ParcelInfoAdapter.this.notifyDataSetChanged();
                }
            }
        });
        holder.binding.btnRemoveParcel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ImportStep2Activity.access$000(ImportStep2Activity$ParcelInfoAdapter.this.this$0).remove(position);
                ImportStep2Activity$ParcelInfoAdapter.this.notifyDataSetChanged();
            }
        });
        holder.binding.setData(parcel);
        holder.binding.executePendingBindings();
    }

    public int getItemCount() {
        return ImportStep2Activity.access$000(this.this$0).size();
    }
}
