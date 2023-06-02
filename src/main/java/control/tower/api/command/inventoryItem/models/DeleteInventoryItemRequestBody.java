package control.tower.api.command.inventoryItem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class DeleteInventoryItemRequestBody {

    @NonNull
    private String sku;

    public DeleteInventoryItemRequestBody(@JsonProperty("sku") String sku) {
        this.sku = sku;
    }
}
