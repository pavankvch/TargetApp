package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class UserInfo implements Serializable {
    @SerializedName("areaid")
    private String areaid;
    @SerializedName("email")
    private String email;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("id")
    private String id;
    @SerializedName("phone")
    private String phone;
    @SerializedName("status")
    private String status;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAreaid() {
        return this.areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
