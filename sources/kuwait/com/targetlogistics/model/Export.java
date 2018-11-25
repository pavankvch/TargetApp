package kuwait.com.targetlogistics.model;

import android.databinding.BaseObservable;
import java.io.Serializable;

public class Export extends BaseObservable implements Serializable {
    private String address1;
    private String address2;
    private String addressId;
    private String cityId;
    private String countryId;
    private String fullName;
    private String parcelInfo;
    private String phoneNo;
    private String pickupDateTIme;
    private String postCode;

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
