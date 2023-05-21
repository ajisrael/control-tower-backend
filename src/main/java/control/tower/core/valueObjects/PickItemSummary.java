package control.tower.core.valueObjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PickItemSummary {

    @Column(name = "pick_item_sku")
    @NonNull
    private String sku;
    @NonNull
    private String locationId;
    @NonNull
    private String binId;
    @NonNull
    private boolean picked = false;

    public PickItemSummary(String sku, Location location) {
        this.sku = sku;
        this.locationId = location.getLocationId();
        this.binId = location.getBinId();
    }

    public PickItemSummary() {}
}
