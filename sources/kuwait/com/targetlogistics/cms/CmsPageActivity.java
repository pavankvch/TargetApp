package kuwait.com.targetlogistics.cms;

import android.os.Bundle;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.databinding.ActivityCmsPagesBinding;

public class CmsPageActivity extends BaseActivity {
    ActivityCmsPagesBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityCmsPagesBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
    }
}
