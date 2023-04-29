package control.tower.aggregates;

import control.tower.core.commands.CreatePickListCommand;
import control.tower.core.commands.PickInventoryItemCommand;
import control.tower.core.events.InventoryItemPickedEvent;
import control.tower.core.events.PickListCreatedEvent;
import control.tower.core.valueObjects.PickItem;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
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
    //  - boolean completed

    public PickList() {} // Required by Axon

    @CommandHandler
    public PickList(CreatePickListCommand command) {
        // TODO: Check that all skus in list exist and throw IllegalArgumentException if they don't
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());
        throwErrorIfSkuListIsEmpty(command.getSkuList());
        for (int i = 0; i < command.getSkuList().size() ; i++) {
            throwErrorIfSkuIsNullOrEmpty(command.getSkuList().get(i));
        }
        throwErrorIfDateIsNull(command.getPickDate());

        apply(new PickListCreatedEvent(command.getPickId(), command.getSkuList(), command.getPickDate()));
    }

    @CommandHandler
    public void handle(PickInventoryItemCommand command) {
        // TODO: Check that sku exists and throw IllegalArgumentException if it doesn't
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());
        throwErrorIfSkuIsNullOrEmpty(command.getSku());

        apply(new InventoryItemPickedEvent(pickId, command.getSku()));
    }

    @EventSourcingHandler
    public void on(PickListCreatedEvent event) {
        pickId = event.getPickId();
        pickDate = event.getPickDate();

        itemList = new ArrayList<>();
        for (int i = 0; i < event.getSkuList().size(); i++) {
            itemList.add(new PickItem(event.getSkuList().get(i)));
        }
    }

    @EventSourcingHandler
    public void on(InventoryItemPickedEvent event) {
        for (int i = 0; i < itemList.size(); i++) {
            PickItem currentItem = itemList.get(i);
            if (event.getSku() == currentItem.getSku()) {
                currentItem.setPicked(true);
            }
        }
    }

    // TODO: Write a helper method to match skus on regex to keep consistent format
    //   in corresponding validation method.

    // TODO: Move method to a utility or helper package to keep DRY with other aggregates
    private boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    private void throwErrorIfPickIdIsNullOrEmpty(String pickId) {
        if (isNullOrEmpty(pickId)) {
            throw new IllegalArgumentException("PickId cannot be null or empty");
        }
    }

    private void throwErrorIfSkuListIsEmpty(List<String> skuList) {
        if (skuList.size() == 0) {
            throw new IllegalArgumentException("Must have at least one sku in pick list");
        }
    }

    private void throwErrorIfSkuIsNullOrEmpty(String sku) {
        if (isNullOrEmpty(sku)) {
            throw new IllegalArgumentException("Sku cannot be null or empty");
        }
    }

    private void throwErrorIfDateIsNull(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
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
