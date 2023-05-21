package control.tower.core.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class PickListCreatedEvent {

    @NonNull
    private final String pickId;
    @NonNull
    private final List<String> skuList;
    @NonNull
    private final Date pickDate;

    public PickListCreatedEvent(String pickId, List<String> skuList, Date pickDate) {
        this.pickId = pickId;
        this.skuList = skuList;
        this.pickDate = pickDate;
    }
}
