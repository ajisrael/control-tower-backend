package control.tower.projections;

import control.tower.core.queryModels.InventoryItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemHistoryRepository extends JpaRepository<InventoryItemHistory, String> {
}
