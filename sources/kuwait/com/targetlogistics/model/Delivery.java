package kuwait.com.targetlogistics.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import java.io.Serializable;

public class Delivery extends BaseObservable implements Serializable {
    @Bindable
    private String address1;
    @Bindable
    private String address2;
    private String addressId;
    @Bindable
    private String apartment;
    private String areaId;
    @Bindable
    private String block;
    private String cityId;
    private String countryId;
    @Bindable
    private String floor;
    @Bindable
    private String fullName;
    @Bindable
    private String house;
    @Bindable
    private String jadda;
    private String parcelInfo;
    @Bindable
    private String phoneNo;
    private String pickupDateTIme;
    @Bindable
    private String pickupInstruction;
    @Bindable
    private String postCode;
    @Bindable
    private String street;
    private String vehicleId;

    public String getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getBlock() {
        return this.block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return this.house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return this.apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getFloor() {
        return this.floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getJadda() {
        return this.jadda;
    }

    public void setJadda(String jadda) {
        this.jadda = jadda;
    }

    public String getPickupInstruction() {
        return this.pickupInstruction;
    }

    public void setPickupInstruction(String pickupInstruction) {
        this.pickupInstruction = pickupInstruction;
    }

    public String getAddressId() {
        return this.addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPickupDateTIme() {
        return this.pickupDateTIme;
    }

    public void setPickupDateTIme(String pickupDateTIme) {
        this.pickupDateTIme = pickupDateTIme;
    }

    public String getParcelInfo() {
        return this.parcelInfo;
    }

    public void setParcelInfo(String parcelInfo) {
        this.parcelInfo = parcelInfo;
    }
}
