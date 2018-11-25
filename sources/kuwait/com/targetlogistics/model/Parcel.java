package kuwait.com.targetlogistics.model;

import android.databinding.BaseObservable;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Parcel extends BaseObservable implements Serializable {
    @SerializedName("parcel_cost_value")
    private String costValue;
    @SerializedName("parcel_height")
    private String height;
    private boolean isAdded = false;
    @SerializedName("parcel_length")
    private String length;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("parcel_description")
    private String parcelDetailDescription;
    @SerializedName("parcel_id")
    private String parcelId;
    @SerializedName("parcel_type")
    private String parcelType;
    private String parcelTypeId;
    @SerializedName("parcel_quantity")
    private String quantity;
    @SerializedName("parcel_weight")
    private String weightOfParcel;
    @SerializedName("parcel_width")
    private String width;

    public String getParcelType() {
        return this.parcelType;
    }

    public Parcel setParcelType(String parcelType) {
        this.parcelType = parcelType;
        return this;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public Parcel setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCostValue() {
        return this.costValue;
    }

    public Parcel setCostValue(String costValue) {
        this.costValue = costValue;
        return this;
    }

    public String getWeightOfParcel() {
        return this.weightOfParcel;
    }

    public Parcel setWeightOfParcel(String weightOfParcel) {
        this.weightOfParcel = weightOfParcel;
        return this;
    }

    public String getParcelDetailDescription() {
        return this.parcelDetailDescription;
    }

    public Parcel setParcelDetailDescription(String parcelDetailDescription) {
        this.parcelDetailDescription = parcelDetailDescription;
        return this;
    }

    public boolean isAdded() {
        return this.isAdded;
    }

    public void setAdded(boolean added) {
        this.isAdded = added;
    }

    public String getParcelId() {
        return this.parcelId;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getParcelTypeId() {
        return this.parcelTypeId;
    }

    public void setParcelTypeId(String parcelTypeId) {
        this.parcelTypeId = parcelTypeId;
    }

    public String getLength() {
        return this.length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return this.width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
