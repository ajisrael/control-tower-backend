package control.tower.api.command.pickList.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class PickListRequestBody {

    @NonNull
    private String pickId;
    @NonNull
    private List<String> skuList;
    @NonNull
    private String pickDate;

    public PickListRequestBody(String pickId, ArrayList<String> skuList, String pickDate) {
        this.pickId = pickId;
        this.skuList = skuList;
        this.pickDate = pickDate;
    }
}
