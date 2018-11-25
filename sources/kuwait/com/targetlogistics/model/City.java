package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kuwait.com.targetlogistics.MyApplication;

public class City implements Serializable {
    @SerializedName("city_id")
    private String cityId;
    @SerializedName("city_name_en")
    private String cityName;
    @SerializedName("city_name_ar")
    private String cityNameAr;
    @SerializedName("country_id")
    private String countryId;

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCityName() {
        return MyApplication.isLTR ? this.cityName : this.cityNameAr;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameAr() {
        return this.cityNameAr;
    }

    public void setCityNameAr(String cityNameAr) {
        this.cityNameAr = cityNameAr;
    }
}
