package control.tower.core.events;

import control.tower.core.valueObjects.Location;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class InventoryItemMovedEvent {

    @NonNull
    private final String sku;
    @NonNull
    private final String name;
    @NonNull
    private final Location location;
    @NonNull
    private final double price;

    public InventoryItemMovedEvent(String sku, String name, Location location, double price) {
        this.sku = sku;
        this.name = name;
        this.location = location;
        this.price = price;
    }
}
