package control.tower.api.command.pickList;

import control.tower.api.command.pickList.models.UpdatePickListRequestBody;
import control.tower.api.command.pickList.models.PickListRequestBody;
import control.tower.api.command.pickList.models.PickListResponse;
import control.tower.config.Constants;
import control.tower.core.commands.AddInventoryItemToPickListCommand;
import control.tower.core.commands.CreatePickListCommand;
import control.tower.core.commands.PickInventoryItemCommand;
import control.tower.core.commands.RemoveInventoryItemFromPickListCommand;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(path = "api/v1/pickList")
public class PickListController {

    private final CommandGateway commandGateway;

    public PickListController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<PickListResponse> createPickList(@RequestBody PickListRequestBody pickListRequestBody) {
        try {
            commandGateway.sendAndWait(
                    new CreatePickListCommand(
                            pickListRequestBody.getPickId(),
                            pickListRequestBody.getSkuList(),
                            buildDateFromString(pickListRequestBody.getPickDate())
                    )
            );
            return ResponseEntity.ok(
                    new PickListResponse(true, "Pick list created successfully"));
        } catch (IllegalArgumentException | CommandExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new PickListResponse(false, Constants.ILLEGAL_ARGUMENT_PREFIX + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new PickListResponse(false, Constants.EXCEPTION_PREFIX + e.getMessage()));
        }
    }

    @PutMapping(path = "/pick")
    public ResponseEntity<PickListResponse> pickInventoryItem(@RequestBody UpdatePickListRequestBody updatePickListRequestBody) {
        try {
            commandGateway.sendAndWait(
                    new PickInventoryItemCommand(
                            updatePickListRequestBody.getPickId(),
                            updatePickListRequestBody.getSku()
                    )
            );
            return ResponseEntity.ok(
                    new PickListResponse(true, "Inventory item picked successfully"));
        } catch (IllegalArgumentException | CommandExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new PickListResponse(false, Constants.ILLEGAL_ARGUMENT_PREFIX + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new PickListResponse(false, Constants.EXCEPTION_PREFIX + e.getMessage()));
        }
    }

    // TODO: Add endpoints and functionality to add and remove an item(s) from a pick list

    @PutMapping(path = "/add")
    public ResponseEntity<PickListResponse> addInventoryItemToPickList(@RequestBody UpdatePickListRequestBody updatePickListRequestBody) {
        try {
            commandGateway.sendAndWait(
                    new AddInventoryItemToPickListCommand(
                            updatePickListRequestBody.getPickId(),
                            updatePickListRequestBody.getSku()
                    )
            );
            return ResponseEntity.ok(
                    new PickListResponse(true, "Inventory item added to pick list successfully"));
        } catch (IllegalArgumentException | CommandExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new PickListResponse(false, Constants.ILLEGAL_ARGUMENT_PREFIX + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new PickListResponse(false, Constants.EXCEPTION_PREFIX + e.getMessage()));
        }
    }

    @PutMapping(path = "/remove")
    public ResponseEntity<PickListResponse> removeInventoryItemFromPickList(@RequestBody UpdatePickListRequestBody updatePickListRequestBody) {
        try {
            commandGateway.sendAndWait(
                    new RemoveInventoryItemFromPickListCommand(
                            updatePickListRequestBody.getPickId(),
                            updatePickListRequestBody.getSku()
                    )
            );
            return ResponseEntity.ok(
                    new PickListResponse(true, "Inventory item removed from pick list successfully"));
        } catch (IllegalArgumentException | CommandExecutionException e) {
            return ResponseEntity.badRequest().body(
                    new PickListResponse(false, Constants.ILLEGAL_ARGUMENT_PREFIX + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new PickListResponse(false, Constants.EXCEPTION_PREFIX + e.getMessage()));
        }
    }

    private Date buildDateFromString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
