package control.tower.core.events;

import java.util.Objects;

public class InventoryItemPickedEvent {

    private final String pickId;

    private final String sku;

    public InventoryItemPickedEvent(String pickId, String sku) {
        this.pickId = pickId;
        this.sku = sku;
    }

    public String getPickId() {
        return pickId;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItemPickedEvent that = (InventoryItemPickedEvent) o;
        return Objects.equals(pickId, that.pickId) && Objects.equals(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId, sku);
    }

    @Override
    public String toString() {
        return "InventoryItemPickedEvent{" +
                "pickId='" + pickId + '\'' +
                ", sku='" + sku + '\'' +
                '}';
    }

}
