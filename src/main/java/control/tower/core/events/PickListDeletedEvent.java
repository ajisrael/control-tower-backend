package control.tower.core.events;

import java.util.Objects;

public class PickListDeletedEvent {

    private final String pickId;

    public PickListDeletedEvent(String pickId) {
        this.pickId = pickId;
    }

    public String getPickId() {
        return pickId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickListDeletedEvent that = (PickListDeletedEvent) o;
        return Objects.equals(pickId, that.pickId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId);
    }

    @Override
    public String toString() {
        return "PickListDeletedEvent{" +
                "pickId='" + pickId + '\'' +
                '}';
    }
}
