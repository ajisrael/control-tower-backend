package control.tower.core.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.axonframework.commandhandling.RoutingKey;

import java.util.Date;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class CreatePickListCommand {

    @RoutingKey
    @NonNull
    private final String pickId;
    @NonNull
    private final List<String> skuList;
    @NonNull
    private final Date pickDate;

    public CreatePickListCommand(String pickId, List<String> skuList, Date pickDate) {
        this.pickId = pickId;
        this.skuList = skuList;
        this.pickDate = pickDate;
    }
}
