package control.tower.core.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@EqualsAndHashCode
@ToString
public class DeleteInventoryItemCommand {

    @TargetAggregateIdentifier
    @NonNull
    private final String sku;

    public DeleteInventoryItemCommand(String sku) {
        this.sku = sku;
    }
}
