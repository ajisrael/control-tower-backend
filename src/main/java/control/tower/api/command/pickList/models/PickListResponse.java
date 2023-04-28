package control.tower.api.command.pickList.models;

import java.util.HashMap;
import java.util.Map;

public class PickListResponse {
    private Map<String, Object> response;

    private boolean success;

    private String message;

    public PickListResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.response = new HashMap<>();
        this.response.put("success", success);
        this.response.put("message", message);
    }

    public Map<String, Object> getResponse() {
        return response;
    }
}
