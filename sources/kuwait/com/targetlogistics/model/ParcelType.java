package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kuwait.com.targetlogistics.MyApplication;

public class ParcelType implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("parcel_name_en")
    private String parcelType;
    @SerializedName("parcel_name_ar")
    private String parcelTypeAr;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParcelType() {
        return MyApplication.isLTR ? this.parcelType : this.parcelTypeAr;
    }

    public void setParcelType(String parcelType) {
        this.parcelType = parcelType;
    }

    public String getParcelTypeAr() {
        return this.parcelTypeAr;
    }

    public void setParcelTypeAr(String parcelTypeAr) {
        this.parcelTypeAr = parcelTypeAr;
    }
}
