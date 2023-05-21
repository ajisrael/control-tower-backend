package control.tower.core.projections;

import control.tower.core.events.*;
import control.tower.core.queries.CountInventoryItemSummariesQuery;
import control.tower.core.queries.FindInventoryItemSummariesQuery;
import control.tower.core.queryModels.InventoryItemSummary;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EqualsAndHashCode
@ToString
public class InventoryItemSummaryProjection {

    private final InventoryItemSummaryRepository inventoryItemSummaryRepository;

    public InventoryItemSummaryProjection(InventoryItemSummaryRepository inventoryItemSummaryRepository) {
        this.inventoryItemSummaryRepository = inventoryItemSummaryRepository;
    }

    @EventHandler
    public void on(InventoryItemCreatedEvent event) {
        inventoryItemSummaryRepository.save(
                new InventoryItemSummary(event.getSku(), event.getName(), event.getLocation(), event.getPrice())
        );
    }

    @EventHandler
    public void on(InventoryItemMovedEvent event) {
        inventoryItemSummaryRepository.findById(event.getSku()).ifPresent(
                inventoryItemSummary -> {
                    inventoryItemSummary.setLocationId(event.getLocation().getLocationId());
                    inventoryItemSummary.setBinId(event.getLocation().getBinId());
                    inventoryItemSummaryRepository.save(inventoryItemSummary);
                }
        );
    }

    @EventHandler
    public void on(InventoryItemAddedToPickListEvent event) {
        inventoryItemSummaryRepository.findById(event.getSku()).ifPresent(
                inventoryItemSummary -> {
                    inventoryItemSummary.setPickId(event.getPickId());
                    inventoryItemSummaryRepository.save(inventoryItemSummary);
                }
        );
    }

    @EventHandler
    public void on(InventoryItemRemovedFromPickListEvent event) {
        inventoryItemSummaryRepository.findById(event.getSku()).ifPresent(
                inventoryItemSummary -> {
                    inventoryItemSummary.setPickId(null);
                    inventoryItemSummaryRepository.save(inventoryItemSummary);
                }
        );
    }

    @EventHandler
    public void on(InventoryItemPickedEvent event) {
        inventoryItemSummaryRepository.findById(event.getSku()).ifPresent(
                inventoryItemSummary -> {
                    inventoryItemSummary.setPicked(true);
                    inventoryItemSummaryRepository.save(inventoryItemSummary);
                }
        );
    }

    @EventHandler
    public void on(PickListDeletedEvent event) {
        List<InventoryItemSummary> items = inventoryItemSummaryRepository.findByPickId(event.getPickId());

        for (InventoryItemSummary item : items) {
            item.setPickId(null);
        }
    }

    @QueryHandler
    public List<InventoryItemSummary> handle(FindInventoryItemSummariesQuery query) {
        return inventoryItemSummaryRepository.findAll(
                PageRequest.of(query.getPageNumber(), query.getLimit(), Sort.by("sku").ascending())
        ).getContent();
    }

    @QueryHandler
    public Long handle(CountInventoryItemSummariesQuery query) {
        return inventoryItemSummaryRepository.count();
    }
}
