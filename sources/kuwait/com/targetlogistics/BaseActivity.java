package kuwait.com.targetlogistics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap$OnMarkerDragListener;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityBaseBinding;
import kuwait.com.targetlogistics.databinding.RawDrawerLayoutBinding;
import kuwait.com.targetlogistics.enums.Events;
import kuwait.com.targetlogistics.home.HomeActivity;
import kuwait.com.targetlogistics.model.Delivery;
import kuwait.com.targetlogistics.model.DrawerEnum;
import kuwait.com.targetlogistics.model.DrawerItem;
import kuwait.com.targetlogistics.model.Import;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig.Builder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AbstractBaseActivity implements OnClickListener, GoogleMap$OnMarkerDragListener {
    public static Import anImport;
    public static Delivery delivery;
    private final int MY_PERMISSIONS_REQUEST_PHONE = 100;
    private DrawerRecyclerAdapter adapter;
    public ActivityBaseBinding baseBinding;
    private List<DrawerItem> drawerItemArrayList = new ArrayList();
    public FragmentManager fragmentManager;
    public BaseActivity me;
    private Drawable menuDrawable;
    public String url;

    private class DrawerRecyclerAdapter extends Adapter<ViewHolder> implements OnClickListener {
        private DrawerRecyclerAdapter() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BaseActivity$DrawerRecyclerAdapter$ViewHolder(this, RawDrawerLayoutBinding.inflate(BaseActivity.this.getLayoutInflater(), parent, false));
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) holder).binding.setData((DrawerItem) BaseActivity.this.drawerItemArrayList.get(position));
            ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) holder).binding.executePendingBindings();
            if (position == 2) {
                ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) holder).binding.imgRightArrow.setVisibility(0);
            } else {
                ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) holder).binding.imgRightArrow.setVisibility(8);
            }
            if (position == 6) {
                ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) holder).binding.linLang.setVisibility(0);
                ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) holder).binding.layoutLang.linLang.setOnClickListener(new BaseActivity$DrawerRecyclerAdapter$1(this));
            }
            ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) holder).binding.layoutShipmentHistory.txtExport.setOnClickListener(new BaseActivity$DrawerRecyclerAdapter$2(this, holder));
            ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) holder).binding.layoutShipmentHistory.txtImport.setOnClickListener(new BaseActivity$DrawerRecyclerAdapter$3(this, holder));
            ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) holder).binding.layoutShipmentHistory.txtDelivery.setOnClickListener(new BaseActivity$DrawerRecyclerAdapter$4(this, holder));
            ((BaseActivity$DrawerRecyclerAdapter$ViewHolder) holder).binding.getRoot().setOnClickListener(new BaseActivity$DrawerRecyclerAdapter$5(this, holder));
        }

        @Subscribe
        public void onFinish(Class<?> aClass) {
            if (aClass == getClass()) {
                BaseActivity.this.finish();
            }
        }

        public int getItemCount() {
            return BaseActivity.this.drawerItemArrayList.size();
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lin_lang:
                    if (Utils.getLanguage(BaseActivity.this.me).equals("en")) {
                        Utils.setLanguage(BaseActivity.this.me, "ar");
                        Utils.setLanguageDirection(BaseActivity.this.me, "rtl");
                        MyApplication.isLTR = Utils.getLanguageDirection(BaseActivity.this.me).equalsIgnoreCase("ltr");
                        BaseActivity.this.launchHome();
                        return;
                    }
                    Utils.setLanguage(BaseActivity.this.me, "en");
                    Utils.setLanguageDirection(BaseActivity.this.me, "ltr");
                    MyApplication.isLTR = Utils.getLanguageDirection(BaseActivity.this.me).equalsIgnoreCase("ltr");
                    BaseActivity.this.launchHome();
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new Builder().setDefaultFontPath(getString(R.string.regular_font)).setFontAttrId(R.attr.fontPath).build());
        Utils.setMyLocale(this, Utils.getLanguageDirection(this).equalsIgnoreCase("ltr") ? "en" : "ar");
        this.baseBinding = (ActivityBaseBinding) DataBindingUtil.setContentView(this, R.layout.activity_base);
        init();
    }

    private void init() {
        this.me = this;
        this.url = "android.resource://" + getPackageName() + "/";
        setToolBar(this.baseBinding.mToolbar);
        this.fragmentManager = getSupportFragmentManager();
        this.baseBinding.mSearchView.setHint(getString(R.string.search));
        if (AbstractBaseActivity.getRedirectClass() == null) {
            AbstractBaseActivity.setRedirectClass(HomeActivity.class);
        }
        EventBus.getDefault().register(this);
        this.baseBinding.txtWhatsappNo.setText(Utils.getWhatsAppNo(this.me));
        this.baseBinding.txtHotlineNo.setText(Utils.getCallCenter(this.me));
        this.baseBinding.txtWhatsappNo.setOnClickListener(this);
        this.baseBinding.txtHotlineNo.setOnClickListener(this);
    }

    public void setHomeHeader() {
        this.baseBinding.toolbarTitle.setVisibility(8);
        this.baseBinding.imgHeader.setVisibility(0);
    }

    public void setAddressListHeader() {
        this.baseBinding.imgAddAddress.setVisibility(0);
    }

    protected void setToolBar(Toolbar mToolBar) {
        try {
            getWindow().setSoftInputMode(2);
            getWindow().setWindowAnimations(0);
            setSupportActionBar(mToolBar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator((int) R.drawable.ic_back_arrow);
            this.baseBinding.drawerLayout.setDrawerLockMode(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Subscribe
    public void onEvent(Events event) {
        switch (BaseActivity$3.$SwitchMap$kuwait$com$targetlogistics$enums$Events[event.ordinal()]) {
            case 1:
                setDrawerData();
                return;
            default:
                return;
        }
    }

    @Subscribe
    public void onFinish(Class<?> aClass) {
        if (aClass == getClass()) {
            finish();
        }
    }

    protected void setNavigationView() {
        setDrawerData();
        DrawerLayout drawer = this.baseBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new BaseActivity$1(this, this, drawer, this.baseBinding.mToolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (this.menuDrawable == null) {
            this.menuDrawable = new BitmapDrawable(getResources(), getBitmap());
        }
        getSupportActionBar().setHomeAsUpIndicator(this.menuDrawable);
        this.baseBinding.drawerLayout.setScrimColor(ContextCompat.getColor(this.me, 17170445));
        this.baseBinding.drawerLayout.setDrawerLockMode(0);
    }

    private Bitmap getBitmap() {
        Drawable drawable = ContextCompat.getDrawable(this.me, R.drawable.menu_icon);
        int height = (int) Utils.convertDpToPixel(20.0f, getApplicationContext());
        int width = (int) Utils.convertDpToPixel(28.0f, getApplicationContext());
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    protected void setDrawerData() {
        this.drawerItemArrayList.clear();
        DrawerItem drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.home));
        drawerItem.setDrawerEnum(DrawerEnum.Home);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.my_profile));
        drawerItem.setDrawerEnum(DrawerEnum.MyProfile);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.shipment_history));
        drawerItem.setDrawerEnum(DrawerEnum.ShipmentHistory);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.address_book));
        drawerItem.setDrawerEnum(DrawerEnum.AddressBook);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.price_list));
        drawerItem.setDrawerEnum(DrawerEnum.PriceList);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.change_pwd));
        drawerItem.setDrawerEnum(DrawerEnum.ChangePassword);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.lang));
        drawerItem.setDrawerEnum(DrawerEnum.Language);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.notifications));
        drawerItem.setDrawerEnum(DrawerEnum.Notification);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.about));
        drawerItem.setDrawerEnum(DrawerEnum.AboutApp);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.terms_conditions));
        drawerItem.setDrawerEnum(DrawerEnum.TermsCondition);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.contact_us));
        drawerItem.setDrawerEnum(DrawerEnum.ContactUs);
        this.drawerItemArrayList.add(drawerItem);
        drawerItem = new DrawerItem();
        drawerItem.setName(getString(R.string.logout));
        drawerItem.setDrawerEnum(DrawerEnum.Logout);
        this.drawerItemArrayList.add(drawerItem);
        if (this.adapter == null) {
            this.adapter = new DrawerRecyclerAdapter();
            this.baseBinding.drawerRecycler.setLayoutManager(new LinearLayoutManager(this));
            this.baseBinding.drawerRecycler.setAdapter(this.adapter);
            return;
        }
        this.adapter.notifyDataSetChanged();
    }

    public Gson getGsonInstance() {
        return new GsonBuilder().excludeFieldsWithModifiers(new int[]{16, 128, 8}).create();
    }

    public void showSnackBar(Activity context, String msg) {
        Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView().findViewById(R.id.frmContainer), (CharSequence) msg, 0);
        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(ContextCompat.getColor(context, R.color.white));
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorRed));
        snackbar.show();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void closeDrawer() {
        this.baseBinding.drawerLayout.closeDrawers();
    }

    public void onMarkerDragStart(Marker marker) {
    }

    public void onMarkerDrag(Marker marker) {
    }

    public void onMarkerDragEnd(Marker marker) {
    }

    private void launchHome() {
        startActivity(new Intent(this.me, HomeActivity.class));
        finishAffinity();
    }

    protected void setTitle(String title) {
        this.baseBinding.toolbarTitle.setText(title);
    }

    public void setFullScreen() {
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_hotline_no:
                if (ContextCompat.checkSelfPermission(this.me, "android.permission.CALL_PHONE") != 0) {
                    ActivityCompat.requestPermissions(this.me, new String[]{"android.permission.CALL_PHONE"}, 100);
                    return;
                }
                Utils.makeCall(this.me, this.baseBinding.txtHotlineNo.getText().toString());
                closeDrawer();
                return;
            case R.id.txt_whatsapp_no:
                Utils.openWhatsApp(this.me);
                closeDrawer();
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        if (this.baseBinding.mSearchView.isSearchOpen()) {
            this.baseBinding.mSearchView.closeSearch();
            return;
        }
        Utils.hideSoftKeyboard(this.me);
        super.onBackPressed();
    }

    private void callLogOutAPI() {
        showOnlyProgressDialog();
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.LOGOUT);
        requestParams.put("language", MyApplication.isLTR ? "en" : "ar");
        requestParams.put("user_id", Utils.getUserId(this.me));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new BaseActivity$2(this));
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == 0) {
                    Utils.makeCall(this.me, this.baseBinding.txtHotlineNo.getText().toString());
                    return;
                }
                return;
            default:
                return;
        }
    }
}
