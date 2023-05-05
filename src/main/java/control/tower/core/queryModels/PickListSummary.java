package control.tower.core.queryModels;

import control.tower.core.valueObjects.PickItemSummary;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class PickListSummary {

    @Id
    private String pickId;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderBy("pick_item_sku DESC")
    private List<PickItemSummary> pickItemSummaryList;

    private Date pickDate;

    public PickListSummary(String pickId, List<PickItemSummary> pickItemSummaryList, Date pickDate) {
        this.pickId = pickId;
        this.pickItemSummaryList = pickItemSummaryList;
        this.pickDate = pickDate;
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

    public Date getPickDate() {
        return pickDate;
    }

    public void setPickDate(Date pickDate) {
        this.pickDate = pickDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickListSummary that = (PickListSummary) o;
        return Objects.equals(pickId, that.pickId) && Objects.equals(pickItemSummaryList, that.pickItemSummaryList) && Objects.equals(pickDate, that.pickDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId, pickItemSummaryList, pickDate);
    }

    @Override
    public String toString() {
        return "PickListSummary{" +
                "pickId='" + pickId + '\'' +
                ", pickItemSummaryList=" + pickItemSummaryList +
                ", pickDate=" + pickDate +
                '}';
    }
}
