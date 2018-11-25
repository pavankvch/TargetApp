package kuwait.com.targetlogistics.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class InitializeResponse {
    @SerializedName("areas")
    private ArrayList<Area> areaArrayList;
    @SerializedName("countries")
    private ArrayList<Country> countryArrayList;
    @SerializedName("data")
    private JsonObject data;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;

    public ArrayList<Area> getAreaArrayList() {
        return this.areaArrayList;
    }

    public void setAreaArrayList(ArrayList<Area> areaArrayList) {
        this.areaArrayList = areaArrayList;
    }

    public ArrayList<Country> getCountryArrayList() {
        return this.countryArrayList;
    }

    public void setCountryArrayList(ArrayList<Country> countryArrayList) {
        this.countryArrayList = countryArrayList;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonObject getData() {
        return this.data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
