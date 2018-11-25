package kuwait.com.targetlogistics;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import com.activeandroid.ActiveAndroid;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import kuwait.com.targetlogistics.api.ApiClient;
import kuwait.com.targetlogistics.api.BaseUrl;
import kuwait.com.targetlogistics.api.ErrorUtils;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.model.Address;
import kuwait.com.targetlogistics.model.Area;
import kuwait.com.targetlogistics.model.CMS;
import kuwait.com.targetlogistics.model.City;
import kuwait.com.targetlogistics.model.Country;
import kuwait.com.targetlogistics.model.ParcelType;
import kuwait.com.targetlogistics.model.UserInfo;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();
    public static boolean isLTR = true;
    private static MyApplication mInstance;
    private ArrayList<Address> addressArrayList = new ArrayList();
    private ArrayList<Area> areaArrayList = new ArrayList();
    private ArrayList<City> cityArrayList = new ArrayList();
    private ArrayList<Country> countryArrayList = new ArrayList();
    private ArrayList<ParcelType> parcelTypeArrayList = new ArrayList();
    private ArrayList<String> pickupTimeSlotList = new ArrayList();
    private ArrayList<String> todayPickupTimeSlotList = new ArrayList();
    private ArrayList<String> tomorrowPickupTimeSlotList = new ArrayList();

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ActiveAndroid.initialize((Context) this);
        isLTR = Utils.getLanguage(this).equalsIgnoreCase("en");
        if (!(Utils.isRegistered(mInstance) || Utils.getGCMToken(mInstance).isEmpty())) {
            callDeviceRegistration();
        }
        callInitialization();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Utils.setMyLocale(this, Utils.getLanguage(mInstance).equalsIgnoreCase("en") ? "en" : "ar");
    }

    public static synchronized MyApplication getInstance() {
        MyApplication myApplication;
        synchronized (MyApplication.class) {
            myApplication = mInstance;
        }
        return myApplication;
    }

    private void callDeviceRegistration() {
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.DEVICE_REGISTRATION);
        requestParams.put("device_type", "a");
        requestParams.put("device_id", Utils.getGCMToken(getInstance()));
        requestParams.put("imei", AbstractBaseActivity.getAndroidId(getInstance()));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallPost(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        Utils.setRegistered(MyApplication.mInstance, true);
                        Utils.printMsg("success message", "Device registered successfully");
                        return;
                    }
                    return;
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                Utils.printMsg("fai", t.getMessage());
            }
        });
    }

    public void callInitialization() {
        HashMap<String, String> requestParams = new HashMap();
        requestParams.put("function", BaseUrl.INITIALIZE);
        requestParams.put("user_id", Utils.getUserId(mInstance));
        requestParams.put("language", isLTR ? "en" : "ar");
        requestParams.put("device_id", Utils.getGCMToken(getInstance()));
        Utils.printMsg("Request Params : ", requestParams.toString());
        ApiClient.getService().methodCallGet(BaseUrl.SUB_URL, requestParams).enqueue(new Callback<JsonObject>() {
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Utils.printMsg("Response : ", ((JsonObject) response.body()).toString());
                    MyApplication.this.areaArrayList.clear();
                    MyApplication.this.countryArrayList.clear();
                    MyApplication.this.cityArrayList.clear();
                    MyApplication.this.addressArrayList.clear();
                    MyApplication.this.parcelTypeArrayList.clear();
                    MyApplication.this.pickupTimeSlotList.clear();
                    MyApplication.this.todayPickupTimeSlotList.clear();
                    MyApplication.this.tomorrowPickupTimeSlotList.clear();
                    if (BaseUrl.isSuccess(((JsonObject) response.body()).get("status").getAsString())) {
                        Type listType;
                        JsonObject jsonObject = ((JsonObject) response.body()).getAsJsonObject("data");
                        Gson gson = new Gson();
                        MyApplication.this.areaArrayList = (ArrayList) gson.fromJson(jsonObject.get("areas"), new MyApplication$2$1(this).getType());
                        if (MyApplication.this.areaArrayList != null && MyApplication.this.areaArrayList.size() > 0) {
                            Utils.setAreaList(MyApplication.mInstance, MyApplication.this.areaArrayList);
                        }
                        MyApplication.this.countryArrayList = (ArrayList) gson.fromJson(jsonObject.get("countries"), new MyApplication$2$2(this).getType());
                        if (MyApplication.this.countryArrayList != null && MyApplication.this.countryArrayList.size() > 0) {
                            Utils.setCountryList(MyApplication.mInstance, MyApplication.this.countryArrayList);
                        }
                        MyApplication.this.cityArrayList = (ArrayList) gson.fromJson(jsonObject.get("cities"), new MyApplication$2$3(this).getType());
                        if (MyApplication.this.cityArrayList != null && MyApplication.this.cityArrayList.size() > 0) {
                            Utils.setCityList(MyApplication.mInstance, MyApplication.this.cityArrayList);
                        }
                        MyApplication.this.parcelTypeArrayList = (ArrayList) gson.fromJson(jsonObject.get("parcel_type"), new MyApplication$2$4(this).getType());
                        if (MyApplication.this.parcelTypeArrayList != null && MyApplication.this.parcelTypeArrayList.size() > 0) {
                            Utils.setParcelTypeList(MyApplication.mInstance, MyApplication.this.parcelTypeArrayList);
                        }
                        UserInfo userInfo = (UserInfo) gson.fromJson(jsonObject.get("user_data"), new MyApplication$2$5(this).getType());
                        if (userInfo != null) {
                            Utils.setUserInfo(MyApplication.mInstance, userInfo);
                        }
                        if (jsonObject.get("user_address_data") instanceof JsonArray) {
                            MyApplication.this.addressArrayList = (ArrayList) gson.fromJson(jsonObject.get("user_address_data"), new MyApplication$2$6(this).getType());
                            if (MyApplication.this.addressArrayList != null && MyApplication.this.addressArrayList.size() > 0) {
                                Utils.setAddressList(MyApplication.mInstance, MyApplication.this.addressArrayList);
                            }
                        }
                        CMS cms = (CMS) gson.fromJson(jsonObject.get("cms"), new MyApplication$2$7(this).getType());
                        if (cms != null) {
                            Utils.setCMSInfo(MyApplication.mInstance, cms);
                        }
                        if (jsonObject.get("pricelist").getAsString() != null) {
                            Utils.setPriceListUrl(MyApplication.mInstance, jsonObject.get("pricelist").getAsString());
                        }
                        if (jsonObject.get("pickupstart").getAsString() != null) {
                            Utils.setPickupStart(MyApplication.mInstance, jsonObject.get("pickupstart").getAsString());
                        }
                        if (jsonObject.get("order_id").getAsString() != null) {
                            Utils.setOrderId(MyApplication.mInstance, jsonObject.get("order_id").getAsString());
                        }
                        if (jsonObject.get("callcenter").getAsString() != null) {
                            Utils.setCallCenter(MyApplication.mInstance, jsonObject.get("callcenter").getAsString());
                        }
                        if (jsonObject.get("whatsapp").getAsString() != null) {
                            Utils.setWhatsAppNo(MyApplication.mInstance, jsonObject.get("whatsapp").getAsString());
                        }
                        if (jsonObject.get("order_type").getAsString() != null) {
                            Utils.setOrderType(MyApplication.mInstance, jsonObject.get("order_type").getAsString());
                        }
                        if (jsonObject.get("android_version").getAsString() != null) {
                            Utils.setAndroidVersion(MyApplication.mInstance, jsonObject.get("android_version").getAsString());
                        }
                        if (jsonObject.get("force_update").getAsString() != null) {
                            Utils.setForceUpdate(MyApplication.mInstance, jsonObject.get("force_update").getAsString());
                        }
                        if (jsonObject.get("pickup_today_slot_en") instanceof JsonArray) {
                            listType = new MyApplication$2$8(this).getType();
                            if (MyApplication.isLTR) {
                                MyApplication.this.todayPickupTimeSlotList = (ArrayList) gson.fromJson(jsonObject.get("pickup_today_slot_en"), listType);
                            } else {
                                MyApplication.this.todayPickupTimeSlotList = (ArrayList) gson.fromJson(jsonObject.get("pickup_today_slot_ar"), listType);
                            }
                            if (MyApplication.this.todayPickupTimeSlotList != null && MyApplication.this.todayPickupTimeSlotList.size() > 0) {
                                Utils.setTodayPickupTimeSlot(MyApplication.mInstance, MyApplication.this.todayPickupTimeSlotList);
                            }
                        }
                        if (jsonObject.get("pickup_tomorrow_slot_en") instanceof JsonArray) {
                            listType = new MyApplication$2$9(this).getType();
                            if (MyApplication.isLTR) {
                                MyApplication.this.tomorrowPickupTimeSlotList = (ArrayList) gson.fromJson(jsonObject.get("pickup_tomorrow_slot_en"), listType);
                            } else {
                                MyApplication.this.tomorrowPickupTimeSlotList = (ArrayList) gson.fromJson(jsonObject.get("pickup_tomorrow_slot_ar"), listType);
                            }
                            if (MyApplication.this.tomorrowPickupTimeSlotList != null && MyApplication.this.tomorrowPickupTimeSlotList.size() > 0) {
                                Utils.setTomorrowPickupTimeSlot(MyApplication.mInstance, MyApplication.this.tomorrowPickupTimeSlotList);
                            }
                        }
                    }
                    EventBus.getDefault().post(new Intent("notification"));
                    return;
                }
                Utils.printMsg("error message", ErrorUtils.parseError(response).message());
            }

            public void onFailure(Call<JsonObject> call, Throwable t) {
                Utils.printMsg("fail", t.getMessage());
            }
        });
    }
}
