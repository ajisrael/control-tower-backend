package control.tower.api.command.pickList.models;

import java.util.Objects;

public class UpdatePickListRequestBody {

    private String pickId;

    private String sku;

    public UpdatePickListRequestBody(String pickId, String sku) {
        this.pickId = pickId;
        this.sku = sku;
    }

    public String getPickId() {
        return pickId;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdatePickListRequestBody that = (UpdatePickListRequestBody) o;
        return Objects.equals(pickId, that.pickId) && Objects.equals(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId, sku);
    }

    @Override
    public String toString() {
        return "PickInventoryItemRequestBody{" +
                "pickId='" + pickId + '\'' +
                ", sku='" + sku + '\'' +
                '}';
    }

}