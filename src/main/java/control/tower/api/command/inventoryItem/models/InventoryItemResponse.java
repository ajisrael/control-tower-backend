package control.tower.api.command.inventoryItem.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InventoryItemResponse {

    private Map<String, Object> response;

    private boolean success;

    private String message;

    public InventoryItemResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.response = new HashMap<>();
        this.response.put("success", success);
        this.response.put("message", message);
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItemResponse that = (InventoryItemResponse) o;
        return success == that.success && Objects.equals(response, that.response) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(response, success, message);
    }

    @Override
    public String toString() {
        return "InventoryItemResponse{" +
                "response=" + response +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
