package kuwait.com.targetlogistics.export;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import kuwait.com.targetlogistics.AbstractBaseActivity;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityExportDeliveryAddressBinding;
import kuwait.com.targetlogistics.model.City;
import kuwait.com.targetlogistics.model.Country;
import kuwait.com.targetlogistics.model.Export;
import kuwait.com.targetlogistics.searchable.SearchActivity;
import org.greenrobot.eventbus.Subscribe;

public class ExportDeliveryAddressActivity extends BaseActivity {
    private static int searchCityResult = 101;
    private static int searchCountryResult = 102;
    ActivityExportDeliveryAddressBinding binding;
    private String cityId = "";
    private String countryId = "";
    private Export export;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityExportDeliveryAddressBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.export));
        if (getIntent().hasExtra("data")) {
            this.export = (Export) getIntent().getSerializableExtra("data");
            this.binding.setData(this.export);
            this.binding.executePendingBindings();
        }
        this.binding.layoutSteps.txtDeliveryAddress.setBackground(ContextCompat.getDrawable(this.me, R.drawable.ic_round_shape_red));
        this.binding.layoutSteps.txtStep3.setText(getString(R.string.shipping_to));
        this.binding.linBack.setOnClickListener(this);
        this.binding.linSkip.setOnClickListener(this);
        this.binding.txtCountry.setOnClickListener(this);
        this.binding.txtCity.setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
        if (this.export != null) {
            setData();
        }
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.lin_back:
                super.onBackPressed();
                return;
            case R.id.lin_skip:
                if (validation()) {
                    Intent intent1 = new Intent(this.me, ExportParcelInfoActivity.class);
                    intent1.putExtra("data", this.export);
                    setData();
                    if (getIntent().hasExtra("HistoryType")) {
                        intent1.putExtra("HistoryType", getIntent().getStringExtra("HistoryType"));
                    }
                    startActivity(intent1);
                    return;
                }
                return;
            case R.id.txtCity:
                if (this.countryId.isEmpty()) {
                    showSnackBar(this.me, getResources().getString(R.string.select_country_first));
                    return;
                }
                Intent i = new Intent(this.me, SearchActivity.class);
                i.putExtra("City", false);
                i.putExtra("countryId", this.countryId);
                startActivityForResult(i, searchCityResult);
                AbstractBaseActivity.setRedirectClass(ExportDeliveryAddressActivity.class);
                return;
            case R.id.txtCountry:
                Intent intent = new Intent(this.me, SearchActivity.class);
                intent.putExtra("Country", true);
                startActivityForResult(intent, searchCountryResult);
                AbstractBaseActivity.setRedirectClass(ExportDeliveryAddressActivity.class);
                return;
            default:
                return;
        }
    }

    private boolean validation() {
        if (this.binding.edtFullName.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_full_name));
            return false;
        } else if (this.binding.edtPhone.getText().toString().isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_enter_phone_no));
            return false;
        } else if (this.countryId.isEmpty()) {
            showSnackBar(this.me, getString(R.string.please_select_country));
            return false;
        } else if (!this.cityId.isEmpty()) {
            return true;
        } else {
            showSnackBar(this.me, getString(R.string.please_select_city));
            return false;
        }
    }

    private void setData() {
        this.export.setCityId(this.cityId);
        this.export.setCountryId(this.countryId);
        this.binding.setData(this.export);
        this.binding.executePendingBindings();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == -1) {
            if (requestCode == searchCityResult) {
                City data1 = (City) data.getSerializableExtra("City");
                this.binding.txtCity.setText(data1.getCityName());
                this.cityId = Utils.getCityId(data1.getCityName(), this.me);
                Utils.printMsg("cityId", this.cityId);
            } else if (requestCode == searchCountryResult) {
                Country data12 = (Country) data.getSerializableExtra("Country");
                this.binding.txtCountry.setText(data12.getCountryName());
                this.countryId = Utils.getCountryId(data12.getCountryName(), this.me);
                this.binding.txtCode.setText("+" + data12.getCountryCode());
                Utils.printMsg("countryId", this.countryId);
            }
        }
    }

    @Subscribe
    public void onMessageReceived(Intent i) {
        if (i.hasExtra("ReorderExport")) {
            boolean isReorder = i.getBooleanExtra("ReorderExport", false);
            Utils.printMsg("Reorder", isReorder + "");
            if (isReorder) {
                this.binding.edtFullName.setText("");
                this.binding.edtPhone.setText("");
                this.binding.txtCountry.setText("");
                this.binding.txtCity.setText("");
                this.binding.txtCode.setText("");
                this.binding.edtAddress1.setText("");
                this.binding.edtAddress2.setText("");
                this.binding.edtPostalCode.setText("");
                this.binding.edtFullName.requestFocus();
                this.countryId = "";
                this.cityId = "";
            }
        }
    }
}
