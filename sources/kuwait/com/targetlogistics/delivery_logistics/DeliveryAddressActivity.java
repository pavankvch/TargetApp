package kuwait.com.targetlogistics.delivery_logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import kuwait.com.targetlogistics.AbstractBaseActivity;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.MapActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityDeliveryAddressBinding;
import kuwait.com.targetlogistics.model.Area;
import kuwait.com.targetlogistics.searchable.SearchActivity;
import org.greenrobot.eventbus.Subscribe;

public class DeliveryAddressActivity extends BaseActivity {
    private static int mapResult = 102;
    private static int searchResult = 101;
    ActivityDeliveryAddressBinding binding;
    private double selectedLat;
    private double selectedLon;

    protected void onStart() {
        super.onStart();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityDeliveryAddressBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        this.binding.setData(delivery);
        this.binding.executePendingBindings();
        init();
    }

    private void init() {
        setTitle(getString(R.string.delivery));
        this.binding.layoutSteps.txtDeliveryAddress.setBackground(ContextCompat.getDrawable(this.me, R.drawable.ic_round_shape_red));
        this.binding.layoutSteps.txtParcelInfo.setText(getString(R.string.vehicle_info));
        this.binding.tILArea.setOnClickListener(this);
        this.binding.linBack.setOnClickListener(this);
        this.binding.linSkip.setOnClickListener(this);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.lin_back:
                super.onBackPressed();
                return;
            case R.id.lin_skip:
                if (this.binding.textArea.getText().toString().isEmpty()) {
                    showSnackBar(this.me, getString(R.string.please_select_area));
                    return;
                } else {
                    startActivity(new Intent(this.me, DeliveryVehicleInfoActivity.class));
                    return;
                }
            case R.id.tILArea:
                startActivityForResult(new Intent(this.me, SearchActivity.class), searchResult);
                AbstractBaseActivity.setRedirectClass(DeliveryAddressActivity.class);
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == -1) {
            if (requestCode == searchResult) {
                Area data1 = (Area) data.getSerializableExtra("Area");
                this.binding.textArea.setText(data1.getAreaName());
                String areaId = Utils.getAreaId(data1.getAreaName(), this.me);
                Utils.printMsg("areaId", areaId);
                delivery.setAreaId(areaId);
                Intent intent = new Intent(this, MapActivity.class);
                intent.putExtra("Area", data1);
                startActivityForResult(intent, mapResult);
            } else if (requestCode == mapResult) {
                this.selectedLat = data.getDoubleExtra("strLat", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                this.selectedLon = data.getDoubleExtra("strLong", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                String street = data.getStringExtra("street");
                String block = data.getStringExtra("block");
                if (block == null || !block.matches("[0-9]+")) {
                    this.binding.edtBlock.setEnabled(true);
                } else {
                    this.binding.edtBlock.setText(block);
                    this.binding.edtBlock.setEnabled(false);
                }
                this.binding.edtStreetNumber.setText(street);
            }
        }
    }

    @Subscribe
    public void onMessageReceived(Intent i) {
        if (i.hasExtra("Reorder")) {
            boolean isReorder = i.getBooleanExtra("Reorder", false);
            Utils.printMsg("Reorder", isReorder + "");
            if (isReorder) {
                this.binding.textArea.setText("");
                this.binding.edtPhone.setText("");
                this.binding.edtAddressName.setText("");
                this.binding.edtBlock.setText("");
                this.binding.edtStreetNumber.setText("");
                this.binding.edtHouse.setText("");
                this.binding.edtApartment.setText("");
                this.binding.edtFloor.setText("");
                this.binding.edtJadda.setText("");
                this.binding.edtPickupInstructions.setText("");
                this.binding.edtPhone.requestFocus();
            }
        }
    }
}
