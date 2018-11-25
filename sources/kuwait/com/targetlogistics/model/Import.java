package kuwait.com.targetlogistics.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import java.io.Serializable;

public class Import extends BaseObservable implements Serializable {
    @Bindable
    private String address1;
    @Bindable
    private String address2;
    @Bindable
    private String cityId;
    private String countryCode;
    @Bindable
    private String countryId;
    @Bindable
    private String name;
    @Bindable
    private String parcelInfo;
    @Bindable
    private String parcelType;
    @Bindable
    private String phoneNo;
    @Bindable
    private String postCode;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        notifyPropertyChanged(14);
        this.name = name;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        notifyPropertyChanged(17);
        this.phoneNo = phoneNo;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
        notifyPropertyChanged(5);
        this.cityId = cityId;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        notifyPropertyChanged(6);
        this.countryId = countryId;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String postCode) {
        notifyPropertyChanged(19);
        this.postCode = postCode;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        notifyPropertyChanged(1);
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        notifyPropertyChanged(2);
        this.address2 = address2;
    }

    public String getParcelInfo() {
        return this.parcelInfo;
    }

    public void setParcelInfo(String parcelInfo) {
        notifyPropertyChanged(15);
        this.parcelInfo = parcelInfo;
    }

    public String getParcelType() {
        return this.parcelType;
    }

    public void setParcelType(String parcelType) {
        notifyPropertyChanged(16);
        this.parcelType = parcelType;
    }
}
