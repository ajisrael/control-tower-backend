package control.tower.aggregates;

import control.tower.core.commands.*;
import control.tower.core.events.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;
import java.util.List;

import static control.tower.core.utils.Helper.isNullOrEmpty;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PickList {

    @AggregateIdentifier
    @NonNull
    private String pickId;
    @NonNull
    private int itemCount;
    @NonNull
    private Date pickDate;
    @NonNull
    private boolean completed = false;

    public PickList() {} // Required by Axon

    @CommandHandler
    public PickList(CreatePickListCommand command) {
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());
        throwErrorIfSkuListIsEmpty(command.getSkuList());
        throwErrorIfDateIsNull(command.getPickDate());

        for (String sku : command.getSkuList()) {
            throwErrorIfSkuIsNullOrEmpty(sku);
        }

        apply(new PickListCreatedEvent(command.getPickId(), command.getSkuList(), command.getPickDate()));

        for (String sku : command.getSkuList()) {
            apply(new InventoryItemAddedToPickListEvent(command.getPickId(), sku));
        }
    }

    @CommandHandler
    public void handle(AddInventoryItemToPickListCommand command) {
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());

        throwErrorIfSkuIsNullOrEmpty(command.getSku());

        apply(new InventoryItemAddedToPickListEvent(pickId, command.getSku()));
    }

    @CommandHandler
    public void handle(RemoveInventoryItemFromPickListCommand command) {
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());
        throwErrorIfSkuIsNullOrEmpty(command.getSku());

        apply(new InventoryItemRemovedFromPickListEvent(pickId, command.getSku()));
    }

    @CommandHandler
    public void handle(PickInventoryItemCommand command) {
        throwErrorIfPickIdIsNullOrEmpty(command.getPickId());

        throwErrorIfSkuIsNullOrEmpty(command.getSku());

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

    private void throwErrorIfPickIdIsNullOrEmpty(String pickId) {
        if (isNullOrEmpty(pickId)) {
            throw new IllegalArgumentException("PickId cannot be null or empty");
        }
    }

    private void throwErrorIfSkuListIsEmpty(List<String> skuList) {
        if (skuList.isEmpty()) {
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
}
