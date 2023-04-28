package control.tower.core.valueObjects;

import javax.persistence.Embeddable;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class LocationEntry {
    private final Location location;
    private final Instant timestamp;

    public LocationEntry(Location location, Instant timestamp) {
        this.location = location;
        this.timestamp = timestamp;
    }

    public LocationEntry(String locationId, String binId, Instant timestamp) {
        this.location = new Location(locationId, binId);
        this.timestamp = timestamp;
    }

    public LocationEntry() {
        this.location = new Location("default","location");
        this.timestamp = Instant.now();
    }

    public Location getLocation() {
        return location;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationEntry that = (LocationEntry) o;
        return Objects.equals(location, that.location) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, timestamp);
    }

    @Override
    public String toString() {
        return "LocationEntry{" +
                "location=" + location +
                ", timestamp=" + timestamp +
                '}';
    }
}
