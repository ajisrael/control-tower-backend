package control.tower.core;

import control.tower.aggregates.InventoryItem;
import control.tower.aggregates.Location;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;
import java.util.Objects;

public class InventoryItemMovedEvent {

    private final String sku;

    private final String name;

    private final Location location;

    private final double price;

    public InventoryItemMovedEvent(String sku, String name, Location location, double price) {
        this.sku = sku;
        this.name = name;
        this.location = location;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItemMovedEvent that = (InventoryItemMovedEvent) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(sku, that.sku) && Objects.equals(name, that.name) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, location, price);
    }

    @Override
    public String toString() {
        return "InventoryItemMovedEvent{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", price=" + price +
                '}';
    }
}
