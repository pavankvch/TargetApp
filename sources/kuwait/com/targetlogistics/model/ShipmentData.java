package kuwait.com.targetlogistics.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ShipmentData implements Serializable {
    @SerializedName("shipment_company")
    private String shipmentCompany;
    @SerializedName("shipment_reference")
    private String shipmentReference;
    @SerializedName("shipment_tracking")
    private String shipmentTracking;

    public String getShipmentCompany() {
        return this.shipmentCompany;
    }

    public void setShipmentCompany(String shipmentCompany) {
        this.shipmentCompany = shipmentCompany;
    }

    public String getShipmentReference() {
        return this.shipmentReference;
    }

    public void setShipmentReference(String shipmentReference) {
        this.shipmentReference = shipmentReference;
    }

    public String getShipmentTracking() {
        return this.shipmentTracking;
    }

    public void setShipmentTracking(String shipmentTracking) {
        this.shipmentTracking = shipmentTracking;
    }
}
