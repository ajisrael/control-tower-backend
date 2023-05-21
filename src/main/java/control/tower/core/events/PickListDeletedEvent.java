package control.tower.core.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;

@Getter
@EqualsAndHashCode
@ToString
public class PickListDeletedEvent {

    @Nonnull
    private final String pickId;

    public PickListDeletedEvent(String pickId) {
        this.pickId = pickId;
    }
}
