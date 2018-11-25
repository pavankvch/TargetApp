package kuwait.com.targetlogistics.home;

import android.os.Bundle;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityAboutAppBinding;
import kuwait.com.targetlogistics.model.CMS;

public class AboutAppActivity extends BaseActivity {
    ActivityAboutAppBinding binding;
    CMS cms;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAboutAppBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getString(R.string.about));
        this.cms = Utils.getCMSInfo(this.me);
        this.binding.setData(this.cms);
        this.binding.executePendingBindings();
    }
}
