package kuwait.com.targetlogistics.history;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.databinding.ActivityExportHistoryBinding;
import kuwait.com.targetlogistics.model.HistoryData;

public class HistoryActivity extends BaseActivity {
    ActivityExportHistoryBinding binding;
    private Bundle bundle;
    HistoryData data;
    private String from;

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public Fragment getItem(int position) {
            return (Fragment) this.mFragmentList.get(position);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return (CharSequence) this.mFragmentTitleList.get(position);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityExportHistoryBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        if (getIntent().hasExtra("HistoryType")) {
            this.from = getIntent().getStringExtra("HistoryType");
            String str = this.from;
            int i = -1;
            switch (str.hashCode()) {
                case -2100928571:
                    if (str.equals("Import")) {
                        boolean z = true;
                        break;
                    }
                    break;
                case 888111124:
                    if (str.equals("Delivery")) {
                        i = 2;
                        break;
                    }
                    break;
                case 2089680852:
                    if (str.equals("Export")) {
                        i = 0;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    setTitle(getString(R.string.export_history));
                    break;
                case 1:
                    setTitle(getString(R.string.import_history));
                    break;
                case 2:
                    setTitle(getString(R.string.delivery_history));
                    break;
            }
        }
        setRedirectClass(HistoryActivity.class);
        setupViewPager(this.binding.viewpager);
        this.binding.tabs.setupWithViewPager(this.binding.viewpager);
        Typeface customFont = Typeface.createFromAsset(getAssets(), getString(R.string.regular_font));
        ViewGroup vg = (ViewGroup) this.binding.tabs.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i2 = 0; i2 < tabChildsCount; i2++) {
                View tabViewChild = vgTab.getChildAt(i2);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(customFont);
                    ((TextView) tabViewChild).setAllCaps(true);
                    ((TextView) tabViewChild).setTextSize(28.0f);
                }
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        this.bundle = new Bundle();
        ExportPendingFragment fragment = new ExportPendingFragment();
        if (this.from.equals("Export")) {
            this.bundle.putString("CurrentFragment", "ExportPending");
        } else if (this.from.equals("Import")) {
            this.bundle.putString("CurrentFragment", "ImportPending");
        } else if (this.from.equals("Delivery")) {
            this.bundle.putString("CurrentFragment", "DeliveryPending");
        }
        this.bundle.putString("HistoryType", this.from);
        fragment.setArguments(this.bundle);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fragment, getString(R.string.pending));
        this.bundle = new Bundle();
        fragment = new ExportPendingFragment();
        if (this.from.equals("Export")) {
            this.bundle.putString("CurrentFragment", "ExportCompleted");
        } else if (this.from.equals("Import")) {
            this.bundle.putString("CurrentFragment", "ImportCompleted");
        } else if (this.from.equals("Delivery")) {
            this.bundle.putString("CurrentFragment", "DeliveryCompleted");
        }
        this.bundle.putString("HistoryType", this.from);
        fragment.setArguments(this.bundle);
        adapter.addFragment(fragment, getString(R.string.completed));
        viewPager.setAdapter(adapter);
    }
}
