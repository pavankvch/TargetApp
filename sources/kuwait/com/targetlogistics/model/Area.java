package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kuwait.com.targetlogistics.MyApplication;

public class Area implements Serializable {
    @SerializedName("area_id")
    private String areaId;
    @SerializedName("area_latitude")
    private Double areaLatitude;
    @SerializedName("area_longitude")
    private Double areaLongitude;
    @SerializedName("area_name_en")
    private String areaName;
    @SerializedName("area_name_ar")
    private String areaNameAr;

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return MyApplication.isLTR ? this.areaName : this.areaNameAr;
    }

    public void setAreaName(String areaNameEn) {
        this.areaName = areaNameEn;
    }

    public String getAreaNameAr() {
        return this.areaNameAr;
    }

    public void setAreaNameAr(String areaNameAr) {
        this.areaNameAr = areaNameAr;
    }

    public Double getAreaLatitude() {
        return this.areaLatitude;
    }

    public Area setAreaLatitude(Double areaLatitude) {
        this.areaLatitude = areaLatitude;
        return this;
    }

    public Double getAreaLongitude() {
        return this.areaLongitude;
    }

    public Area setAreaLongitude(Double areaLongitude) {
        this.areaLongitude = areaLongitude;
        return this;
    }
}
