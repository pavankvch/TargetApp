package kuwait.com.targetlogistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivitySplashBinding;
import kuwait.com.targetlogistics.home.HomeActivity;
import kuwait.com.targetlogistics.user.LoginActivity;
import org.greenrobot.eventbus.Subscribe;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySplashBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
        showOnlyProgressDialog();
        if (!Utils.getLanguage(this.me).isEmpty()) {
            this.binding.tvArabic.setVisibility(8);
            this.binding.tvEnglish.setVisibility(8);
        }
    }

    private void init() {
        this.me = this;
        setFullScreen();
        this.binding.tvArabic.setOnClickListener(this.me);
        this.binding.tvEnglish.setOnClickListener(this.me);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvArabic:
                Utils.setLanguage(this.me, "ar");
                Utils.setLanguageDirection(this.me, "rtl");
                MyApplication.isLTR = Utils.getLanguageDirection(this).equalsIgnoreCase("ltr");
                launchHome();
                return;
            case R.id.tvEnglish:
                Utils.setLanguage(this.me, "en");
                Utils.setLanguageDirection(this.me, "ltr");
                MyApplication.isLTR = Utils.getLanguageDirection(this).equalsIgnoreCase("ltr");
                launchHome();
                return;
            default:
                super.onClick(view);
                return;
        }
    }

    private void launchHome() {
        if (Utils.getUserId(this.me).isEmpty()) {
            startActivity(new Intent(this.me, LoginActivity.class));
            ActivityCompat.finishAffinity(this.me);
            return;
        }
        startActivity(new Intent(this.me, HomeActivity.class));
        ActivityCompat.finishAffinity(this.me);
    }

    @Subscribe
    public void onMessageReceived(Intent i) {
        dismissDialog();
        if (i.getAction() != null && i.getAction().equalsIgnoreCase("notification")) {
            if (!Utils.getAndroidVersion(this.me).equalsIgnoreCase(Utils.getVersionName(this.me))) {
                showVersionDialog("", getString(R.string.new_version_message), this.me, new SplashActivity$1(this));
            } else if (!Utils.getLanguage(this.me).isEmpty()) {
                launchHome();
            }
        }
    }
}
