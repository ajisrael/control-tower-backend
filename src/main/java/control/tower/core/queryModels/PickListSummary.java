package control.tower.core.queryModels;

import control.tower.core.valueObjects.PickItemSummary;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
@ToString
public class PickListSummary {

    @Id
    @NonNull
    private String pickId;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderBy("pick_item_sku DESC")
    @NonNull
    private List<PickItemSummary> pickItemSummaryList;

    @NonNull
    private Date pickDate;

    public PickListSummary(String pickId, List<PickItemSummary> pickItemSummaryList, Date pickDate) {
        this.pickId = pickId;
        this.pickItemSummaryList = pickItemSummaryList;
        this.pickDate = pickDate;
    }

    public PickListSummary() {}
}
