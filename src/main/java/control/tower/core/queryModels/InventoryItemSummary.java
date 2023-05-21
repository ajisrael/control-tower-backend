package control.tower.core.queryModels;

import control.tower.core.valueObjects.Location;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InventoryItemSummary {

    @Id
    @NonNull
    private String sku;
    @NonNull
    private String name;
    @NonNull
    private String locationId;
    @NonNull
    private String binId;
    @NonNull
    private double price;
    @NonNull
    private boolean picked = false;
    private String pickId = null;

    public InventoryItemSummary(String sku, String name, Location currentLocation, double price) {
        this.sku = sku;
        this.name = name;
        this.locationId = currentLocation.getLocationId();
        this.binId = currentLocation.getBinId();
        this.price = price;
    }

    public InventoryItemSummary() {} // Required by Axon
}

