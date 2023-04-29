package control.tower.projections;

import control.tower.core.queryModels.PickListSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickListSummaryRepository extends JpaRepository<PickListSummary, String> {}
