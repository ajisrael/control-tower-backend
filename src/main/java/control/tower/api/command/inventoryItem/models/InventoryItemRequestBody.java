package control.tower.api.command.inventoryItem.models;

import java.util.Objects;

public class InventoryItemRequestBody {

    private String sku;

    private String name;

    private String locationId;

    private String binId;

    private double price;

    public InventoryItemRequestBody(String sku, String name, String locationId, String binId, double price) {
        this.sku = sku;
        this.name = name;
        this.locationId = locationId;
        this.binId = binId;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getBinId() {
        return binId;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItemRequestBody that = (InventoryItemRequestBody) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(sku, that.sku) && Objects.equals(name, that.name) && Objects.equals(locationId, that.locationId) && Objects.equals(binId, that.binId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, locationId, binId, price);
    }

    @Override
    public String toString() {
        return "InventoryItemRequestBody{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", locationId='" + locationId + '\'' +
                ", binId='" + binId + '\'' +
                ", price=" + price +
                '}';
    }
}
