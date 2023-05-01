package control.tower.core.projections;

import control.tower.core.events.InventoryItemCreatedEvent;
import control.tower.core.events.InventoryItemMovedEvent;
import control.tower.core.queries.FindInventoryItemSummariesQuery;
import control.tower.core.queryModels.InventoryItemHistory;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Component
@ProcessingGroup("inventory-item-history")
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

    @QueryHandler
    public List<InventoryItemHistory> handle(FindInventoryItemSummariesQuery query) {
        return inventoryItemHistoryRepository.findAll(
                PageRequest.of(query.getPageNumber(), query.getLimit(), Sort.by("sku").ascending())
        ).getContent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItemHistoryProjection that = (InventoryItemHistoryProjection) o;
        return Objects.equals(inventoryItemHistoryRepository, that.inventoryItemHistoryRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryItemHistoryRepository);
    }

    @Override
    public String toString() {
        return "InventoryItemHistoryProjection{" +
                "inventoryItemHistoryRepository=" + inventoryItemHistoryRepository +
                '}';
    }

}
