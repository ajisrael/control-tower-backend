package control.tower.api.command.pickList.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
@ToString
public class PickListResponse {

    @NonNull
    private Map<String, Object> response;

    public PickListResponse(boolean success, String message) {
        this.response = new HashMap<>();
        this.response.put("success", success);
        this.response.put("message", message);
    }
}
