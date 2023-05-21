package control.tower.api.command.pickList.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UpdatePickListRequestBody {

    @NonNull
    private String pickId;
    @NonNull
    private String sku;

    public UpdatePickListRequestBody(String pickId, String sku) {
        this.pickId = pickId;
        this.sku = sku;
    }
}
