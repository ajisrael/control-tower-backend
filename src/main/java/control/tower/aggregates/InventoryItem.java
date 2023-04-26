package control.tower.aggregates;

import control.tower.core.commands.CreateInventoryItemCommand;
import control.tower.core.events.InventoryItemCreatedEvent;
import control.tower.core.events.InventoryItemMovedEvent;
import control.tower.core.commands.MoveInventoryItemCommand;
import control.tower.core.valueObjects.Location;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class InventoryItem {

    @AggregateIdentifier
    private String sku;

    private String name;

    private Location location;

    private double price;

    @CommandHandler
    public InventoryItem(CreateInventoryItemCommand command) {
        throwErrorIfSkuIsNullOrEmpty(command.getSku());
        throwErrorIfNameIsNullOrEmpty(command.getName());
        throwErrorIfPriceIsLessThanZero(command.getPrice());

        apply(new InventoryItemCreatedEvent(command.getSku(), command.getName(), command.getLocation(), command.getPrice()));
    }

    @CommandHandler
    public void handle(MoveInventoryItemCommand command) {
        throwErrorIfSkuIsNullOrEmpty(command.getSku());

        // TODO: Throw error when movementId is not unique

        // TODO: Throw error when location is the same

        apply(new InventoryItemMovedEvent(sku, name, command.getNewLocation(), price));
    }

    @EventSourcingHandler
    public void on(InventoryItemCreatedEvent event) {
        sku = event.getSku();
        name = event.getName();
        location = event.getLocation();
        price = event.getPrice();
    }

    @EventSourcingHandler
    public void on(InventoryItemMovedEvent event) {

        location = event.getLocation();
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

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public InventoryItem() {
        // Required by Axon
    }
}
