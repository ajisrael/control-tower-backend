package control.tower.core.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class DeletePickListCommand {

    @TargetAggregateIdentifier
    private final String pickId;

    public DeletePickListCommand(String pickId) {
        this.pickId = pickId;
    }

    public String getPickId() {
        return pickId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletePickListCommand that = (DeletePickListCommand) o;
        return Objects.equals(pickId, that.pickId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId);
    }

    @Override
    public String toString() {
        return "DeletePickListCommand{" +
                "pickId='" + pickId + '\'' +
                '}';
    }

}
