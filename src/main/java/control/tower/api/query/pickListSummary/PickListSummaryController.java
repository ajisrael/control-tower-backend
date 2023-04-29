package control.tower.api.query.pickListSummary;

import control.tower.core.queries.FindPickListsQuery;
import control.tower.core.queryModels.PickListSummary;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/pickListSummary")
public class PickListSummaryController {

    private final QueryGateway queryGateway;

    public PickListSummaryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<PickListSummary> getPickListSummaries(@RequestParam(required = false, defaultValue = "0") int offset,
                                                      @RequestParam(required = false, defaultValue = "100") int limit) {
        return queryGateway.query(
                new FindPickListsQuery(offset, limit),
                ResponseTypes.multipleInstancesOf(PickListSummary.class)
        ).join();
    }
}
