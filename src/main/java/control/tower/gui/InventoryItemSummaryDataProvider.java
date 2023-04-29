package control.tower.gui;

import com.vaadin.data.provider.CallbackDataProvider;
import control.tower.core.queries.CountInventoryItemSummariesQuery;
import control.tower.core.queries.FindInventoryItemSummariesQuery;
import control.tower.core.queryModels.InventoryItemSummary;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

@Component
public class InventoryItemSummaryDataProvider extends CallbackDataProvider<InventoryItemSummary, Void> {

    public InventoryItemSummaryDataProvider(QueryGateway queryGateway) {
        super(
                q -> queryGateway.query(
                        new FindInventoryItemSummariesQuery(q.getOffset(), q.getLimit()),
                        ResponseTypes.multipleInstancesOf(InventoryItemSummary.class)
                ).join().stream(),
                q -> queryGateway.query(new CountInventoryItemSummariesQuery(), Long.class).join().intValue()
        );
    }

}
