package control.tower.core.events;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PickListCreatedEvent {

    private final String pickId;

    private final List<String> skuList;

    private final Date pickDate;

    public PickListCreatedEvent(String pickId, List<String> skuList, Date pickDate) {
        this.pickId = pickId;
        this.skuList = skuList;
        this.pickDate = pickDate;
    }

    public String getPickId() {
        return pickId;
    }

    public List<String> getSkuList() {
        return skuList;
    }

    public Date getPickDate() {
        return pickDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickListCreatedEvent that = (PickListCreatedEvent) o;
        return Objects.equals(pickId, that.pickId) && Objects.equals(skuList, that.skuList) && Objects.equals(pickDate, that.pickDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId, skuList, pickDate);
    }

    @Override
    public String toString() {
        return "PickListCreatedEvent{" +
                "listId='" + pickId + '\'' +
                ", skuList=" + skuList +
                ", pickDate=" + pickDate +
                '}';
    }
}
