package control.tower.aggregates;

import control.tower.core.commands.*;
import control.tower.core.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class PickList {

    @AggregateIdentifier
    private String pickId;

    private int itemCount;

    private Date pickDate;

    // TODO: Add additional fields:
    //  - boolean completed

    public PickList() {} // Required by Axon

    @CommandHandler
    public PickList(CreatePickListCommand command) {
        // TODO: Check that all skus are not currently assigned to a pick list and throw IllegalStateException if they are
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());
        throwErrorIfSkuListIsEmpty(command.getSkuList());
        throwErrorIfDateIsNull(command.getPickDate());

        for (int i = 0; i < command.getSkuList().size() ; i++) {
            throwErrorIfSkuIsNullOrEmpty(command.getSkuList().get(i));
        }

        apply(new PickListCreatedEvent(command.getPickId(), command.getSkuList(), command.getPickDate()));

        for (int i = 0; i < command.getSkuList().size(); i++) {
            apply(new InventoryItemAddedToPickListEvent(command.getPickId(), command.getSkuList().get(i)));
        }
    }

    @CommandHandler
    public void handle(AddInventoryItemToPickListCommand command) {
        // TODO: Check that sku exists and throw IllegalArgumentException if it doesn't
        // TODO: Check that all skus are not currently assigned to a pick list and throw IllegalStateException if they are
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());
        throwErrorIfSkuIsNullOrEmpty(command.getSku());

        apply(new InventoryItemAddedToPickListEvent(pickId, command.getSku()));
    }

    @CommandHandler
    public void handle(RemoveInventoryItemFromPickListCommand command) {
        // TODO: Check that sku exists and throw IllegalArgumentException if it doesn't
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());
        throwErrorIfSkuIsNullOrEmpty(command.getSku());

        apply(new InventoryItemRemovedFromPickListEvent(pickId, command.getSku()));

        // TODO: Automatically delete Pick list if it is the last sku in the list
    }

    @CommandHandler
    public void handle(PickInventoryItemCommand command) {
        // TODO: Check that sku exists and throw IllegalArgumentException if it doesn't
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());
        throwErrorIfSkuIsNullOrEmpty(command.getSku());
        // TODO: Throw error if item is already picked

        apply(new InventoryItemPickedEvent(pickId, command.getSku()));
    }

    @CommandHandler
    public void handle(DeletePickListCommand command) {
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());

        apply(new PickListDeletedEvent(command.getPickId()));
    }

    @EventSourcingHandler
    public void on(PickListCreatedEvent event) {
        pickId = event.getPickId();
        pickDate = event.getPickDate();
        itemCount = event.getSkuList().size();
    }

    @EventSourcingHandler
    public void on(InventoryItemAddedToPickListEvent event) {
        itemCount++;
    }

    @EventSourcingHandler
    public void on(InventoryItemRemovedFromPickListEvent event) {
        itemCount--;
        if (itemCount == 0) {
            apply(new PickListDeletedEvent(pickId));
        }
    }

    @EventSourcingHandler
    public void on(PickListDeletedEvent event) {
        AggregateLifecycle.markDeleted();
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

    public Date getPickDate() {
        return pickDate;
    }

    public void setPickDate(Date pickDate) {
        this.pickDate = pickDate;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickList pickList = (PickList) o;
        return itemCount == pickList.itemCount && Objects.equals(pickId, pickList.pickId) && Objects.equals(pickDate, pickList.pickDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId, itemCount, pickDate);
    }

    @Override
    public String toString() {
        return "PickList{" +
                "pickId='" + pickId + '\'' +
                ", itemCount=" + itemCount +
                ", pickDate=" + pickDate +
                '}';
    }
}
