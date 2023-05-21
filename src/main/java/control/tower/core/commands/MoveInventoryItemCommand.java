package control.tower.core.commands;

import control.tower.core.valueObjects.Location;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@EqualsAndHashCode
@ToString
public class MoveInventoryItemCommand {

    @TargetAggregateIdentifier
    @NonNull
    private final String sku;
    @NonNull
    private final Location newLocation;

    // TODO: Create constructor for local moves that only requires binId
    //  and gets locationId from corresponding inventoryItem using SKU.
    //  or maybe adjust the command to hold locationId and binId as strings.
    //  Then just create another constructor that sets the locationId to null
    //  and update the event handler to use the existing locationId when the
    //  locationId on the event is null.

    public MoveInventoryItemCommand(String sku, String locationId, String binId) {
        this.sku = sku;
        this.newLocation = new Location(locationId, binId);
    }
}
