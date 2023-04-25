package control.tower.api.command.inventoryItem;

import com.vaadin.ui.MenuBar;
import control.tower.aggregates.InventoryItem;
import control.tower.core.CreateInventoryItemCommand;
import control.tower.core.valueObjects.Location;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

