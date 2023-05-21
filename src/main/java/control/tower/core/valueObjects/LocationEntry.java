package control.tower.core.valueObjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.time.Instant;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
public class LocationEntry {

    @NonNull
    private final String locationId;
    @NonNull
    private final String binId;
    @NonNull
    private final Instant timestamp;

    public LocationEntry(Location location, Instant timestamp) {
        this.locationId = location.getLocationId();
        this.binId = location.getBinId();
        this.timestamp = timestamp;
    }

    public LocationEntry() {
        this.locationId = "default";
        this.binId = "location";
        this.timestamp = Instant.now();
    }
}
