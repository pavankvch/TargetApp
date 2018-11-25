package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kuwait.com.targetlogistics.MyApplication;

public class CMS implements Serializable {
    @SerializedName("aboutapp_en")
    private String aboutApp;
    @SerializedName("aboutapp_ar")
    private String aboutAppAr;
    @SerializedName("terms_ar")
    private String termsAr;
    @SerializedName("terms_en")
    private String termsText;

    public String getTermsText() {
        return MyApplication.isLTR ? this.termsText : this.termsAr;
    }

    public void setTermsText(String termsText) {
        this.termsText = termsText;
    }

    public String getTermsAr() {
        return this.termsAr;
    }

    public void setTermsAr(String termsAr) {
        this.termsAr = termsAr;
    }

    public String getAboutApp() {
        return MyApplication.isLTR ? this.aboutApp : this.aboutAppAr;
    }

    public void setAboutApp(String aboutApp) {
        this.aboutApp = aboutApp;
    }

    public String getAboutAppAr() {
        return this.aboutAppAr;
    }

    public void setAboutAppAr(String aboutAppAr) {
        this.aboutAppAr = aboutAppAr;
    }
}
