package control.tower.core;

import control.tower.aggregates.Location;

import java.util.List;
import java.util.Objects;
public class InventoryItemCreatedEvent {

    private final String sku;

    private final String name;

    private final Location location;

    private final List<Location> locationHistory;

    private final double price;

    public InventoryItemCreatedEvent(String sku, String name, Location location, List<Location> locationHistory, double price) {
        this.sku = sku;
        this.name = name;
        this.location = location;
        this.locationHistory = locationHistory;
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

    public List<Location> getLocationHistory() {
        return locationHistory;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItemCreatedEvent that = (InventoryItemCreatedEvent) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(sku, that.sku) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && Objects.equals(locationHistory, that.locationHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, location, locationHistory, price);
    }

    @Override
    public String toString() {
        return "InventoryItemCreatedEvent{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", locationHistory=" + locationHistory +
                ", price=" + price +
                '}';
    }
}