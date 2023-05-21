package control.tower.core.valueObjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.axonframework.modelling.command.EntityId;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
public class Location {

    @EntityId
    @NonNull
    private String locationKey;
    @NonNull
    private String locationId;
    @NonNull
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
}
