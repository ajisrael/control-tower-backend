package control.tower.projections;

import control.tower.core.InventoryItemSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemSummaryRepository extends JpaRepository<InventoryItemSummary, String> {
    // InventoryItemSummary specifies the entity type and String specifies the type of identifier used for
    // InventoryItemSummary instances
}
