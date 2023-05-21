package control.tower.core.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class InventoryItemAddedToPickListEvent {

    @NonNull
    private final String pickId;
    @NonNull
    private final String sku;

    public InventoryItemAddedToPickListEvent(String pickId, String sku) {
        this.pickId = pickId;
        this.sku = sku;
    }
}
