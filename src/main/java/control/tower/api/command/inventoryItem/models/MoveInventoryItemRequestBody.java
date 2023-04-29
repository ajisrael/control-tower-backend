package control.tower.api.command.inventoryItem.models;

import java.util.Objects;

public class MoveInventoryItemRequestBody {

    private String sku;

    private String locationId;

    private String binId;

    public MoveInventoryItemRequestBody(String sku, String locationId, String binId) {
        this.sku = sku;
        this.locationId = locationId;
        this.binId = binId;
    }

    public String getSku() {
        return sku;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getBinId() {
        return binId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveInventoryItemRequestBody that = (MoveInventoryItemRequestBody) o;
        return Objects.equals(sku, that.sku) && Objects.equals(locationId, that.locationId) && Objects.equals(binId, that.binId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, locationId, binId);
    }

    @Override
    public String toString() {
        return "MoveInventoryItemRequestBody{" +
                "sku='" + sku + '\'' +
                ", locationId='" + locationId + '\'' +
                ", binId='" + binId + '\'' +
                '}';
    }
}
