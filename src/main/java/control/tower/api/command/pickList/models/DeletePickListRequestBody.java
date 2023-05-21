package control.tower.api.command.pickList.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class DeletePickListRequestBody {

    @NonNull
    private String pickId;

    public DeletePickListRequestBody(@JsonProperty("pickId") String pickId) {
        this.pickId = pickId;
    }
}
