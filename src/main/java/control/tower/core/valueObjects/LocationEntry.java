package control.tower.core.valueObjects;

import javax.persistence.Embeddable;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class LocationEntry {

    private final String locationId;

    private final String binId;

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

    public String getLocationId() {
        return locationId;
    }

    public String getBinId() {
        return binId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationEntry that = (LocationEntry) o;
        return Objects.equals(locationId, that.locationId) && Objects.equals(binId, that.binId) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, binId, timestamp);
    }

    @Override
    public String toString() {
        return "LocationEntry{" +
                "locationId='" + locationId + '\'' +
                ", binId='" + binId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
