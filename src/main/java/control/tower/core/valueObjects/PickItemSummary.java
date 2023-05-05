package control.tower.core.valueObjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PickItemSummary {

    @Column(name = "pick_item_sku")
    private String sku;

    private String locationId;

    private String binId;

    private boolean picked = false;

    public PickItemSummary(String sku, Location location) {
        this.sku = sku;
        this.locationId = location.getLocationId();
        this.binId = location.getBinId();
    }

    public PickItemSummary() {}

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }

    public boolean getPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickItemSummary that = (PickItemSummary) o;
        return picked == that.picked && Objects.equals(sku, that.sku) && Objects.equals(locationId, that.locationId) && Objects.equals(binId, that.binId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, locationId, binId, picked);
    }

    @Override
    public String toString() {
        return "PickItemSummary{" +
                "sku='" + sku + '\'' +
                ", locationId='" + locationId + '\'' +
                ", binId='" + binId + '\'' +
                ", picked=" + picked +
                '}';
    }

}
