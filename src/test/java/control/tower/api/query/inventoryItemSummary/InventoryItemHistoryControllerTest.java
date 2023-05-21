package control.tower.api.query.inventoryItemSummary;

import control.tower.api.query.inventoryItemHistory.InventoryItemHistoryController;
import control.tower.core.queries.FindInventoryItemHistoriesQuery;
import control.tower.core.queryModels.InventoryItemHistory;
import control.tower.core.valueObjects.Location;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class InventoryItemHistoryControllerTest {
    private InventoryItemHistoryController controller;

    @Mock
    private QueryGateway queryGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new InventoryItemHistoryController(queryGateway);
    }

    @Test
    void getInventoryItemHistories_ReturnsInventoryItemHistories() {
        // Arrange
        int offset = 0;
        int limit = 100;
        List<InventoryItemHistory> expectedHistories = new ArrayList<>();
        expectedHistories.add(new InventoryItemHistory("sku1", new Location("location1", "bin1"), Instant.now()));
        when(queryGateway.query(any(FindInventoryItemHistoriesQuery.class), any(ResponseTypes.multipleInstancesOf(InventoryItemHistory.class).getClass())))
                .thenReturn(completedFuture(expectedHistories));

        // Act
        List<InventoryItemHistory> actualHistories = controller.getInventoryItemHistories(offset, limit);

        // Assert
        assertEquals(expectedHistories, actualHistories);
    }

    @Test
    void getInventoryItemHistories_ReturnsEmptyList() {
        // Arrange
        int offset = 0;
        int limit = 100;
        List<InventoryItemHistory> expectedHistories = new ArrayList<>();
        when(queryGateway.query(any(FindInventoryItemHistoriesQuery.class), any(ResponseTypes.multipleInstancesOf(InventoryItemHistory.class).getClass())))
                .thenReturn(completedFuture(expectedHistories));

        // Act
        List<InventoryItemHistory> actualHistories = controller.getInventoryItemHistories(offset, limit);

        // Assert
        assertEquals(expectedHistories, actualHistories);
    }
}
