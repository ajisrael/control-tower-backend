package control.tower.core.queryModels;

import control.tower.core.valueObjects.Location;
import control.tower.core.valueObjects.LocationEntry;
import control.tower.core.valueObjects.LocationHistory;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class InventoryItemHistory {

    @Id
    private String sku;

    @Embedded
    private LocationHistory locationHistory;

    public InventoryItemHistory(String sku, Location startingLocation, Instant timestamp) {
        this.sku = sku;
        this.locationHistory = new LocationHistory();
        this.locationHistory.addLocation(startingLocation, timestamp);
    }

    public InventoryItemHistory () {
    }

    public String getSku() {
        return sku;
    }

    public LocationHistory getLocationHistory() {
        return locationHistory;
    }

    public void addLocationToLocationHistory(Location location, Instant timestamp) {
        this.locationHistory.addLocation(location, timestamp);
    }

    @Override
    public String toString() {
        return "InventoryItemHistory{" +
                "sku='" + sku + '\'' +
                ", locationHistory=" + locationHistory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItemHistory that = (InventoryItemHistory) o;
        return Objects.equals(sku, that.sku) && Objects.equals(locationHistory, that.locationHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, locationHistory);
    }
}
