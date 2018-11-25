package kuwait.com.targetlogistics.common;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.telephony.PhoneNumberUtils;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.auth.api.credentials.IdentityProviders;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.model.Address;
import kuwait.com.targetlogistics.model.Area;
import kuwait.com.targetlogistics.model.CMS;
import kuwait.com.targetlogistics.model.City;
import kuwait.com.targetlogistics.model.Country;
import kuwait.com.targetlogistics.model.ParcelType;
import kuwait.com.targetlogistics.model.UserInfo;

public class Utils {
    private static final int ANIMATION_DURATION = 300;
    private static final boolean ENABLE_LOG = false;
    private static DateFormat ServerDateFormat = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS_24, Locale.ENGLISH);
    private static DateFormat ServerDateFormatOutput = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH);
    private static String amountDigits = "0.000";
    private static DateFormat currentDateFormat = new SimpleDateFormat(DateUtils.YYYY_MM_DD, Locale.ENGLISH);
    private static DecimalFormat decimalFormat;
    private static DateFormat expectedDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    public static String getTimeZone(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("timezone", "Asia/Kuwait");
    }

    public static void setUserId(Context context, String userAddressId) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("userId", userAddressId).apply();
    }

    public static String getUserId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("userId", "");
    }

    public static String getEmail(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("userEmail", "");
    }

    public static void setEmail(Context context, String userId) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("userEmail", userId).apply();
    }

    public static UserInfo getUserInfo(Context context) {
        return (UserInfo) new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(context).getString("UserInfo", ""), UserInfo.class);
    }

    public static void setUserInfo(Context context, UserInfo userInfo) {
        Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        prefsEditor.putString("UserInfo", new Gson().toJson(userInfo));
        prefsEditor.apply();
    }

    public static void setCMSInfo(Context context, CMS cmsInfo) {
        Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        prefsEditor.putString("cmsInfo", new Gson().toJson(cmsInfo));
        prefsEditor.apply();
    }

    public static CMS getCMSInfo(Context context) {
        return (CMS) new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(context).getString("cmsInfo", ""), CMS.class);
    }

    public static void setCountryId(Context context, String countryId) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("countryId", countryId).apply();
    }

    public static String getCountryId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("countryId", "");
    }

    public static void setCountryCode(Context context, String countryCode) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("countryCode", countryCode).apply();
    }

    public static String getCountryCode(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("countryCode", "");
    }

    public static void setUserContactNumber(Context context, String countryCode) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("ContactNumber", countryCode).apply();
    }

    public static String getUserContactNumber(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("ContactNumber", "");
    }

    public static void setIsFirstTime(Context context, boolean isFirstTime) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("IsFirstTime", isFirstTime).apply();
    }

    public static boolean getIsFirstTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("IsFirstTime", true);
    }

    public static void setUserAddressId(Context context, String userAddressId) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("userAddressId", userAddressId).apply();
    }

    public static String getUserAddressId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("userAddressId", "");
    }

    public static void setCountryName(Context context, String countryName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("countryName", countryName).apply();
    }

    public static String getCountryName(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("countryName", "");
    }

    public static void setCountryFlag(Context context, String countryFlag) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("countryFlag", countryFlag).apply();
    }

    public static String getCountryFlag(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("countryFlag", "");
    }

    public static String getUserName(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("fullname", "");
    }

    public static void setUserName(Context context, String userName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("fullname", userName).apply();
    }

    public static String getFirstName(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("firstName", "");
    }

    public static void setFirstName(Context context, String firstName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("firstName", firstName).apply();
    }

    public static String getLastName(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("lastName", "");
    }

    public static void setPriceListUrl(Context context, String firstName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("PriceListUrl", firstName).apply();
    }

    public static String getPriceListUrl(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("PriceListUrl", "");
    }

    public static void setPickupStart(Context context, String firstName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("PickupStart", firstName).apply();
    }

    public static String getPickupStart(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("PickupStart", "");
    }

    public static void setOrderId(Context context, String firstName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("OrderId", firstName).apply();
    }

    public static String getOrderId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("OrderId", "");
    }

    public static void setMType(Context context, String firstName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("mtype", firstName).apply();
    }

    public static String getMType(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("mtype", "");
    }

    public static void setCallCenter(Context context, String firstName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("CallCenter", firstName).apply();
    }

    public static String getCallCenter(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("CallCenter", "");
    }

    public static void setWhatsAppNo(Context context, String firstName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("WhatsAppNo", firstName).apply();
    }

    public static String getWhatsAppNo(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("WhatsAppNo", "");
    }

    public static void setOrderType(Context context, String firstName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("OrderType", firstName).apply();
    }

    public static String getOrderType(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("OrderType", "");
    }

    public static void setAndroidVersion(Context context, String firstName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("AndroidVersion", firstName).apply();
    }

    public static String getAndroidVersion(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("AndroidVersion", "");
    }

    public static void setForceUpdate(Context context, String firstName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("ForceUpdate", firstName).apply();
    }

    public static String getForceUpdate(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("ForceUpdate", "");
    }

    public static void setLastName(Context context, String lastName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("lastName", lastName).apply();
    }

    public static void setEnable(View view, boolean isEnable) {
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                vg.getChildAt(i).setClickable(isEnable);
                vg.getChildAt(i).setEnabled(isEnable);
            }
        }
    }

    public static void setAreaList(Context context, ArrayList<Area> ActivityList) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("AreaList", new Gson().toJson(ActivityList, new Utils$1().getType())).apply();
    }

    public static ArrayList<Area> getAreaList(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return (ArrayList) new Gson().fromJson(prefs.getString("AreaList", ""), new Utils$2().getType());
    }

    public static void setCountryList(Context context, ArrayList<Country> ActivityList) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("CountryList", new Gson().toJson(ActivityList, new Utils$3().getType())).apply();
    }

    public static ArrayList<Country> getCountryList(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return (ArrayList) new Gson().fromJson(prefs.getString("CountryList", ""), new Utils$4().getType());
    }

    public static void setCityList(Context context, ArrayList<City> ActivityList) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("CityList", new Gson().toJson(ActivityList, new Utils$5().getType())).apply();
    }

    public static ArrayList<City> getCityList(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return (ArrayList) new Gson().fromJson(prefs.getString("CityList", ""), new Utils$6().getType());
    }

    public static void setParcelTypeList(Context context, ArrayList<ParcelType> ActivityList) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("ParcelType", new Gson().toJson(ActivityList, new Utils$7().getType())).apply();
    }

    public static ArrayList<ParcelType> getParcelTypeList(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return (ArrayList) new Gson().fromJson(prefs.getString("ParcelType", ""), new Utils$8().getType());
    }

    public static void setAddressList(Context context, ArrayList<Address> ActivityList) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("HistoryList", new Gson().toJson(ActivityList, new Utils$9().getType())).apply();
    }

    public static ArrayList<Address> getAddressList(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.getString("HistoryList", "").isEmpty()) {
            return new ArrayList();
        }
        return (ArrayList) new Gson().fromJson(prefs.getString("HistoryList", ""), new Utils$10().getType());
    }

    public static void setPickupTimeSlot(Context context, ArrayList<String> ActivityList) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("PickupTimeSlot", new Gson().toJson(ActivityList, new Utils$11().getType())).apply();
    }

    public static ArrayList<String> getPickupTimeSlot(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.getString("PickupTimeSlot", "").isEmpty()) {
            return new ArrayList();
        }
        return (ArrayList) new Gson().fromJson(prefs.getString("PickupTimeSlot", ""), new Utils$12().getType());
    }

    public static void setTodayPickupTimeSlot(Context context, ArrayList<String> ActivityList) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("TodayPickupTimeSlot", new Gson().toJson(ActivityList, new Utils$13().getType())).apply();
    }

    public static ArrayList<String> getTodayPickupTimeSlot(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.getString("TodayPickupTimeSlot", "").isEmpty()) {
            return new ArrayList();
        }
        return (ArrayList) new Gson().fromJson(prefs.getString("TodayPickupTimeSlot", ""), new Utils$14().getType());
    }

    public static void setTomorrowPickupTimeSlot(Context context, ArrayList<String> ActivityList) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("TomorrowPickupTimeSlot", new Gson().toJson(ActivityList, new Utils$15().getType())).apply();
    }

    public static ArrayList<String> getTomorrowPickupTimeSlot(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.getString("TomorrowPickupTimeSlot", "").isEmpty()) {
            return new ArrayList();
        }
        return (ArrayList) new Gson().fromJson(prefs.getString("TomorrowPickupTimeSlot", ""), new Utils$16().getType());
    }

    public static double getDeliveryCharge(Context context) {
        return (double) PreferenceManager.getDefaultSharedPreferences(context).getFloat("deliveryCharge", 0.0f);
    }

    public static void setDeliveryCharge(Context context, double deliveryCharge) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat("deliveryCharge", (float) deliveryCharge).apply();
    }

    public static String getCurrencySymbol(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("currencySymbol", "KWD");
    }

    public static void setCurrencySymbol(Context context, String currencySymbol) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("currencySymbol", currencySymbol).apply();
    }

    public static String getContactNumber(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("contactNumber", "");
    }

    public static void setMinimumAmount(Context context, String minimumAmount) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("minimumAmount", minimumAmount).apply();
    }

    public static String getMinimumAmount(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("minimumAmount", "0");
    }

    public static void setContactNumber(Context context, String contactNumber) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("contactNumber", contactNumber).apply();
    }

    public static void setPushStatus(Context context, boolean status) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("NotificationStatus", status).apply();
    }

    public static boolean getPushStatus(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("NotificationStatus", false);
    }

    public static void setFirstTime(Context context, boolean status) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("isFirstTime", status).apply();
    }

    public static boolean isFirstTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("isFirstTime", false);
    }

    public static void setAddressAdded(Context context, boolean status) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("isAddressAdded", status).apply();
    }

    public static boolean isAddressAdded(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("isAddressAdded", false);
    }

    public static void setDeliveryAddressAdded(Context context, boolean status) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("isDeliveryAddressAdded", status).apply();
    }

    public static boolean isDeliveryAddressAdded(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("isDeliveryAddressAdded", false);
    }

    public static void setLanguage(Context context, String language) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("language", language).apply();
    }

    public static String getLanguage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("language", "");
    }

    public static String getApiLanguage(Context context) {
        if (getLanguage(context).equalsIgnoreCase("ar")) {
            return "arabic";
        }
        return "english";
    }

    public static void setLanguageDirection(Context context, String languageDir) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("languageDirection", languageDir).apply();
    }

    public static String getLanguageDirection(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("languageDirection", "ltr");
    }

    public static String getLangageName(Context context) {
        if (getLanguage(context).equalsIgnoreCase("ar")) {
            return "arabic";
        }
        return "english";
    }

    public static String getGCMToken(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("GCMToken", "");
    }

    public static void setGCMToken(Context context, String GCMToken) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("GCMToken", GCMToken).apply();
    }

    public static boolean isRegistered(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("isRegistered", false);
    }

    public static void setRegistered(Context context, boolean isRegistered) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("isRegistered", isRegistered).apply();
    }

    public static int getNotificationBaseCount(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("notificationBaseCount", 0);
    }

    public static void setNotificationBaseCount(Context context, int notificationBaseCount) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt("notificationBaseCount", notificationBaseCount).apply();
    }

    public static String getLocale(Context mContext) {
        Locale locale;
        if (VERSION.SDK_INT >= 24) {
            locale = mContext.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = mContext.getResources().getConfiguration().locale;
        }
        return String.valueOf(locale.getLanguage().charAt(0));
    }

    public static String getCountries(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("countries", "");
    }

    public static void setCountries(Context context, String countries) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("countries", countries).apply();
    }

    public static String getAreas(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("areas", "");
    }

    public static void setAreas(Context context, String areas) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("areas", areas).apply();
    }

    public static String getAddress(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("address", "");
    }

    public static void setAddress(Context context, String address) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("address", address).apply();
    }

    public static String getLocations(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("locations", "");
    }

    public static void setLocations(Context context, String locations) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("locations", locations).apply();
    }

    public static String getCategories(Context context, String categoryId) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(categoryId, "");
    }

    public static void setCategories(Context context, String categoryId, String categories) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(categoryId, categories).apply();
    }

    public static String getSubMenuItem(Context context, String subCategoryId) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(subCategoryId, "");
    }

    public static void setSubMenuItem(Context context, String subCategoryId, String subMenuItems) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(subCategoryId, subMenuItems).apply();
    }

    public static String getDeviceFavorite(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("deviceFavorite", "");
    }

    public static void setDeviceFavorite(Context context, String deviceFavorite) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("deviceFavorite", deviceFavorite).apply();
    }

    public static String getUserFavorite(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("userFavorite", "");
    }

    public static void setUserFavorite(Context context, String userFavorite) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("userFavorite", userFavorite).apply();
    }

    public static String getContactUs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("contactUs", "");
    }

    public static void setContactUs(Context context, String contactUs) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("contactUs", contactUs).apply();
    }

    public static String getSignatureDishes(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("signatureDishes", "");
    }

    public static void setSignatureDishes(Context context, String signatureDishes) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("signatureDishes", signatureDishes).apply();
    }

    public static String getDeviceHistory(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("deviceHistory", "");
    }

    public static void setDeviceHistory(Context context, String deviceHistory) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("deviceHistory", deviceHistory).apply();
    }

    public static String getUserHistory(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("userHistory", "");
    }

    public static void setUserHistory(Context context, String userHistory) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("userHistory", userHistory).apply();
    }

    public static boolean checkConnectivity(Context context) {
        try {
            NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (info != null && info.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            printMsg("Error ", e.toString());
            return false;
        }
    }

    public static InputFilter[] getTextFilter() {
        return new InputFilter[]{new Utils$17()};
    }

    public static void hideSoftKeyboard(Activity me) {
        if (me.getCurrentFocus() != null) {
            ((InputMethodManager) me.getSystemService("input_method")).hideSoftInputFromWindow(me.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void openKeyBoard(EditText edt, Activity a) {
        ((InputMethodManager) a.getSystemService("input_method")).showSoftInput(edt, 1);
    }

    public static void HideKeyBoard(EditText edt, Activity a) {
        ((InputMethodManager) a.getSystemService("input_method")).hideSoftInputFromWindow(edt.getWindowToken(), 0);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).toggleSoftInput(2, 0);
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionName;
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static boolean checkPermission(Context mContext, String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) == 0;
    }

    public static boolean isValidEmail(String email) {
        return Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
    }

    public static String getB64Auth(String key) {
        return Base64.encodeToString(("X-MAKI-SecurityToken:" + key).getBytes(), 10);
    }

    public static String getBASE64Encoded(String key) {
        return Base64.encodeToString(key.getBytes(), 10);
    }

    public static void setMyLocale(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public static String getBase64Encoded(File file) {
        String encodedBase64 = "";
        try {
            byte[] bytes = new byte[((int) file.length())];
            new FileInputStream(file).read(bytes);
            return new String(Base64.encode(bytes, 0));
        } catch (Exception e) {
            e.printStackTrace();
            return encodedBase64;
        }
    }

    public static String getBase64Encoded(Bitmap image) {
        ByteBuffer buffer = ByteBuffer.allocate(image.getByteCount());
        image.copyPixelsToBuffer(buffer);
        return Base64.encodeToString(buffer.array(), 0);
    }

    static String getFormattedAmount(Context context, double amount) {
        if (decimalFormat == null) {
            decimalFormat = new DecimalFormat(amountDigits, new DecimalFormatSymbols(Locale.US));
        }
        return decimalFormat.format(amount) + " " + getCurrencySymbol(context);
    }

    public static void updateCurrency() {
        if (decimalFormat != null) {
            decimalFormat = null;
        }
    }

    public static void openInstagram(Context context) {
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage("com.instagram.android"));
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(Intent.createChooser(new Intent("android.intent.action.VIEW", Uri.parse("https://www.instagram.com")), "Select app"));
        }
    }

    public static void openTwitter(Context context) {
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage("com.twitter.android"));
        } catch (Exception e) {
            context.startActivity(Intent.createChooser(new Intent("android.intent.action.VIEW", Uri.parse(IdentityProviders.TWITTER)), "Select app"));
        }
    }

    public static void openGooglePlus(Context context) {
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage("com.google.android.apps.plus"));
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(Intent.createChooser(new Intent("android.intent.action.VIEW", Uri.parse("https://plus.google.com")), "Select app"));
        }
    }

    public static void openFacebook(Context context) {
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage("com.facebook.katana"));
        } catch (Exception e) {
            context.startActivity(Intent.createChooser(new Intent("android.intent.action.VIEW", Uri.parse(IdentityProviders.FACEBOOK)), "Select app"));
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public static float convertPixelsToDp(float px, Context context) {
        return px / (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public static void expand(View v) {
        v.measure(-1, -2);
        int targtetHeight = v.getMeasuredHeight();
        v.getLayoutParams().height = 0;
        v.setVisibility(0);
        Animation a = new Utils$18(v, targtetHeight);
        a.setDuration(300);
        v.startAnimation(a);
    }

    public static void collapse(View v) {
        Animation a = new Utils$19(v, v.getMeasuredHeight());
        a.setDuration(300);
        v.startAnimation(a);
    }

    public static Spannable getColoredString(String mString, int colorId) {
        Spannable spannable = new SpannableString(mString);
        spannable.setSpan(new ForegroundColorSpan(colorId), 0, spannable.length(), 33);
        return spannable;
    }

    public static void textUnderLine(View v) {
        if (v instanceof TextView) {
            ((TextView) v).setPaintFlags(((TextView) v).getPaintFlags() | 8);
        }
    }

    public static void setFonts(TextInputLayout textInputLayout) {
        textInputLayout.setTypeface(Typeface.createFromAsset(MyApplication.getInstance().getAssets(), "fonts/TitilliumWeb-Regular.ttf"));
    }

    public static boolean isSameDay(Date date1, Date date2) {
        boolean sameDay;
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        boolean sameYear;
        if (calendar1.get(1) == calendar2.get(1)) {
            sameYear = true;
        } else {
            sameYear = false;
        }
        boolean sameMonth;
        if (calendar1.get(2) == calendar2.get(2)) {
            sameMonth = true;
        } else {
            sameMonth = false;
        }
        if (calendar1.get(5) == calendar2.get(5)) {
            sameDay = true;
        } else {
            sameDay = false;
        }
        if (sameDay && sameMonth && sameYear) {
            return true;
        }
        return false;
    }

    public static String getAreaId(String selectedArea, Context context) {
        String areaId = null;
        Iterator it = getAreaList(context).iterator();
        while (it.hasNext()) {
            Area area = (Area) it.next();
            if (area.getAreaName().equalsIgnoreCase(selectedArea)) {
                areaId = area.getAreaId();
            }
        }
        return areaId;
    }

    public static String getCountryId(String selectedCountry, Context context) {
        String countryId = null;
        Iterator it = getCountryList(context).iterator();
        while (it.hasNext()) {
            Country country = (Country) it.next();
            if (country.getCountryName().equalsIgnoreCase(selectedCountry)) {
                countryId = country.getCountryId();
            }
        }
        return countryId;
    }

    public static String getCityId(String selectedCity, Context context) {
        String cityId = null;
        Iterator it = getCityList(context).iterator();
        while (it.hasNext()) {
            City city = (City) it.next();
            if (city.getCityName().equals(selectedCity)) {
                cityId = city.getCityId();
            }
        }
        return cityId;
    }

    public static String getCityName(String cityId, Context context) {
        String cityName = null;
        Iterator it = getCityList(context).iterator();
        while (it.hasNext()) {
            City city = (City) it.next();
            if (city.getCityId().equals(cityId)) {
                cityName = city.getCityName();
            }
        }
        return cityName;
    }

    public static String getAreaName(String areaId, Context context) {
        String areaName = null;
        Iterator it = getAreaList(context).iterator();
        while (it.hasNext()) {
            Area area = (Area) it.next();
            if (area.getAreaId().equals(areaId)) {
                areaName = area.getAreaName();
            }
        }
        return areaName;
    }

    public static String getCountryName(String countryId, Context context) {
        String countryName = null;
        Iterator it = getCountryList(context).iterator();
        while (it.hasNext()) {
            Country country = (Country) it.next();
            if (country.getCountryId().equals(countryId)) {
                countryName = country.getCountryName();
            }
        }
        return countryName;
    }

    public static String getParcelType(String id, Context context) {
        String parcelTypeText = null;
        Iterator it = getParcelTypeList(context).iterator();
        while (it.hasNext()) {
            ParcelType parcelType = (ParcelType) it.next();
            if (parcelType.getId().equals(id)) {
                parcelTypeText = parcelType.getParcelType();
            }
        }
        return parcelTypeText;
    }

    public static String formatDate(String strDate) {
        Date date = null;
        try {
            date = currentDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return expectedDateFormat.format(date);
    }

    public static void printMsg(String tag, String msg) {
    }

    public static String ServerformatDate(String strDate) {
        Date date = null;
        try {
            date = ServerDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ServerDateFormatOutput.format(date);
    }

    public static String getMonthShortName(int monthNumber) {
        String monthName = "";
        if (monthNumber >= 0 && monthNumber < 12) {
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.set(2, monthNumber);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
                simpleDateFormat.setCalendar(calendar);
                monthName = simpleDateFormat.format(calendar.getTime());
            } catch (Exception e) {
                if (e != null) {
                    e.printStackTrace();
                }
            }
        }
        return monthName;
    }

    public static Calendar getStartTime(Calendar calendar, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.HH_MM_PM, Locale.ENGLISH);
        try {
            Calendar tmpTime = Calendar.getInstance(Locale.ENGLISH);
            tmpTime.setTime(sdf.parse("09:00 am"));
            Calendar newDate = Calendar.getInstance(Locale.ENGLISH);
            newDate.setTime(calendar.getTime());
            newDate.set(11, tmpTime.get(11));
            newDate.set(12, tmpTime.get(12));
            newDate.set(13, tmpTime.get(13));
            return newDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return Calendar.getInstance(Locale.ENGLISH);
        }
    }

    public static Calendar getEndTime(Calendar calendar, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.HH_MM_PM, Locale.ENGLISH);
        try {
            Calendar tmpTime = Calendar.getInstance(Locale.ENGLISH);
            tmpTime.setTime(sdf.parse("08:00 pm"));
            Calendar newDate = Calendar.getInstance(Locale.ENGLISH);
            newDate.setTime(calendar.getTime());
            newDate.set(11, tmpTime.get(11));
            newDate.set(12, tmpTime.get(12));
            newDate.set(13, tmpTime.get(13));
            return newDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return Calendar.getInstance(Locale.ENGLISH);
        }
    }

    public static void openWhatsApp(Context context) {
        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(getWhatsAppNo(context)) + "@s.whatsapp.net");
        context.startActivity(sendIntent);
    }

    public static void makeCall(Context context, String phoneNo) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:" + phoneNo));
        if (ContextCompat.checkSelfPermission(context, "android.permission.CALL_PHONE") == 0) {
            context.startActivity(intent);
        }
    }
}
