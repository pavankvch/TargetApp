package kuwait.com.targetlogistics;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import java.util.ArrayList;
import kuwait.com.targetlogistics.common.OnItemSelected;
import kuwait.com.targetlogistics.databinding.RawParcelTypeBinding;
import kuwait.com.targetlogistics.export.ExportParcelInfoActivity;
import kuwait.com.targetlogistics.import_logistics.ImportStep2Activity;
import kuwait.com.targetlogistics.model.ParcelType;

class AbstractBaseActivity$ParcelTypeAdapter extends Adapter<ViewHolder> {
    private Context context;
    private ArrayList<?> list;
    OnItemSelected onItemSelected;
    final /* synthetic */ AbstractBaseActivity this$0;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        RawParcelTypeBinding binding;

        public ViewHolder(RawParcelTypeBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public AbstractBaseActivity$ParcelTypeAdapter(AbstractBaseActivity this$0, Context context, ArrayList<?> list, OnItemSelected onItemSelected) {
        this.this$0 = this$0;
        this.context = context;
        this.list = list;
        this.onItemSelected = onItemSelected;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(RawParcelTypeBinding.inflate(LayoutInflater.from(this.context), parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        String finalParcelType = "";
        if (AbstractBaseActivity.getRedirectClass().equals(ExportParcelInfoActivity.class) || AbstractBaseActivity.getRedirectClass().equals(ImportStep2Activity.class)) {
            ParcelType parcelType = (ParcelType) this.list.get(position);
            finalParcelType = parcelType.getParcelType();
            holder.binding.setData(parcelType.getParcelType());
            finalParcelType = parcelType.getParcelType();
        } else {
            holder.binding.setData((String) this.list.get(position));
            finalParcelType = (String) this.list.get(position);
        }
        holder.binding.executePendingBindings();
        final String finalParcelType1 = finalParcelType;
        holder.binding.getRoot().setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AbstractBaseActivity.access$000(AbstractBaseActivity$ParcelTypeAdapter.this.this$0).dismiss();
                if (AbstractBaseActivity$ParcelTypeAdapter.this.onItemSelected != null) {
                    AbstractBaseActivity$ParcelTypeAdapter.this.onItemSelected.onItemSelected(finalParcelType1);
                }
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }
}
