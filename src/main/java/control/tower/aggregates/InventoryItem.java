package control.tower.aggregates;

import control.tower.core.commands.CreateInventoryItemCommand;
import control.tower.core.commands.DeleteInventoryItemCommand;
import control.tower.core.commands.MoveInventoryItemCommand;
import control.tower.core.events.InventoryItemAddedToPickListEvent;
import control.tower.core.events.InventoryItemCreatedEvent;
import control.tower.core.events.InventoryItemDeletedEvent;
import control.tower.core.events.InventoryItemMovedEvent;
import control.tower.core.events.InventoryItemPickedEvent;
import control.tower.core.events.InventoryItemRemovedFromPickListEvent;
import control.tower.core.events.PickListDeletedEvent;
import control.tower.core.valueObjects.Location;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static control.tower.core.utils.Helper.isNullOrEmpty;

@Aggregate
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InventoryItem {

    @AggregateIdentifier
    @NonNull
    private String sku;
    @NonNull
    private String name;
    @NonNull
    private Location location;
    @NonNull
    private double price;
    @NonNull
    private boolean picked = false;
    private String pickId = null;

    // TODO: Review what other fields are required for an Inventory Item
    //  - Customer
    //  - May not need price
    //  - How to keep track of skus that are parts of a set?
    //  - State field to keep track of in inventory or not?

    public InventoryItem() {} // Required by Axon

    @CommandHandler
    public InventoryItem(CreateInventoryItemCommand command) {
        throwErrorIfSkuIsNullOrEmpty(command.getSku());
        throwErrorIfNameIsNullOrEmpty(command.getName());
        throwErrorIfLocationIsNullOrIdsAreNullOrEmpty(command.getLocation());
        throwErrorIfPriceIsLessThanZero(command.getPrice());

        apply(new InventoryItemCreatedEvent(command.getSku(), command.getName(), command.getLocation(), command.getPrice()));
    }

    @CommandHandler
    public void handle(MoveInventoryItemCommand command) {
        throwErrorIfLocationIsNullOrIdsAreNullOrEmpty(command.getNewLocation());
        throwErrorIfNewLocationIsEqualToCurrentLocation(command.getNewLocation());

        apply(new InventoryItemMovedEvent(sku, name, command.getNewLocation(), price));
    }

    @CommandHandler
    public void handle(DeleteInventoryItemCommand command) {
        throwErrorIfSkuIsNullOrEmpty(command.getSku());

        apply(new InventoryItemDeletedEvent(command.getSku()));
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

    @EventSourcingHandler
    public void on(InventoryItemAddedToPickListEvent event) {
        pickId = event.getPickId();
    }

    @EventSourcingHandler
    public void on(InventoryItemRemovedFromPickListEvent event) {
        if (sku == event.getSku()) {
            pickId = null;
        }
    }

    @EventSourcingHandler
    public void on(InventoryItemPickedEvent event) {
        if (sku == event.getSku()) {
            picked = true;
        }
    }

    @EventSourcingHandler
    public void on(PickListDeletedEvent event) {
        if (pickId == event.getPickId()) {
            pickId = null;
        }
    }

    @EventSourcingHandler
    public void on(InventoryItemDeletedEvent event) {
        AggregateLifecycle.markDeleted();
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

    private void throwErrorIfLocationIsNullOrIdsAreNullOrEmpty(Location location) {
        if (location == null || isNullOrEmpty(location.getLocationId()) || isNullOrEmpty(location.getBinId())) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
    }

    private void throwErrorIfPriceIsLessThanZero(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    private void throwErrorIfNewLocationIsEqualToCurrentLocation(Location newLocation) {
        if (newLocation.equals(location)) {
            throw new IllegalArgumentException("Location must be different than current location");
        }
    }
}
