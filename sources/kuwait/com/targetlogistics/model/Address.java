package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kuwait.com.targetlogistics.MyApplication;
import kuwait.com.targetlogistics.R;

public class Address implements Serializable {
    private String address;
    @SerializedName("customer_id")
    private String customerId;
    private boolean isAddressSelected;
    @SerializedName("pickup_phone")
    private String phoneNo;
    @SerializedName("pickupaddress_id")
    private String pickupAddressId;
    @SerializedName("pickup_addressname")
    private String pickupAddressname;
    @SerializedName("pickup_apartment")
    private String pickupApartment;
    @SerializedName("pickup_area")
    private String pickupArea;
    @SerializedName("pickup_block")
    private String pickupBlock;
    @SerializedName("pickup_floor")
    private String pickupFloor;
    @SerializedName("pickup_houseno")
    private String pickupHouseno;
    @SerializedName("pickup_instruction")
    private String pickupInstruction;
    @SerializedName("pickup_latitude")
    private String pickupLatitude;
    @SerializedName("pickup_longitude")
    private String pickupLongitude;
    @SerializedName("pickup_status")
    private String pickupStatus;
    @SerializedName("pickup_street")
    private String pickupStreet;
    @SerializedName("pickup_jadda")
    private String pickup_Jadda;

    public boolean isAddressSelected() {
        return this.isAddressSelected;
    }

    public Address setAddressSelected(boolean addressSelected) {
        this.isAddressSelected = addressSelected;
        return this;
    }

    public String getAddress() {
        return getPickupAddressname() + "," + MyApplication.getInstance().getResources().getString(R.string.block) + ":" + getPickupBlock() + "," + MyApplication.getInstance().getResources().getString(R.string.street) + ":" + getPickupStreet() + "," + MyApplication.getInstance().getResources().getString(R.string.house) + ":" + getPickupHouseno() + "," + MyApplication.getInstance().getResources().getString(R.string.jadda) + ":" + getPickup_Jadda();
    }

    public Address setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public Address setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public String getPickupAddressId() {
        return this.pickupAddressId;
    }

    public void setPickupAddressId(String pickupAddressId) {
        this.pickupAddressId = pickupAddressId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPickupArea() {
        return this.pickupArea;
    }

    public void setPickupArea(String pickupArea) {
        this.pickupArea = pickupArea;
    }

    public String getPickupAddressname() {
        return this.pickupAddressname;
    }

    public void setPickupAddressname(String pickupAddressname) {
        this.pickupAddressname = pickupAddressname;
    }

    public String getPickupBlock() {
        return this.pickupBlock;
    }

    public void setPickupBlock(String pickupBlock) {
        this.pickupBlock = pickupBlock;
    }

    public String getPickupStreet() {
        return this.pickupStreet;
    }

    public void setPickupStreet(String pickupStreet) {
        this.pickupStreet = pickupStreet;
    }

    public String getPickupHouseno() {
        return this.pickupHouseno;
    }

    public void setPickupHouseno(String pickupHouseno) {
        this.pickupHouseno = pickupHouseno;
    }

    public String getPickupApartment() {
        return this.pickupApartment;
    }

    public void setPickupApartment(String pickupApartment) {
        this.pickupApartment = pickupApartment;
    }

    public String getPickupFloor() {
        return this.pickupFloor;
    }

    public void setPickupFloor(String pickupFloor) {
        this.pickupFloor = pickupFloor;
    }

    public String getPickup_Jadda() {
        return this.pickup_Jadda;
    }

    public void setPickup_Jadda(String pickup_Jadda) {
        this.pickup_Jadda = pickup_Jadda;
    }

    public String getPickupInstruction() {
        return this.pickupInstruction;
    }

    public void setPickupInstruction(String pickupInstruction) {
        this.pickupInstruction = pickupInstruction;
    }

    public String getPickupLatitude() {
        return this.pickupLatitude;
    }

    public void setPickupLatitude(String pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public String getPickupLongitude() {
        return this.pickupLongitude;
    }

    public void setPickupLongitude(String pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public String getPickupStatus() {
        return this.pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }
}
