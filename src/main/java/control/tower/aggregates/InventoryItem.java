package control.tower.aggregates;

import control.tower.core.CreateInventoryItemCommand;
import control.tower.core.InventoryItemCreatedEvent;
import control.tower.core.InventoryItemMovedEvent;
import control.tower.core.MoveInventoryItemCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class InventoryItem {

    @AggregateIdentifier
    private String sku;

    private String name;

    private Location location;

    private List<Location> locationHistory;

    private double price;

    @CommandHandler
    public InventoryItem(CreateInventoryItemCommand command) {
        throwErrorIfSkuIsNullOrEmpty(command.getSku());
        throwErrorIfNameIsNullOrEmpty(command.getName());
        throwErrorIfPriceIsLessThanZero(command.getPrice());

        locationHistory.add(command.getLocation());
        apply(new InventoryItemCreatedEvent(command.getSku(), command.getName(), command.getLocation(), locationHistory, command.getPrice()));
    }

    @CommandHandler
    public InventoryItem(MoveInventoryItemCommand command) {
        throwErrorIfSkuIsNullOrEmpty(command.getSku());

        setNewLocationAndUpdateLocationHistory(command.getNewLocation());

        apply(new InventoryItemMovedEvent(sku, name, location, locationHistory, price));
    }

    private void setNewLocationAndUpdateLocationHistory(Location newLocation) {
        location.setEndTime(new Date());
        locationHistory.add(newLocation);
        location = newLocation;
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
    private void throwErrorIfSkuIsNullOrEmpty(String sku) {
        if (isNullOrEmpty(sku)) {
            throw new IllegalArgumentException("Sku cannot be null or empty");
        }
    }

    private void throwErrorIfNameIsNullOrEmpty(String name) {
        if (isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
    private void throwErrorIfPriceIsLessThanZero(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    public InventoryItem() {
        // Required by Axon
    }
}
