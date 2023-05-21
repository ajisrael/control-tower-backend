package control.tower.core.projections;

import control.tower.core.queryModels.InventoryItemSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// InventoryItemSummary specifies the entity type and String specifies the type of identifier used for
// InventoryItemSummary instances
public interface InventoryItemSummaryRepository extends JpaRepository<InventoryItemSummary, String> {

    List<InventoryItemSummary> findByPickId(String pickId);

}
