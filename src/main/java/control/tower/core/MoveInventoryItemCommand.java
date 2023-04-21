package control.tower.core;

import control.tower.aggregates.Location;
import org.axonframework.commandhandling.RoutingKey;

import java.util.Objects;

public class MoveInventoryItemCommand {

    @RoutingKey
    private final String movementId;

    private final String sku;
    private final Location newLocation;

    // TODO: Create constructor for local moves that only requires binId
    //  and gets locationId from corresponding inventoryItem using SKU
    public MoveInventoryItemCommand(String movementId, String sku, String locationId, String binId) {
        this.movementId = movementId;
        this.sku = sku;
        this.newLocation = new Location(locationId, binId);
    }

    public String getMovementId() {
        return movementId;
    }

    public String getSku() {
        return sku;
    }

    public Location getNewLocation() {
        return newLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveInventoryItemCommand that = (MoveInventoryItemCommand) o;
        return Objects.equals(movementId, that.movementId) && Objects.equals(sku, that.sku) && Objects.equals(newLocation, that.newLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movementId, sku, newLocation);
    }

    @Override
    public String toString() {
        return "MoveInventoryItemCommand{" +
                "movementId='" + movementId + '\'' +
                ", sku='" + sku + '\'' +
                ", newLocation=" + newLocation +
                '}';
    }
}
