package control.tower.core;

import control.tower.core.valueObjects.Location;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class InventoryItemSummary {

    @Id
    private String sku;

    private String name;

    @Embedded
    private Location currentLocation;

    private double price;

    public InventoryItemSummary(String sku, String name, Location currentLocation, double price) {
        this.sku = sku;
        this.name = name;
        this.currentLocation = currentLocation;
        this.price = price;
    }

    public InventoryItemSummary() {} // Required by Axon

    public String getSku() {
        return this.sku;
    }

    public String getName() {
        return this.name;
    }

    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    public String getCurrentLocationKey() {
        return this.currentLocation.getLocationKey();
    }

    public double getPrice() {
        return this.price;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    @Override
    public String toString() {
        return "InventoryItemSummary{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", currentLocation=" + currentLocation +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItemSummary that = (InventoryItemSummary) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(sku, that.sku) && Objects.equals(name, that.name) && Objects.equals(currentLocation, that.currentLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, currentLocation, price);
    }

}

