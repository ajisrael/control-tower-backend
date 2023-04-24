package control.tower.projections;

import control.tower.core.*;
import control.tower.core.valueObjects.Location;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
                    inventoryItemSummary.setCurrentLocation(event.getLocation());
                    inventoryItemSummaryRepository.save(inventoryItemSummary);
                }
        );
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
