package control.tower.api.command.inventoryItem.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class InventoryItemRequestBody {

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

    public InventoryItemRequestBody(String sku, String name, String locationId, String binId, double price) {
        this.sku = sku;
        this.name = name;
        this.locationId = locationId;
        this.binId = binId;
        this.price = price;
    }
}
