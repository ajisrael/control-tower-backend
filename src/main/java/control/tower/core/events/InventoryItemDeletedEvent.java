package control.tower.core.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class InventoryItemDeletedEvent {

    @NonNull
    private final String sku;

    public InventoryItemDeletedEvent(String sku) {
        this.sku = sku;
    }
}
