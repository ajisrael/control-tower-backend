package control.tower.core.projections;

import control.tower.core.events.*;
import control.tower.core.queries.FindPickListsQuery;
import control.tower.core.queryModels.InventoryItemSummary;
import control.tower.core.queryModels.PickListSummary;
import control.tower.core.valueObjects.Location;
import control.tower.core.valueObjects.PickItemSummary;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EqualsAndHashCode
@ToString
public class PickListSummaryProjection {

    private final PickListSummaryRepository pickListSummaryRepository;
    private final InventoryItemSummaryRepository inventoryItemSummaryRepository;

    public PickListSummaryProjection(
            PickListSummaryRepository pickListSummaryRepository,
            InventoryItemSummaryRepository inventoryItemSummaryRepository) {
        this.pickListSummaryRepository = pickListSummaryRepository;
        this.inventoryItemSummaryRepository = inventoryItemSummaryRepository;
    }

    @EventHandler
    public void on(PickListCreatedEvent event) {
        List<PickItemSummary> pickItemSummaryList = new ArrayList<>();

        pickListSummaryRepository.save(
                new PickListSummary(event.getPickId(), pickItemSummaryList, event.getPickDate())
        );
    }

    @EventHandler
    public void on(InventoryItemMovedEvent event) {
        // TODO: Find a way to do this more efficiently
        //  - may be way to link persistent location on item summary to pick list

        List<PickListSummary> pickListSummaries = pickListSummaryRepository.findAll();
        for (PickListSummary currentPickList : pickListSummaries) {
            for (PickItemSummary currentItem : currentPickList.getPickItemSummaryList()) {
                if (currentItem.getSku() == event.getSku()) {
                    currentItem.setLocationId(event.getLocation().getLocationId());
                    currentItem.setBinId(event.getLocation().getBinId());
                }
            }
        }

        pickListSummaryRepository.saveAll(pickListSummaries);
    }

    @EventHandler
    public void on(InventoryItemAddedToPickListEvent event) {
        List<PickItemSummary> pickItemSummaryList  = pickListSummaryRepository.findById(event.getPickId()).get().getPickItemSummaryList();
        InventoryItemSummary inventoryItemSummary = inventoryItemSummaryRepository.findById(event.getSku()).get();

        pickItemSummaryList.add(
                new PickItemSummary(
                        event.getSku(),
                        new Location(inventoryItemSummary.getLocationId(), inventoryItemSummary.getBinId())
                )
        );
    }

    @EventHandler
    public void on(InventoryItemRemovedFromPickListEvent event) {
        List<PickItemSummary> pickItemSummaryList = pickListSummaryRepository.findById(event.getPickId()).get().getPickItemSummaryList();

        for (PickItemSummary currentItem : pickItemSummaryList) {
            if (event.getSku() == currentItem.getSku()) {
                pickItemSummaryList.remove(currentItem);
                break;
            }
        }
    }

    @EventHandler
    public void on(InventoryItemPickedEvent event) {
        List<PickItemSummary> pickItemSummaryList = pickListSummaryRepository.findById(event.getPickId()).get().getPickItemSummaryList();

        for (PickItemSummary currentItem : pickItemSummaryList) {
            if (event.getSku() == currentItem.getSku()) {
                currentItem.setPicked(true);
            }
        }
    }

    @EventHandler
    public void on(PickListDeletedEvent event) {
        pickListSummaryRepository.deleteById(event.getPickId());
    }

    @QueryHandler
    public List<PickListSummary> handle(FindPickListsQuery query) {
        return pickListSummaryRepository.findAll(
                PageRequest.of(query.getPageNumber(), query.getLimit(), Sort.by("pickId").ascending())
        ).getContent();
    }
}
