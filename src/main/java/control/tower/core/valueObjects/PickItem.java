package control.tower.core.valueObjects;

import org.axonframework.modelling.command.EntityId;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PickItem {

    @EntityId
    private String sku;
    private boolean picked;

    public PickItem(String sku) {
        this.sku = sku;
        this.picked = false;
    }

    public PickItem() {}

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickItem pickItem = (PickItem) o;
        return picked == pickItem.picked && Objects.equals(sku, pickItem.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, picked);
    }

    @Override
    public String toString() {
        return "PickItem{" +
                "sku='" + sku + '\'' +
                ", picked=" + picked +
                '}';
    }

}
