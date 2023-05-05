package control.tower.api.query.inventoryItemSummary;

import control.tower.core.queries.FindInventoryItemSummariesQuery;
import control.tower.core.queryModels.InventoryItemSummary;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/inventoryItemSummary")
public class InventoryItemSummaryController {

    private final QueryGateway queryGateway;

    public InventoryItemSummaryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<InventoryItemSummary> getInventoryItemSummaries(@RequestParam(required = false, defaultValue = "0") int offset,
                                                                @RequestParam(required = false, defaultValue = "100") int limit) {
        return queryGateway.query(
                new FindInventoryItemSummariesQuery(offset, limit),
                ResponseTypes.multipleInstancesOf(InventoryItemSummary.class)
        ).join();
    }

    // TODO: Add endpoint for getting Inventory Item Summary by id

}
