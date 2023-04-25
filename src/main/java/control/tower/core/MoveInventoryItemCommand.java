package control.tower.core;

import control.tower.core.valueObjects.Location;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class MoveInventoryItemCommand {

    @TargetAggregateIdentifier
    private final String sku;
    private final Location newLocation;

    // TODO: Create constructor for local moves that only requires binId
    //  and gets locationId from corresponding inventoryItem using SKU

    public MoveInventoryItemCommand(String sku, String locationId, String binId) {
        this.sku = sku;
        this.newLocation = new Location(locationId, binId);
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
        return Objects.equals(sku, that.sku) && Objects.equals(newLocation, that.newLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, newLocation);
    }

    @Override
    public String toString() {
        return "MoveInventoryItemCommand{" +
                "sku='" + sku + '\'' +
                ", newLocation=" + newLocation +
                '}';
    }
}