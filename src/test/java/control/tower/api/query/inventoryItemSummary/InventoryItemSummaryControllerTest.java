package control.tower.api.query.inventoryItemSummary;

import control.tower.core.queries.FindInventoryItemSummariesQuery;
import control.tower.core.queryModels.InventoryItemSummary;
import control.tower.core.valueObjects.Location;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class InventoryItemSummaryControllerTest {
    private InventoryItemSummaryController controller;

    @Mock
    private QueryGateway queryGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new InventoryItemSummaryController(queryGateway);
    }

    @Test
    void getInventoryItemSummaries_ReturnsInventoryItemSummaries() {
        // Arrange
        int offset = 0;
        int limit = 100;
        List<InventoryItemSummary> expectedSummaries = new ArrayList<>();
        expectedSummaries.add(new InventoryItemSummary("sku1", "item1", new Location("location1", "bin1"), 10.99));
        when(queryGateway.query(any(FindInventoryItemSummariesQuery.class), any(ResponseTypes.multipleInstancesOf(InventoryItemSummary.class).getClass())))
                .thenReturn(completedFuture(expectedSummaries));

        // Act
        List<InventoryItemSummary> actualSummaries = controller.getInventoryItemSummaries(offset, limit);

        // Assert
        assertEquals(expectedSummaries, actualSummaries);
    }

    @Test
    void getInventoryItemSummaries_ReturnsEmptyList() {
        // Arrange
        int offset = 0;
        int limit = 100;
        List<InventoryItemSummary> expectedSummaries = new ArrayList<>();
        when(queryGateway.query(any(FindInventoryItemSummariesQuery.class), any(ResponseTypes.multipleInstancesOf(InventoryItemSummary.class).getClass())))
                .thenReturn(completedFuture(expectedSummaries));

        // Act
        List<InventoryItemSummary> actualSummaries = controller.getInventoryItemSummaries(offset, limit);

        // Assert
        assertEquals(expectedSummaries, actualSummaries);
    }
}
