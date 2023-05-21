package control.tower.core.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@EqualsAndHashCode
@ToString
public class DeletePickListCommand {

    @TargetAggregateIdentifier
    @NonNull
    private final String pickId;

    public DeletePickListCommand(String pickId) {
        this.pickId = pickId;
    }
}
