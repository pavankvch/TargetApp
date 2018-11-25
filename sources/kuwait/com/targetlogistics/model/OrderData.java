package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;

public class OrderData implements Serializable {
    @SerializedName(alternate = {"pickupaddressformat_en"}, value = "addressformat_en")
    private String address;
    @SerializedName(alternate = {"pickupaddressformat_ar"}, value = "addressformat_ar")
    private String addressAr;
    @SerializedName("city_name_en")
    private String cityName;
    @SerializedName("country_name_en")
    private String countryName;
    @SerializedName("customer_id")
    private String customerId;
    private String deliveryAddress;
    @SerializedName(alternate = {}, value = "delivery_address1")
    private String deliveryAddress1;
    @SerializedName("delivery_address2")
    private String deliveryAddress2;
    @SerializedName("deliveryaddress_id")
    private String deliveryAddressId;
    @SerializedName("delivery_apartment")
    private String deliveryApartment;
    @SerializedName("delivery_block")
    private String deliveryBlock;
    @SerializedName(alternate = {"delivery_area"}, value = "delivery_city")
    private String deliveryCity;
    @SerializedName("delivery_country")
    private String deliveryCountry;
    @SerializedName("delivery_floor")
    private String deliveryFloor;
    @SerializedName(alternate = {"delivery_addressname"}, value = "delivery_fullname")
    private String deliveryFullName;
    @SerializedName("delivery_houseno")
    private String deliveryHouseno;
    @SerializedName("delivery_instruction")
    private String deliveryInstruction;
    @SerializedName("delivery_jadda")
    private String deliveryJadda;
    @SerializedName("delivery_phone")
    private String deliveryPhone;
    @SerializedName("delivery_postcode")
    private String deliveryPostcode;
    @SerializedName("delivery_status")
    private String deliveryStatus;
    @SerializedName("delivery_street")
    private String deliveryStreet;
    @SerializedName("deliveryaddressformat_en")
    private String deliveryaddressformat;
    @SerializedName("deliveryaddressformat_ar")
    private String deliveryaddressformatAr;
    @SerializedName("order_datetime")
    private String orderDatetime;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("order_status")
    private String orderStatus;
    @SerializedName("order_status_text")
    private String orderStatusText;
    @SerializedName("parcel_data")
    private ArrayList<Parcel> parcelArrayList;
    private String pickupAddress;
    @SerializedName("pickup_address1")
    private String pickupAddress1;
    @SerializedName("pickup_address2")
    private String pickupAddress2;
    @SerializedName("pickupaddress_id")
    private String pickupAddressId;
    @SerializedName("pickup_city")
    private String pickupCity;
    @SerializedName("pickup_country")
    private String pickupCountry;
    @SerializedName("pickup_datetime")
    private String pickupDatetime;
    @SerializedName("pickup_fullname")
    private String pickupFullName;
    @SerializedName("pickup_phone")
    private String pickupPhoneNo;
    @SerializedName("pickup_postcode")
    private String pickupPostCode;
    @SerializedName("pickupstart")
    private String pickupStatus;
    @SerializedName("shipment_data")
    private ShipmentData shipmentData;
    @SerializedName("shippingcost")
    private String shippingCost;
    @SerializedName("totalweight")
    private String totalWeight;
    @SerializedName("vehicle_name_en")
    private String vehicleName;

    public String getPickupFullName() {
        return this.pickupFullName;
    }

    public void setPickupFullName(String pickupFullName) {
        this.pickupFullName = pickupFullName;
    }

    public String getPickupCountry() {
        return this.pickupCountry;
    }

    public void setPickupCountry(String pickupCountry) {
        this.pickupCountry = pickupCountry;
    }

    public String getPickupPostCode() {
        return this.pickupPostCode;
    }

    public void setPickupPostCode(String pickupPostCode) {
        this.pickupPostCode = pickupPostCode;
    }

    public String getPickupAddress1() {
        return this.pickupAddress1;
    }

    public void setPickupAddress1(String pickupAddress1) {
        this.pickupAddress1 = pickupAddress1;
    }

    public String getPickupAddress2() {
        return this.pickupAddress2;
    }

