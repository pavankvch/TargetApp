package kuwait.com.targetlogistics.export;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import java.util.ArrayList;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.databinding.ActivityExportAddressListBinding;
import kuwait.com.targetlogistics.history.HistoryActivity;
import kuwait.com.targetlogistics.home.HomeActivity;
import kuwait.com.targetlogistics.interfaces.OnDialog;
import kuwait.com.targetlogistics.model.HistoryList;

public class ExportAddressListActivity extends BaseActivity {
    private ExportAddressListAdapter adapter;
    ActivityExportAddressListBinding binding;
    private ArrayList<HistoryList> list;
    private Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityExportAddressListBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        this.list = new ArrayList();
        this.mContext = this;
        setTitle(getString(R.string.export));
        setAddressListHeader();
        this.baseBinding.imgAddAddress.setOnClickListener(this);
        this.binding.btnSubmitOrder.setOnClickListener(this);
        getData();
        LayoutManager layoutManager = new LinearLayoutManager(this.me, 1, false);
        this.binding.recExportAddressList.setHasFixedSize(true);
        this.binding.recExportAddressList.setLayoutManager(layoutManager);
        this.adapter = new ExportAddressListAdapter(this.me, this.list);
        this.binding.recExportAddressList.setAdapter(this.adapter);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_submit_order:
                if (!getRedirectClass().equals(HistoryActivity.class)) {
                    showSimpleDialog(getString(R.string.order_success_message), new OnDialog() {
                        public void onOk() {
                            ExportAddressListActivity.this.startActivity(new Intent(ExportAddressListActivity.this.me, HomeActivity.class));
                            ExportAddressListActivity.this.finishAffinity();
                        }
                    });
                    return;
                }
                return;
            case R.id.img_add_address:
                startActivity(new Intent(this, ExportPickupAddressActivity.class));
                onFinishAll(new Class[]{ExportPickupAddressActivity.class, ExportDeliveryAddressActivity.class, ExportParcelInfoActivity.class, ExportPickUpDateTimeActivity.class});
                finish();
                return;
            default:
                return;
        }
    }

    private void getData() {
        HistoryList addres = new HistoryList();
        addres.setPickupCity("Kuwait City");
        addres.setDeliveryCity("India");
        addres.setNoOfParcels("3");
        addres.setWeight("5 Kgs");
        this.list.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Hawally");
        addres.setDeliveryCity("Dubai");
        addres.setNoOfParcels("2");
        addres.setWeight("7 Kgs");
        this.list.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Dasma");
        addres.setDeliveryCity("Qatar");
        addres.setNoOfParcels("5");
        addres.setWeight("12 Kgs");
        this.list.add(addres);
        addres = new HistoryList();
        addres.setPickupCity("Salmiya");
        addres.setDeliveryCity("Egypt");
        addres.setNoOfParcels("1");
        addres.setWeight("10 Kgs");
        this.list.add(addres);
    }
}
