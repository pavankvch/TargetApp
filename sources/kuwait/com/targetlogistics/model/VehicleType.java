package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kuwait.com.targetlogistics.MyApplication;

public class VehicleType implements Serializable {
    private boolean isSelected;
    @SerializedName("vehicle_id")
    private String vehicleId;
    @SerializedName("vehicle_name_en")
    private String vehicleName;
    @SerializedName("vehicle_name_ar")
    private String vehicleNameAr;

    public boolean isSelected() {
        return this.isSelected;
    }

    public VehicleType setSelected(boolean selected) {
        this.isSelected = selected;
        return this;
    }

    public String getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return MyApplication.isLTR ? this.vehicleName : this.vehicleNameAr;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNameAr() {
        return this.vehicleNameAr;
    }

    public void setVehicleNameAr(String vehicleNameAr) {
        this.vehicleNameAr = vehicleNameAr;
    }
}
