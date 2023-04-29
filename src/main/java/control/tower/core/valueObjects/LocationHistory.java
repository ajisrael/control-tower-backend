package control.tower.core.valueObjects;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OrderBy;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Embeddable
public class LocationHistory {

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderBy("timestamp DESC")
    private List<LocationEntry> locations = new ArrayList<>();

    public LocationHistory() {}

    public List<LocationEntry> getLocations() {
        return locations;
    }

    public void addLocation(Location location, Instant timestamp) {
        locations.add(new LocationEntry(location, timestamp));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationHistory that = (LocationHistory) o;
        return Objects.equals(locations, that.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locations);
    }

    @Override
    public String toString() {
        return "LocationHistory{" +
                "locations=" + locations +
                '}';
    }

}
