package kuwait.com.targetlogistics.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import kuwait.com.targetlogistics.AbstractBaseActivity;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityHomeBinding;
import kuwait.com.targetlogistics.delivery_logistics.DeliveryPickupAddressActivity;
import kuwait.com.targetlogistics.export.ExportPickupAddressActivity;
import kuwait.com.targetlogistics.import_logistics.ImportStep1Activity;
import org.greenrobot.eventbus.Subscribe;

public class HomeActivity extends BaseActivity {
    private ActivityHomeBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityHomeBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setNavigationView();
        AbstractBaseActivity.setRedirectClass(HomeActivity.class);
        setHomeHeader();
        if (Utils.getPickupStart(this.me).equals("1") || Utils.getPickupStart(this.me).equals("4")) {
            this.binding.txtDeliveryInProgress.setVisibility(0);
        } else {
            this.binding.txtDeliveryInProgress.setVisibility(8);
        }
        this.binding.frameExport.setOnClickListener(this);
        this.binding.frameImport.setOnClickListener(this);
        this.binding.frameDelivery.setOnClickListener(this);
        this.binding.txtDeliveryInProgress.setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
        AbstractBaseActivity.setRedirectClass(HomeActivity.class);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.frame_delivery:
                startActivity(new Intent(this, DeliveryPickupAddressActivity.class));
                return;
            case R.id.frame_export:
                startActivity(new Intent(this, ExportPickupAddressActivity.class));
                return;
            case R.id.frame_import:
                startActivity(new Intent(this, ImportStep1Activity.class));
                return;
            case R.id.txt_delivery_in_progress:
                startActivity(new Intent(this, TrackDeliveryActivity.class));
                HomeActivity$1 homeActivity$1 = new HomeActivity$1(this);
                return;
            default:
                return;
        }
    }

    @Subscribe
    public void onMessageReceived(Intent i) {
        if (i.getAction() != null && i.getAction().equalsIgnoreCase("notification")) {
            if (Utils.getPickupStart(this.me).equals("1") || Utils.getPickupStart(this.me).equals("4")) {
                runOnUiThread(new HomeActivity$2(this));
            } else {
                runOnUiThread(new HomeActivity$3(this));
            }
            if (!Utils.getAndroidVersion(this.me).equalsIgnoreCase(Utils.getVersionName(this.me))) {
                showVersionDialog("", getString(R.string.new_version_message), this.me, null);
            }
        }
    }
}
