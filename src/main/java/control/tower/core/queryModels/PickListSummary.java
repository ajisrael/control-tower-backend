package control.tower.core.queryModels;

import control.tower.core.valueObjects.PickItemSummary;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import java.util.List;
import java.util.Objects;

@Entity
public class PickListSummary {

    @Id
    private String pickId;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderBy("pick_item_sku DESC")
    private List<PickItemSummary> pickItemSummaryList;

    public PickListSummary(String pickId, List<PickItemSummary> pickItemSummaryList) {
        this.pickId = pickId;
        this.pickItemSummaryList = pickItemSummaryList;
    }

    public PickListSummary() {
    }

    public String getPickId() {
        return pickId;
    }

    public void setPickId(String pickId) {
        this.pickId = pickId;
    }

    public List<PickItemSummary> getPickItemSummaryList() {
        return pickItemSummaryList;
    }

    public void setPickItemSummaryList(List<PickItemSummary> pickItemSummaryList) {
        this.pickItemSummaryList = pickItemSummaryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickListSummary that = (PickListSummary) o;
        return Objects.equals(pickId, that.pickId) && Objects.equals(pickItemSummaryList, that.pickItemSummaryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId, pickItemSummaryList);
    }

    @Override
    public String toString() {
        return "PickListSummary{" +
                "pickId='" + pickId + '\'' +
                ", pickItemSummaryList=" + pickItemSummaryList +
                '}';
    }

}
