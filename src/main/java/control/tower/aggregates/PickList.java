package control.tower.aggregates;

import control.tower.core.commands.CreatePickListCommand;
import control.tower.core.events.PickListCreatedEvent;
import control.tower.core.valueObjects.PickItem;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class PickList {

    @AggregateIdentifier
    private String pickId;

    private List<PickItem> itemList;

    private Date pickDate;

    // TODO: Add additional fields:
    //  - PickState state { completed: boolean, timestamp: Instant }

    public PickList() {} // Required by Axon

    @CommandHandler
    public PickList(CreatePickListCommand command) {
        // TODO: Add validation for command
        // TODO: Check that all skus in list exist and
        //  throw IllegalStateException if they don't

        apply(new PickListCreatedEvent(command.getPickId(), command.getSkuList(), command.getPickDate()));
    }

    @EventHandler
    public void on(PickListCreatedEvent event) {
        pickId = event.getPickId();
        pickDate = event.getPickDate();

        itemList = new ArrayList<>();
        for (int i = 0; i < event.getSkuList().size(); i++) {
            itemList.add(new PickItem(event.getSkuList().get(i)));
        }
    }

    public String getPickId() {
        return pickId;
    }

    public void setPickId(String pickId) {
        this.pickId = pickId;
    }

    public List<PickItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<PickItem> itemList) {
        this.itemList = itemList;
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
        PickList pickList = (PickList) o;
        return Objects.equals(pickId, pickList.pickId) && Objects.equals(itemList, pickList.itemList) && Objects.equals(pickDate, pickList.pickDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId, itemList, pickDate);
    }

    @Override
    public String toString() {
        return "PickList{" +
                "pickId='" + pickId + '\'' +
                ", itemList=" + itemList +
                ", pickDate=" + pickDate +
                '}';
    }

}
