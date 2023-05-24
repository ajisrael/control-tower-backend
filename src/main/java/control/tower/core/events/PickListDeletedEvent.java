package control.tower.core.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class PickListDeletedEvent {

    @NonNull
    private final String pickId;

    public PickListDeletedEvent(String pickId) {
        this.pickId = pickId;
    }
}
