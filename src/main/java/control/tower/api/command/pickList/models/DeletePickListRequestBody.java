package control.tower.api.command.pickList.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DeletePickListRequestBody {
    private String pickId;

    public DeletePickListRequestBody(@JsonProperty("pickId") String pickId) {
        this.pickId = pickId;
    }


    public String getPickId() {
        return pickId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletePickListRequestBody that = (DeletePickListRequestBody) o;
        return Objects.equals(pickId, that.pickId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickId);
    }

    @Override
    public String toString() {
        return "DeletePickListRequestBody{" +
                "pickId='" + pickId + '\'' +
                '}';
    }

}
