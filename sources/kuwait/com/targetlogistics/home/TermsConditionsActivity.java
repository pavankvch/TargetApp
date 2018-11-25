package kuwait.com.targetlogistics.home;

import android.os.Bundle;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityTermsConditionsBinding;
import kuwait.com.targetlogistics.model.CMS;

public class TermsConditionsActivity extends BaseActivity {
    ActivityTermsConditionsBinding binding;
    CMS cms;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityTermsConditionsBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.terms_conditions));
        this.cms = Utils.getCMSInfo(this.me);
        this.binding.setData(this.cms);
        this.binding.executePendingBindings();
    }
}
