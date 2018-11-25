package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kuwait.com.targetlogistics.common.DateUtils;

public class Notification implements Serializable {
    @SerializedName("push_date")
    private String dateTime;
    @SerializedName("description_ar")
    private String descriptionAr;
    @SerializedName("description_en")
    private String descriptionEn;
    @SerializedName("push_id")
    private String id;
    @SerializedName("title_ar")
    private String titleAr;
    @SerializedName("title_en")
    private String titleEn;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleEn() {
        return this.titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleAr() {
        return this.titleAr;
    }

    public void setTitleAr(String titleAr) {
        this.titleAr = titleAr;
    }

    public String getDescriptionEn() {
        return this.descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionAr() {
        return this.descriptionAr;
    }

    public void setDescriptionAr(String descriptionAr) {
        this.descriptionAr = descriptionAr;
    }

    public String getDateTime() {
        return DateUtils.formateDateFromstring(this.dateTime);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
