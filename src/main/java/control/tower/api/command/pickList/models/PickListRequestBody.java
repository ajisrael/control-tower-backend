package control.tower.api.command.pickList.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PickListRequestBody {

    private String pickId;

    private List<String> skuList;

    private String pickDate;

    public PickListRequestBody(String pickId, ArrayList<String> skuList, String pickDate) {
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

    public String getPickDate() {
        return pickDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickListRequestBody that = (PickListRequestBody) o;
        return Objects.equals(pickId, that.pickId) && Objects.equals(skuList, that.skuList) && Objects.equals(pickDate, that.pickDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId, skuList, pickDate);
    }

    @Override
    public String toString() {
        return "PickListRequestBody{" +
                "pickId='" + pickId + '\'' +
                ", skuList=" + skuList +
                ", pickDate='" + pickDate + '\'' +
                '}';
    }
}
