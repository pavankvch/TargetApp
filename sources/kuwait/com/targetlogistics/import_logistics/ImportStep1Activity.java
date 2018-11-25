package kuwait.com.targetlogistics.import_logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityImportStep1Binding;
import kuwait.com.targetlogistics.model.City;
import kuwait.com.targetlogistics.model.Country;
import kuwait.com.targetlogistics.model.Import;
import kuwait.com.targetlogistics.searchable.SearchActivity;

public class ImportStep1Activity extends BaseActivity {
    private static int searchCityResult = 101;
    private static int searchCountryResult = 102;
    ActivityImportStep1Binding binding;
    private String cityId = "";
    private String countryId = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityImportStep1Binding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        anImport = new Import();
        this.binding.setData(anImport);
        this.binding.executePendingBindings();
        init();
    }

    private void init() {
        setTitle(getString(R.string.Import));
        if (getIntent().hasExtra("HistoryType")) {
            Utils.setEnable(this.binding.linMain, false);
        } else {
            this.binding.edtFullName.requestFocus();
            Utils.setEnable(this.binding.linMain, true);
            this.binding.txtCountry.setOnClickListener(this);
            this.binding.txtCity.setOnClickListener(this);
        }
        this.binding.layoutSteps.txtStep1.setBackground(ContextCompat.getDrawable(this.me, R.drawable.ic_round_shape_red));
        this.binding.linNext.setOnClickListener(this);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.lin_next:
                if (validation()) {
                    Intent intent1 = new Intent(this.me, ImportStep2Activity.class);
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
                setRedirectClass(ImportStep1Activity.class);
                return;
            case R.id.txtCountry:
                Intent intent = new Intent(this.me, SearchActivity.class);
                intent.putExtra("Country", true);
                startActivityForResult(intent, searchCountryResult);
                setRedirectClass(ImportStep1Activity.class);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == -1) {
            if (requestCode == searchCityResult) {
                City data1 = (City) data.getSerializableExtra("City");
                this.binding.txtCity.setText(data1.getCityName());
                anImport.setCityId(Utils.getCityId(data1.getCityName(), this.me));
                this.cityId = Utils.getCityId(data1.getCityName(), this.me);
            } else if (requestCode == searchCountryResult) {
                Country data12 = (Country) data.getSerializableExtra("Country");
                this.binding.txtCountry.setText(data12.getCountryName());
                this.countryId = data12.getCountryId();
                this.binding.txtCode.setText("+" + data12.getCountryCode());
                anImport.setCountryId(Utils.getCountryId(data12.getCountryName(), this.me));
            }
        }
    }
}
