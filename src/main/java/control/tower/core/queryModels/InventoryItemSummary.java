package control.tower.core.queryModels;

import control.tower.core.valueObjects.Location;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
public class InventoryItemSummary {

    @Id
    private String sku;

    private String name;

    private String locationId;

    private String binId;

    private double price;

    private String pickId = null;

    private boolean picked = false;

    public InventoryItemSummary(String sku, String name, Location currentLocation, double price) {
        this.sku = sku;
        this.name = name;
        this.locationId = currentLocation.getLocationId();
        this.binId = currentLocation.getBinId();
        this.price = price;
    }

    public InventoryItemSummary() {} // Required by Axon

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        InventoryItemSummary that = (InventoryItemSummary) o;
        return Double.compare(that.price, price) == 0 && picked == that.picked && Objects.equals(sku, that.sku) && Objects.equals(name, that.name) && Objects.equals(locationId, that.locationId) && Objects.equals(binId, that.binId) && Objects.equals(pickId, that.pickId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, locationId, binId, price, pickId, picked);
    }

    @Override
    public String toString() {
        return "InventoryItemSummary{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", locationId='" + locationId + '\'' +
                ", binId='" + binId + '\'' +
                ", price=" + price +
                ", pickId='" + pickId + '\'' +
                ", picked=" + picked +
                '}';
    }

}

