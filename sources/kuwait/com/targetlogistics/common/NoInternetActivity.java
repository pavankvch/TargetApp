package kuwait.com.targetlogistics.common;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.databinding.ActivityNoInternetBinding;

public class NoInternetActivity extends BaseActivity {
    ActivityNoInternetBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityNoInternetBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        setTitle(getString(R.string.no_internet));
        setToolBar(this.baseBinding.mToolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    public void onBackPressed() {
        finishAffinity();
    }

    protected void setToolBar(Toolbar mToolBar) {
        try {
            getWindow().setSoftInputMode(2);
            getWindow().setWindowAnimations(0);
            setSupportActionBar(mToolBar);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
