package control.tower.core.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@EqualsAndHashCode
@ToString
public class RemoveInventoryItemFromPickListCommand {

    @TargetAggregateIdentifier
    @NonNull
    private final String pickId;
    @NonNull
    private final String sku;

    public RemoveInventoryItemFromPickListCommand(String pickId, String sku) {
        this.pickId = pickId;
        this.sku = sku;
    }
}
