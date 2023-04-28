package control.tower.core.valueObjects;

import org.axonframework.modelling.command.EntityId;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Location {

    @EntityId
    private String locationKey;
    
    private String locationId;
    
    private String binId;

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

    public void setLocationKey(String locationKey) {
        this.locationKey = locationKey;
        this.locationId = locationKey.split("_")[0];
        this.binId = locationKey.split("_")[1];
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
        this.locationKey = locationId + "_" + this.binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
        this.locationKey = this.locationId + "_" + binId;
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