    public void setPickupAddress2(String pickupAddress2) {
        this.pickupAddress2 = pickupAddress2;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getVehicleName() {
        return this.vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getAddress() {
        return MyApplication.isLTR ? this.address : this.addressAr;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressAr() {
        return this.addressAr;
    }

    public void setAddressAr(String addressAr) {
        this.addressAr = addressAr;
    }

    public String getDeliveryAddress() {
        return getDeliveryFullName() + "," + MyApplication.getInstance().getResources().getString(R.string.block) + ":" + getDeliveryBlock() + "," + MyApplication.getInstance().getResources().getString(R.string.street) + ":" + getDeliveryStreet() + "," + MyApplication.getInstance().getResources().getString(R.string.house) + ":" + getDeliveryHouseno() + "," + MyApplication.getInstance().getResources().getString(R.string.jadda) + ":" + getDeliveryJadda();
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryBlock() {
        return this.deliveryBlock;
    }

    public void setDeliveryBlock(String deliveryBlock) {
        this.deliveryBlock = deliveryBlock;
    }

    public String getDeliveryStreet() {
        return this.deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryHouseno() {
        return this.deliveryHouseno;
    }

    public void setDeliveryHouseno(String deliveryHouseno) {
        this.deliveryHouseno = deliveryHouseno;
    }

    public String getDeliveryApartment() {
        return this.deliveryApartment;
    }

    public void setDeliveryApartment(String deliveryApartment) {
        this.deliveryApartment = deliveryApartment;
    }

    public String getDeliveryFloor() {
        return this.deliveryFloor;
    }

    public void setDeliveryFloor(String deliveryFloor) {
        this.deliveryFloor = deliveryFloor;
    }

    public String getDeliveryJadda() {
        return this.deliveryJadda;
    }

    public void setDeliveryJadda(String deliveryJadda) {
        this.deliveryJadda = deliveryJadda;
    }

    public String getDeliveryInstruction() {
        return this.deliveryInstruction;
    }

    public void setDeliveryInstruction(String deliveryInstruction) {
        this.deliveryInstruction = deliveryInstruction;
    }

    public String getDeliveryStatus() {
        return this.deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPickupDatetime() {
        return this.pickupDatetime;
    }

    public void setPickupDatetime(String pickupDatetime) {
        this.pickupDatetime = pickupDatetime;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShippingCost() {
        return this.shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getTotalWeight() {
        return this.totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getPickupAddressId() {
        return this.pickupAddressId;
    }

    public void setPickupAddressId(String pickupAddressId) {
        this.pickupAddressId = pickupAddressId;
    }

    public String getDeliveryAddressId() {
        return this.deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryFullName() {
        return this.deliveryFullName;
    }

    public void setDeliveryFullName(String deliveryFullName) {
        this.deliveryFullName = deliveryFullName;
    }

    public String getDeliveryPhone() {
        return this.deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public String getDeliveryCountry() {
        return this.deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public String getDeliveryCity() {
        return this.deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryPostcode() {
        return this.deliveryPostcode;
    }

    public void setDeliveryPostcode(String deliveryPostcode) {
        this.deliveryPostcode = deliveryPostcode;
    }

    public String getDeliveryAddress1() {
        return this.deliveryAddress1;
    }

    public void setDeliveryAddress1(String deliveryAddress1) {
        this.deliveryAddress1 = deliveryAddress1;
    }

    public String getDeliveryAddress2() {
        return this.deliveryAddress2;
    }

    public void setDeliveryAddress2(String deliveryAddress2) {
        this.deliveryAddress2 = deliveryAddress2;
    }

    public ArrayList<Parcel> getParcelArrayList() {
        return this.parcelArrayList;
    }

    public void setParcelArrayList(ArrayList<Parcel> parcelArrayList) {
        this.parcelArrayList = parcelArrayList;
    }

    public String getPickupAddress() {
        return this.pickupAddress;
    }

    public String getPickupPhoneNo() {
        return this.pickupPhoneNo;
    }

    public void setPickupPhoneNo(String pickupPhoneNo) {
        this.pickupPhoneNo = pickupPhoneNo;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getPickupCity() {
        return this.pickupCity;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    public String getPickupStatus() {
        return this.pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }

    public String getOrderDatetime() {
        return this.orderDatetime;
    }

    public void setOrderDatetime(String orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public ShipmentData getShipmentData() {
        return this.shipmentData;
    }

    public void setShipmentData(ShipmentData shipmentData) {
        this.shipmentData = shipmentData;
    }

    public String getDeliveryaddressformat() {
        return MyApplication.isLTR ? this.deliveryaddressformat : this.deliveryaddressformatAr;
    }

    public void setDeliveryaddressformat(String deliveryaddressformat) {
        this.deliveryaddressformat = deliveryaddressformat;
    }

    public String getDeliveryaddressformatAr() {
        return this.deliveryaddressformatAr;
    }

    public void setDeliveryaddressformatAr(String deliveryaddressformatAr) {
        this.deliveryaddressformatAr = deliveryaddressformatAr;
    }

    public String getOrderStatusText() {
        return this.orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }
}
