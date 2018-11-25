package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;

public class Value implements Serializable {
    @SerializedName("city_name_en")
    @Expose
    private String cityName;
    @SerializedName("city_name_ar")
    @Expose
    private String cityNameAr;
    @SerializedName("country_name_en")
    @Expose
    private String countryName;
    @SerializedName("country_name_ar")
    @Expose
    private String countryNameAr;
    private String date;
    @SerializedName("delivery_area_en")
    @Expose
    private String deliveryArea;
    @SerializedName("delivery_area_ar")
    @Expose
    private String deliveryAreaAr;
    private String deliveryCity;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    private boolean isDelivery = false;
    private boolean isHeader;
    @SerializedName("parcel")
    @Expose
    private String noOfParcels;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("area_name_ar")
    @Expose
    private String pickupCiAr;
    @SerializedName("area_name_en")
    @Expose
    private String pickupCity;
    @SerializedName("shippingcost")
    @Expose
    private String shipmentCost;
    @SerializedName("totalweight")
    @Expose
    private String weight;

    public String getPickupCity() {
        return MyApplication.isLTR ? this.pickupCity : this.pickupCiAr;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    public String getPickupCiAr() {
        return this.pickupCiAr;
    }

    public void setPickupCiAr(String pickupCiAr) {
        this.pickupCiAr = pickupCiAr;
    }

    public String getShipmentCost() {
        return this.shipmentCost + " " + MyApplication.getInstance().getResources().getString(R.string.price_unit);
    }

    public void setShipmentCost(String shipmentCost) {
        this.shipmentCost = shipmentCost;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNoOfParcels() {
        return this.noOfParcels;
    }

    public void setNoOfParcels(String noOfParcels) {
        this.noOfParcels = noOfParcels;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeliveryArea() {
        return MyApplication.isLTR ? this.deliveryArea : this.deliveryAreaAr;
    }

    public void setDeliveryArea(String deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public String getDeliveryAreaAr() {
        return this.deliveryAreaAr;
    }

    public void setDeliveryAreaAr(String deliveryAreaAr) {
        this.deliveryAreaAr = deliveryAreaAr;
    }

    public String getDeliveryCity() {
        return this.cityName + "," + this.countryName;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getCountryName() {
        return MyApplication.isLTR ? this.countryName : this.countryNameAr;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCountryNameAr() {
        return this.countryNameAr;
    }

    public void setCountryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
    }

    public String getCityNameAr() {
        return this.cityNameAr;
    }

    public void setCityNameAr(String cityNameAr) {
        this.cityNameAr = cityNameAr;
    }

    public String getCityName() {
        return MyApplication.isLTR ? this.cityName : this.cityNameAr;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isHeader() {
        return this.isHeader;
    }

    public void setHeader(boolean header) {
        this.isHeader = header;
    }

    public boolean isDelivery() {
        return this.isDelivery;
    }

    public void setDelivery(boolean delivery) {
        this.isDelivery = delivery;
    }
}
