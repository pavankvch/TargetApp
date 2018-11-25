package kuwait.com.targetlogistics.api;

import android.support.annotation.NonNull;

public class BaseUrl {
    public static final String CANCEL_ORDER = "cancelorder";
    public static final String CHANGE_PASSWORD = "changepassword";
    public static final String CONTACT_US = "contactus";
    public static final String DELETE_ADDRESS = "deleteaddress";
    public static final String DELIVERY_ORDER = "deliveryorder";
    public static final String DEVICE_REGISTRATION = "deviceRegistration";
    public static final String EDIT_ADDRESS = "editaddress";
    public static final String EDIT_PROFILE = "editprofile";
    public static final String EXPORT_ORDER = "exportorder";
    public static final String FORGOT_PASSWORD = "forgotpassword";
    public static final String GET_ADDRESS = "getaddress";
    public static final String GET_ADDRESS_LAT_LNG = "getLocationAddress";
    public static final String GET_DELIVERY_HISTORY = "localdeliveryhistory";
    public static final String GET_DELIVERY_HISTORY_DETAIL = "localdeliverydetail";
    public static final String GET_EXPORT_HISTORY = "exporthistory";
    public static final String GET_EXPORT_HISTORY_DETAIL = "exportdetail";
    public static final String GET_IMPORT_HISTORY = "importhistory";
    public static final String GET_IMPORT_HISTORY_DETAIL = "importdetail";
    public static final String GET_NOTIFICATIONS = "notificationlist";
    public static final String GET_PROFILE = "getprofile";
    public static final String GLOBAL_URL = "http://target-kw.com/myadmin/api/";
    public static final String IMPORT_ORDER = "importorder";
    public static final String INITIALIZE = "initialize";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String REGISTRATION = "registration";
    public static final String SAVE_ADDRESS = "saveaddress";
    public static final String SUB_URL = "api.php?";
    public static final String TRACK_ORDER = "trackorder";
    public static final String VEHICLE_LIST = "getvehicle";

    public static boolean isSuccess(@NonNull String status) {
        return status.equalsIgnoreCase("success");
    }
}
