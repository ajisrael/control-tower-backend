package control.tower.core.queries;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class FindInventoryItemSummariesQuery {

    @NonNull
    private final int offset;
    @NonNull
    private final int limit;

    public FindInventoryItemSummariesQuery(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getPageNumber() {
        return offset / limit;
    }
}
