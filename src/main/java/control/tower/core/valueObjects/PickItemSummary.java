package control.tower.core.valueObjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PickItemSummary {

    @Column(name = "pick_item_sku")
    private String sku;

    private Location location;

    private PickItem pickItem;

    public PickItemSummary(String sku, Location location, PickItem pickItem) {
        this.sku = sku;
        this.location = location;
        this.pickItem = pickItem;
    }

    public PickItemSummary() {
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PickItem getPickItem() {
        return pickItem;
    }

    public void setPickItem(PickItem pickItem) {
        this.pickItem = pickItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickItemSummary that = (PickItemSummary) o;
        return Objects.equals(sku, that.sku) && Objects.equals(location, that.location) && Objects.equals(pickItem, that.pickItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, location, pickItem);
    }

    @Override
    public String toString() {
        return "PickItemSummary{" +
                "sku='" + sku + '\'' +
                ", location=" + location +
                ", pickItem=" + pickItem +
                '}';
    }
}
