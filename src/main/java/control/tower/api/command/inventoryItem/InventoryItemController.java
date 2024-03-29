package control.tower.api.command.inventoryItem;

import control.tower.api.command.inventoryItem.models.DeleteInventoryItemRequestBody;
import control.tower.api.command.inventoryItem.models.InventoryItemRequestBody;
import control.tower.api.command.inventoryItem.models.InventoryItemResponse;
import control.tower.api.command.inventoryItem.models.MoveInventoryItemRequestBody;
import control.tower.config.Constants;
import control.tower.core.commands.CreateInventoryItemCommand;
import control.tower.core.commands.DeleteInventoryItemCommand;
import control.tower.core.commands.MoveInventoryItemCommand;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/inventoryItem")
public class InventoryItemController {

    private final CommandGateway commandGateway;

    public InventoryItemController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<InventoryItemResponse> createInventoryItem(@RequestBody InventoryItemRequestBody inventoryItemRequestBody) {
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
                    new InventoryItemResponse(true, "Inventory item created successfully"));
        } catch (IllegalArgumentException | CommandExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new InventoryItemResponse(false, Constants.ILLEGAL_ARGUMENT_PREFIX + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new InventoryItemResponse(false, Constants.EXCEPTION_PREFIX + e.getMessage()));
        }
    }


    @PutMapping
    public ResponseEntity<InventoryItemResponse> moveInventoryItem(@RequestBody MoveInventoryItemRequestBody moveInventoryItemRequestBody) {
        try {
            commandGateway.sendAndWait(
                    new MoveInventoryItemCommand(
                        moveInventoryItemRequestBody.getSku(),
                        moveInventoryItemRequestBody.getLocationId(),
                        moveInventoryItemRequestBody.getBinId()
                    )
            );
            return ResponseEntity.ok(
                    new InventoryItemResponse(true, "Inventory item moved successfully"));
        } catch (IllegalArgumentException | CommandExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new InventoryItemResponse(false, Constants.ILLEGAL_ARGUMENT_PREFIX + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new InventoryItemResponse(false, Constants.EXCEPTION_PREFIX + e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<InventoryItemResponse> deleteInventoryItem(@RequestBody DeleteInventoryItemRequestBody deleteInventoryItemRequestBody) {
        try {
            commandGateway.sendAndWait(
                    new DeleteInventoryItemCommand(
                            deleteInventoryItemRequestBody.getSku()
                    )
            );
            return ResponseEntity.ok(
                    new InventoryItemResponse(true, "Inventory item deleted successfully"));
        } catch (IllegalArgumentException | CommandExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new InventoryItemResponse(false, Constants.ILLEGAL_ARGUMENT_PREFIX + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new InventoryItemResponse(false, Constants.EXCEPTION_PREFIX + e.getMessage()));
        }
    }
}

