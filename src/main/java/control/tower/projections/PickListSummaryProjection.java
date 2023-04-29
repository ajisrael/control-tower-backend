package control.tower.projections;

import control.tower.core.events.InventoryItemMovedEvent;
import control.tower.core.events.PickListCreatedEvent;
import control.tower.core.queries.FindPickListsQuery;
import control.tower.core.queryModels.PickListSummary;
import control.tower.core.valueObjects.Location;
import control.tower.core.valueObjects.PickItem;
import control.tower.core.valueObjects.PickItemSummary;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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

        for (int i = 0; i < event.getSkuList().size(); i++) {
            String currentSku = event.getSkuList().get(i);
            Location skuLocation = inventoryItemSummaryRepository.findById(currentSku).get().getCurrentLocation();
            pickItemSummaryList.add(
                    new PickItemSummary(
                            currentSku,
                            skuLocation,
                            new PickItem(currentSku)
                    )
            );
        }

        pickListSummaryRepository.save(
                new PickListSummary(event.getPickId(), pickItemSummaryList)
        );
    }

    @EventHandler
    public void on(InventoryItemMovedEvent event) {
        // TODO: Find a way to do this more efficiently
        //  - may be way to link persistent location on item summary to pick list

        List<PickListSummary> pickListSummaries = pickListSummaryRepository.findAll();
        for (int i = 0; i < pickListSummaries.size(); i++) {
            PickListSummary currentPickList = pickListSummaries.get(i);
            for (int j = 0; j < currentPickList.getPickItemSummaryList().size(); j++) {
                PickItemSummary currentItem = currentPickList.getPickItemSummaryList().get(j);
                if (currentItem.getSku() == event.getSku()) {
                    currentItem.setLocation(event.getLocation());
                }
            }
        }
        pickListSummaryRepository.saveAll(pickListSummaries);
    }

    @QueryHandler
    public List<PickListSummary> handle(FindPickListsQuery query) {
        return pickListSummaryRepository.findAll(
                PageRequest.of(query.getPageNumber(), query.getLimit(), Sort.by("pickId").ascending())
        ).getContent();
    }
}
