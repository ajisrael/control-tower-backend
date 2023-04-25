package control.tower.api.command.inventoryItem;

import control.tower.core.CreateInventoryItemCommand;
import control.tower.core.MoveInventoryItemCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/inventoryItem")
public class InventoryItemController {

    private final CommandGateway commandGateway;

    public InventoryItemController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public void createInventoryItem(@RequestBody InventoryItemRequestBody inventoryItemRequestBody) {
        commandGateway.sendAndWait(
                new CreateInventoryItemCommand(
                        inventoryItemRequestBody.getSku(),
                        inventoryItemRequestBody.getLocationId(),
                        inventoryItemRequestBody.getBinId(),
                        inventoryItemRequestBody.getName(),
                        inventoryItemRequestBody.getPrice()
                )
        );
    }

    @PutMapping
    public void moveInventoryItem(@RequestBody MoveInventoryItemRequestBody moveInventoryItemRequestBody) {
        commandGateway.sendAndWait(
                new MoveInventoryItemCommand(
                    moveInventoryItemRequestBody.getSku(),
                    moveInventoryItemRequestBody.getLocationId(),
                    moveInventoryItemRequestBody.getBinId()
                )
        );
    }
}

