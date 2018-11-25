package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kuwait.com.targetlogistics.MyApplication;

public class Country implements Serializable {
    @SerializedName("country_code")
    private String countryCode;
    @SerializedName("country_id")
    private String countryId;
    @SerializedName("country_name_en")
    private String countryName;
    @SerializedName("country_name_ar")
    private String countryNameAr;

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return MyApplication.isLTR ? this.countryName : this.countryNameAr;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryName = countryNameEn;
    }

    public String getCountryNameAr() {
        return this.countryNameAr;
    }

    public void setCountryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
