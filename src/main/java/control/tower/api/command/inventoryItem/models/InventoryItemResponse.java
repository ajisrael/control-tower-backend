package control.tower.api.command.inventoryItem.models;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode
@ToString
public class InventoryItemResponse {

    @NonNull
    private Map<String, Object> response;

    public InventoryItemResponse(boolean success, String message) {
        this.response = new HashMap<>();
        this.response.put("success", success);
        this.response.put("message", message);
    }

    public Map<String, Object> getResponse() {
        return response;
    }
}
