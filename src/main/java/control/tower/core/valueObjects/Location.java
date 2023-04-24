package control.tower.core.valueObjects;

import org.axonframework.modelling.command.EntityId;

import java.util.Objects;

public class Location {

    @EntityId
    private final String locationKey;
    
    private final String locationId;
    
    private final String binId;

    public Location() {
        this.locationKey = "default_location_key";
        this.locationId = "default_location_id";
        this.binId = "default_bin_id";
    }

    public Location(String locationId, String binId) {
        this.locationKey = locationId + "_" + binId;
        this.locationId = locationId;
        this.binId = binId;
    }

    public String getLocationKey() {
        return locationKey;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getBinId() {
        return binId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(locationKey, location.locationKey) && Objects.equals(locationId, location.locationId) && Objects.equals(binId, location.binId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationKey, locationId, binId);
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationKey='" + locationKey + '\'' +
                ", locationId='" + locationId + '\'' +
                ", binId='" + binId + '\'' +
                '}';
    }
}
