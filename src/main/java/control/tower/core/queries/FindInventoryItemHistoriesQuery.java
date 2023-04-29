package control.tower.core.queries;

import java.util.Objects;

public class FindInventoryItemHistoriesQuery {

    private final int offset;

    private final int limit;

    public FindInventoryItemHistoriesQuery(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindInventoryItemHistoriesQuery that = (FindInventoryItemHistoriesQuery) o;
        return offset == that.offset && limit == that.limit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(offset, limit);
    }

    @Override
    public String toString() {
        return "FindInventoryItemHistoriesQuery{" +
                "offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
