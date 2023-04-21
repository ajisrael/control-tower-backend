package control.tower.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.EntityId;

import java.util.Date;
import java.util.Objects;

public class Location {

    @EntityId
    private final String locationKey;
    
    private final String locationId;
    
    private final String binId;

    private final Date startTime;
    private Date endTime;

    public Location(String locationId, String binId) {
        this.locationKey = locationId + "_" + binId;
        this.locationId = locationId;
        this.binId = binId;
        this.startTime = new Date();
        this.endTime = null;
    }

    public String getLocationKey() {
        return locationKey;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getBinId() {
        return binId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(locationKey, location.locationKey) && Objects.equals(locationId, location.locationId) && Objects.equals(binId, location.binId) && Objects.equals(startTime, location.startTime) && Objects.equals(endTime, location.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationKey, locationId, binId, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationKey='" + locationKey + '\'' +
                ", locationId='" + locationId + '\'' +
                ", binId='" + binId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
