package control.tower.core.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class PickInventoryItemCommand {

    @TargetAggregateIdentifier
    private final String pickId;

    private final String sku;

    public PickInventoryItemCommand(String pickId, String sku) {
        this.pickId = pickId;
        this.sku = sku;
    }

    public String getPickId() {
        return pickId;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickInventoryItemCommand that = (PickInventoryItemCommand) o;
        return Objects.equals(pickId, that.pickId) && Objects.equals(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId, sku);
    }

    @Override
    public String toString() {
        return "PickInventoryItemCommand{" +
                "pickId='" + pickId + '\'' +
                ", sku='" + sku + '\'' +
                '}';
    }

}
