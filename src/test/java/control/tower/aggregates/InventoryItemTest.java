package control.tower.aggregates;

import control.tower.core.commands.CreateInventoryItemCommand;
import control.tower.core.commands.MoveInventoryItemCommand;
import control.tower.core.events.InventoryItemCreatedEvent;
import control.tower.core.events.InventoryItemMovedEvent;
import control.tower.core.valueObjects.Location;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryItemTest {
    private static final String SKU = "1234";
    private static final String NAME = "chair";
    private static final String LOCATION_ID = "WHS";
    private static final String BIN_ID = "103";
    private static final double PRICE = 100.00;
    private static final Location LOCATION = new Location(LOCATION_ID, BIN_ID);
    private static final String NEW_LOCATION_ID = "FL3";
    private static final String NEW_BIN_ID = "104";

    private FixtureConfiguration<InventoryItem> fixture;

    @BeforeEach
    void setup() {
        fixture = new AggregateTestFixture<>(InventoryItem.class);
    }

    @Test
    void shouldCreateInventoryItem() {
        fixture.givenNoPriorActivity()
                .when(new CreateInventoryItemCommand(SKU, LOCATION_ID, BIN_ID, NAME, PRICE))
                .expectEvents(new InventoryItemCreatedEvent(SKU, NAME, LOCATION, PRICE));
    }

    @Test
    void shouldNotCreateInventoryItemWhenSkuIsNull() {
        fixture.givenNoPriorActivity()
                .when(new CreateInventoryItemCommand(null, LOCATION_ID, BIN_ID, NAME, PRICE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreateInventoryItemWhenSkuIsEmpty() {
        fixture.givenNoPriorActivity()
                .when(new CreateInventoryItemCommand("", LOCATION_ID, BIN_ID, NAME, PRICE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreateInventoryItemWhenLocationIdIsNull() {
        fixture.givenNoPriorActivity()
                .when(new CreateInventoryItemCommand(SKU, null, BIN_ID, NAME, PRICE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreateInventoryItemWhenLocationIdIsEmpty() {
        fixture.givenNoPriorActivity()
                .when(new CreateInventoryItemCommand(SKU, "", BIN_ID, NAME, PRICE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreateInventoryItemWhenBinIdIsNull() {
        fixture.givenNoPriorActivity()
                .when(new CreateInventoryItemCommand(SKU, LOCATION_ID, null, NAME, PRICE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreateInventoryItemWhenBinIdIsEmpty() {
        fixture.givenNoPriorActivity()
                .when(new CreateInventoryItemCommand(SKU, LOCATION_ID, "", NAME, PRICE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreateInventoryItemWhenNameIsNull() {
        fixture.givenNoPriorActivity()
                .when(new CreateInventoryItemCommand(SKU, LOCATION_ID, BIN_ID, null, PRICE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreateInventoryItemWhenNameIsEmpty() {
        fixture.givenNoPriorActivity()
                .when(new CreateInventoryItemCommand(SKU, LOCATION_ID, BIN_ID, "", PRICE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreateInventoryItemWhenPriceIsNegative() {
        fixture.givenNoPriorActivity()
                .when(new CreateInventoryItemCommand(SKU, LOCATION_ID, BIN_ID, NAME, PRICE * -1))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldMoveInventoryItemWhenGivenNewLocationId() {
        fixture.given(new InventoryItemCreatedEvent(SKU, NAME, LOCATION, PRICE))
                .when(new MoveInventoryItemCommand(SKU, NEW_LOCATION_ID, BIN_ID))
                .expectEvents(new InventoryItemMovedEvent(SKU, NAME, new Location(NEW_LOCATION_ID, BIN_ID), PRICE));
    }

    @Test
    void shouldMoveInventoryItemWhenGivenNewBinId() {
        fixture.given(new InventoryItemCreatedEvent(SKU, NAME, LOCATION, PRICE))
                .when(new MoveInventoryItemCommand(SKU, LOCATION_ID, NEW_BIN_ID))
                .expectEvents(new InventoryItemMovedEvent(SKU, NAME, new Location(LOCATION_ID, NEW_BIN_ID), PRICE));
    }

    @Test
    void shouldNotMoveInventoryItemWhenLocationIdIsNull() {
        fixture.given(new InventoryItemCreatedEvent(SKU, NAME, LOCATION, PRICE))
                .when(new MoveInventoryItemCommand(SKU, null, NEW_BIN_ID))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotMoveInventoryItemWhenLocationIdIsEmpty() {
        fixture.given(new InventoryItemCreatedEvent(SKU, NAME, LOCATION, PRICE))
                .when(new MoveInventoryItemCommand(SKU, "", NEW_BIN_ID))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotMoveInventoryItemWhenBinIdIsNull() {
        fixture.given(new InventoryItemCreatedEvent(SKU, NAME, LOCATION, PRICE))
                .when(new MoveInventoryItemCommand(SKU, LOCATION_ID, null))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotMoveInventoryItemWhenBinIdIsEmpty() {
        fixture.given(new InventoryItemCreatedEvent(SKU, NAME, LOCATION, PRICE))
                .when(new MoveInventoryItemCommand(SKU, LOCATION_ID, ""))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotMoveInventoryItemWhenGivenCurrentLocation() {
        fixture.given(new InventoryItemCreatedEvent(SKU, NAME, LOCATION, PRICE))
                .when(new MoveInventoryItemCommand(SKU, LOCATION_ID, BIN_ID))
                .expectException(IllegalArgumentException.class);
    }
}
