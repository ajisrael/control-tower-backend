package control.tower.api.query.inventoryItemHistory;

import control.tower.core.queries.FindInventoryItemHistoriesQuery;
import control.tower.core.queryModels.InventoryItemHistory;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/inventoryItemHistory")
public class InventoryItemHistoryController {

    private final QueryGateway queryGateway;

    public InventoryItemHistoryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<InventoryItemHistory> getInventoryItemHistories(@RequestParam(required = false, defaultValue = "0") int offset,
                                                                @RequestParam(required = false, defaultValue = "100") int limit) {
        return queryGateway.query(
                new FindInventoryItemHistoriesQuery(offset, limit),
                ResponseTypes.multipleInstancesOf(InventoryItemHistory.class)
        ).join();
    }

    // TODO: Add endpoint for getting Inventory Item History for a single item by id

}
