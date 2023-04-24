package control.tower.core;

import control.tower.core.valueObjects.Location;
import org.axonframework.commandhandling.RoutingKey;

import java.util.Objects;

public class CreateInventoryItemCommand {

    @RoutingKey
    private final String sku;
    private final Location location;
    private final String name;
    private final double price;

    public CreateInventoryItemCommand(String sku, String locationId, String binId, String name, double price) {
        this.sku = sku;
        this.location = new Location(locationId, binId);
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateInventoryItemCommand that = (CreateInventoryItemCommand) o;
        return price == that.price &&
                Objects.equals(sku, that.sku) &&
                Objects.equals(name, that.name) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, location, name, price);
    }

    @Override
    public String toString() {
        return "CreateFurnitureItemCommand{" +
                "sku='" + sku + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
