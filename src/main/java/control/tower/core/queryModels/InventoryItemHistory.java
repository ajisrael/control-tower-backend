package control.tower.core.queryModels;

import control.tower.core.valueObjects.Location;
import control.tower.core.valueObjects.LocationHistory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter
@EqualsAndHashCode
@ToString
public class InventoryItemHistory {

    @Id
    @NonNull
    private String sku;
    @Embedded
    @NonNull
    private LocationHistory locationHistory;

    public InventoryItemHistory(String sku, Location startingLocation, Instant timestamp) {
        this.sku = sku;
        this.locationHistory = new LocationHistory();
        this.locationHistory.addLocation(startingLocation, timestamp);
    }

    public InventoryItemHistory () {
    }

    public void addLocationToLocationHistory(Location location, Instant timestamp) {
        this.locationHistory.addLocation(location, timestamp);
    }
}
