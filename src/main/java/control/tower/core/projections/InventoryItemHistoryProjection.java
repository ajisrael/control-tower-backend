package control.tower.core.projections;

import control.tower.core.events.InventoryItemCreatedEvent;
import control.tower.core.events.InventoryItemDeletedEvent;
import control.tower.core.events.InventoryItemMovedEvent;
import control.tower.core.events.PickListDeletedEvent;
import control.tower.core.queries.FindInventoryItemHistoriesQuery;
import control.tower.core.queryModels.InventoryItemHistory;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@ProcessingGroup("inventory-item-history")
@EqualsAndHashCode
@ToString
public class InventoryItemHistoryProjection {

    private final InventoryItemHistoryRepository inventoryItemHistoryRepository;

    public InventoryItemHistoryProjection(InventoryItemHistoryRepository inventoryItemHistoryRepository) {
        this.inventoryItemHistoryRepository = inventoryItemHistoryRepository;
    }

    @EventHandler
    public void on(InventoryItemCreatedEvent event, @Timestamp Instant createdAt) {
        inventoryItemHistoryRepository.save(
                new InventoryItemHistory(event.getSku(), event.getLocation(), createdAt)
        );
    }

    @EventHandler
    public void on(InventoryItemMovedEvent event, @Timestamp Instant movedAt) {
        inventoryItemHistoryRepository.findById(event.getSku()).ifPresent(
                inventoryItemHistory -> {
                    inventoryItemHistory.addLocationToLocationHistory(event.getLocation(), movedAt);
                    inventoryItemHistoryRepository.save(inventoryItemHistory);
                }
        );
    }

    @EventHandler
    public void on(InventoryItemDeletedEvent event) {
        inventoryItemHistoryRepository.deleteById(event.getSku());
    }

    @QueryHandler
    public List<InventoryItemHistory> handle(FindInventoryItemHistoriesQuery query) {
        return inventoryItemHistoryRepository.findAll(
                PageRequest.of(query.getPageNumber(), query.getLimit(), Sort.by("sku").ascending())
        ).getContent();
    }
}
