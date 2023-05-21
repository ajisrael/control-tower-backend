package control.tower.core.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class InventoryItemRemovedFromPickListEvent {

    @NonNull
    private final String pickId;
    @NonNull
    private final String sku;

    public InventoryItemRemovedFromPickListEvent(String pickId, String sku) {
        this.pickId = pickId;
        this.sku = sku;
    }
}
