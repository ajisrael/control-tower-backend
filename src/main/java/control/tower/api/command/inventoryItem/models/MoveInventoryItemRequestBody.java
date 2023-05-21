package control.tower.api.command.inventoryItem.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class MoveInventoryItemRequestBody {

    @NonNull
    private String sku;
    @NonNull
    private String locationId;
    @NonNull
    private String binId;

    public MoveInventoryItemRequestBody(String sku, String locationId, String binId) {
        this.sku = sku;
        this.locationId = locationId;
        this.binId = binId;
    }
}
