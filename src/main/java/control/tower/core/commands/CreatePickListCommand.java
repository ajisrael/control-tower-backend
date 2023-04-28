package control.tower.core.commands;

import org.axonframework.commandhandling.RoutingKey;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CreatePickListCommand {

    @RoutingKey
    private final String pickId;

    private final List<String> skuList;

    private final Date pickDate;

    public CreatePickListCommand(String pickId, List<String> skuList, Date pickDate) {
        this.pickId = pickId;
        this.skuList = skuList;
        this.pickDate = pickDate;
    }

    public String getPickId() {
        return pickId;
    }

    public List<String> getSkuList() {
        return skuList;
    }

    public Date getPickDate() {
        return pickDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePickListCommand that = (CreatePickListCommand) o;
        return Objects.equals(pickId, that.pickId) && Objects.equals(skuList, that.skuList) && Objects.equals(pickDate, that.pickDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId, skuList, pickDate);
    }

    @Override
    public String toString() {
        return "CreatePickListCommand{" +
                "listId='" + pickId + '\'' +
                ", skuList=" + skuList +
                ", pickDate=" + pickDate +
                '}';
    }
}
