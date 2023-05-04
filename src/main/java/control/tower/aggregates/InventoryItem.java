package control.tower.aggregates;

import control.tower.core.commands.CreateInventoryItemCommand;
import control.tower.core.events.*;
import control.tower.core.commands.MoveInventoryItemCommand;
import control.tower.core.valueObjects.Location;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Objects;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class InventoryItem {

    @AggregateIdentifier
    private String sku;

    private String name;

    private Location location;

    private double price;

    private String pickId = null;

    private boolean picked = false;

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

    public String getPickId() {
        return pickId;
    }

    public void setPickId(String pickId) {
        this.pickId = pickId;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItem that = (InventoryItem) o;
        return Double.compare(that.price, price) == 0 && picked == that.picked && Objects.equals(sku, that.sku) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && Objects.equals(pickId, that.pickId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, location, price, pickId, picked);
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", price=" + price +
                ", pickId='" + pickId + '\'' +
                ", picked=" + picked +
                '}';
    }

}
