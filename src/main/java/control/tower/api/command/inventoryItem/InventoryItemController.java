package control.tower.api.command.inventoryItem;

import control.tower.api.command.inventoryItem.models.InventoryItemRequestBody;
import control.tower.api.command.inventoryItem.models.InventoryItemResponse;
import control.tower.api.command.inventoryItem.models.MoveInventoryItemRequestBody;
import control.tower.core.commands.CreateInventoryItemCommand;
import control.tower.core.commands.MoveInventoryItemCommand;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="api/v1/inventoryItem")
public class InventoryItemController {

    private final CommandGateway commandGateway;

    public InventoryItemController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createInventoryItem(@RequestBody InventoryItemRequestBody inventoryItemRequestBody) {
        try {
            commandGateway.sendAndWait(
                    new CreateInventoryItemCommand(
                            inventoryItemRequestBody.getSku(),
                            inventoryItemRequestBody.getLocationId(),
                            inventoryItemRequestBody.getBinId(),
                            inventoryItemRequestBody.getName(),
                            inventoryItemRequestBody.getPrice()
                    )
            );
            return ResponseEntity.ok(
                    new InventoryItemResponse(true, "Inventory item created successfully")
                            .getResponse());
        } catch (IllegalArgumentException | CommandExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new InventoryItemResponse(false, "Invalid argument: " + e.getMessage())
                            .getResponse());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new InventoryItemResponse(false, "An error occurred: " + e.getMessage())
                            .getResponse());
        }
    }


    @PutMapping
    public void moveInventoryItem(@RequestBody MoveInventoryItemRequestBody moveInventoryItemRequestBody) {
        // TODO: add try catch for proper error handling and notification to client upon error receipt
        commandGateway.sendAndWait(
                new MoveInventoryItemCommand(
                    moveInventoryItemRequestBody.getSku(),
                    moveInventoryItemRequestBody.getLocationId(),
                    moveInventoryItemRequestBody.getBinId()
                )
        );
    }
}

