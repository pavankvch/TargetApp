package kuwait.com.targetlogistics.model;

import java.io.Serializable;

public class HistoryList implements Serializable {
    private String date;
    private String deliveryCity;
    private String driverName;
    private boolean isDelivery = false;
    private boolean isHeader;
    private String noOfParcels;
    private String orderId;
    private String pickupCity;
    private String shipmentCost;
    private String weight;

    public String getShipmentCost() {
        return this.shipmentCost;
    }

    public void setShipmentCost(String shipmentCost) {
        this.shipmentCost = shipmentCost;
    }

    public String getDate() {
        return this.date;
    }

    public HistoryList setDate(String date) {
        this.date = date;
        return this;
    }

    public boolean isHeader() {
        return this.isHeader;
    }

    public HistoryList setHeader(boolean header) {
        this.isHeader = header;
        return this;
    }

    public String getPickupCity() {
        return this.pickupCity;
    }

    public HistoryList setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
        return this;
    }

    public String getDeliveryCity() {
        return this.deliveryCity;
    }

    public HistoryList setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
        return this;
    }

    public String getNoOfParcels() {
        return this.noOfParcels;
    }

    public HistoryList setNoOfParcels(String noOfParcels) {
        this.noOfParcels = noOfParcels;
        return this;
    }

    public String getWeight() {
        return this.weight;
    }

    public HistoryList setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public HistoryList setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isDelivery() {
        return this.isDelivery;
    }

    public void setDelivery(boolean delivery) {
        this.isDelivery = delivery;
    }
}
