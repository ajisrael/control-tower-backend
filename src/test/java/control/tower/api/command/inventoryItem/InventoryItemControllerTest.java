package control.tower.api.command.inventoryItem;
import control.tower.api.command.inventoryItem.models.InventoryItemRequestBody;
import control.tower.api.command.inventoryItem.models.InventoryItemResponse;
import control.tower.api.command.inventoryItem.models.MoveInventoryItemRequestBody;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InventoryItemControllerTest {

    @Test
    void createInventoryItem_ValidArguments_ReturnsOkResponse() {
        // Arrange
        InventoryItemRequestBody requestBody = new InventoryItemRequestBody("sku", "locationId", "binId", "name", 9.99);
        CommandGateway commandGateway = mock(CommandGateway.class);
        when(commandGateway.sendAndWait(any())).thenReturn(null);
        InventoryItemController controller = new InventoryItemController(commandGateway);

        // Act
        ResponseEntity<InventoryItemResponse> response = controller.createInventoryItem(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().getResponse().get("success"));
        assertEquals("Inventory item created successfully", response.getBody().getResponse().get("message"));
    }

    @Test
    void createInventoryItem_InvalidArguments_ReturnsBadRequestResponse() {
        // Arrange
        InventoryItemRequestBody requestBody = new InventoryItemRequestBody(null, "locationId", "binId", "name", 9.99);
        CommandGateway commandGateway = mock(CommandGateway.class);
        when(commandGateway.sendAndWait(any())).thenThrow(new IllegalArgumentException("Invalid argument"));
        InventoryItemController controller = new InventoryItemController(commandGateway);

        // Act
        ResponseEntity<InventoryItemResponse> response = controller.createInventoryItem(requestBody);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(false, response.getBody().getResponse().get("success"));
        assertEquals("Invalid argument: Invalid argument", response.getBody().getResponse().get("message"));
    }

    @Test
    void createInventoryItem_CommandExecutionException_ReturnsBadRequestResponse() {
        // Arrange
        InventoryItemRequestBody requestBody = new InventoryItemRequestBody("sku", "locationId", "binId", "name", 9.99);
        CommandGateway commandGateway = mock(CommandGateway.class);
        when(commandGateway.sendAndWait(any())).thenThrow(new CommandExecutionException("Command execution failed", new IllegalArgumentException("Invalid argument")));
        InventoryItemController controller = new InventoryItemController(commandGateway);

        // Act
        ResponseEntity<InventoryItemResponse> response = controller.createInventoryItem(requestBody);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(false, response.getBody().getResponse().get("success"));
        assertEquals("Invalid argument: Command execution failed", response.getBody().getResponse().get("message"));
    }

    @Test
    void createInventoryItem_UnhandledException_ReturnsInternalServerErrorResponse() {
        // Arrange
        InventoryItemRequestBody requestBody = new InventoryItemRequestBody("sku", "locationId", "binId", "name", 9.99);
        CommandGateway commandGateway = mock(CommandGateway.class);
        when(commandGateway.sendAndWait(any())).thenThrow(new RuntimeException("Unhandled exception"));
        InventoryItemController controller = new InventoryItemController(commandGateway);

        // Act
        ResponseEntity<InventoryItemResponse> response = controller.createInventoryItem(requestBody);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(false, response.getBody().getResponse().get("success"));
        assertEquals("An error occurred: Unhandled exception", response.getBody().getResponse().get("message"));
    }

    @Test
    void moveInventoryItem_ValidArguments_ReturnsOkResponse() {
        // Arrange
        MoveInventoryItemRequestBody requestBody = new MoveInventoryItemRequestBody("sku", "locationId", "binId");
        CommandGateway commandGateway = mock(CommandGateway.class);
        when(commandGateway.sendAndWait(any())).thenReturn(null);
        InventoryItemController controller = new InventoryItemController(commandGateway);

        // Act
        ResponseEntity<InventoryItemResponse> response = controller.moveInventoryItem(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().getResponse().get("success"));
        assertEquals("Inventory item moved successfully", response.getBody().getResponse().get("message"));
    }

    @Test
    void moveInventoryItem_InvalidArguments_ReturnsBadRequestResponse() {
        // Arrange
        MoveInventoryItemRequestBody requestBody = new MoveInventoryItemRequestBody(null, "locationId", "binId");
        CommandGateway commandGateway = mock(CommandGateway.class);
        when(commandGateway.sendAndWait(any())).thenThrow(new IllegalArgumentException("Invalid argument"));
        InventoryItemController controller = new InventoryItemController(commandGateway);

        // Act
        ResponseEntity<InventoryItemResponse> response = controller.moveInventoryItem(requestBody);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(false, response.getBody().getResponse().get("success"));
        assertEquals("Invalid argument: Invalid argument", response.getBody().getResponse().get("message"));
    }

    @Test
    void moveInventoryItem_CommandExecutionException_ReturnsBadRequestResponse() {
        // Arrange
        MoveInventoryItemRequestBody requestBody = new MoveInventoryItemRequestBody("sku", "locationId", "binId");
        CommandGateway commandGateway = mock(CommandGateway.class);
        when(commandGateway.sendAndWait(any())).thenThrow(new CommandExecutionException("Command execution failed", new IllegalArgumentException("Invalid argument")));
        InventoryItemController controller = new InventoryItemController(commandGateway);

        // Act
        ResponseEntity<InventoryItemResponse> response = controller.moveInventoryItem(requestBody);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(false, response.getBody().getResponse().get("success"));
        assertEquals("Invalid argument: Command execution failed", response.getBody().getResponse().get("message"));
    }

    @Test
    void moveInventoryItem_UnhandledException_ReturnsInternalServerErrorResponse() {
        // Arrange
        MoveInventoryItemRequestBody requestBody = new MoveInventoryItemRequestBody("sku", "locationId", "binId");
        CommandGateway commandGateway = mock(CommandGateway.class);
        when(commandGateway.sendAndWait(any())).thenThrow(new RuntimeException("Unhandled exception"));
        InventoryItemController controller = new InventoryItemController(commandGateway);

        // Act
        ResponseEntity<InventoryItemResponse> response = controller.moveInventoryItem(requestBody);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(false, response.getBody().getResponse().get("success"));
        assertEquals("An error occurred: Unhandled exception", response.getBody().getResponse().get("message"));
    }
}
