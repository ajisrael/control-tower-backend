package control.tower.core.commands;

import control.tower.core.valueObjects.Location;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.axonframework.commandhandling.RoutingKey;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@ToString
public class CreateInventoryItemCommand {

    @RoutingKey
    @NonNull
    private final String sku;
    @NonNull
    private final Location location;
    @NonNull
    private final String name;
    @NonNull
    private final double price;

    public CreateInventoryItemCommand(String sku, String locationId, String binId, String name, double price) {
        this.sku = sku;
        this.location = new Location(locationId, binId);
        this.name = name;
        this.price = price;
    }
}
