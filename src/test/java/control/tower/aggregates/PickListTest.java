package control.tower.aggregates;

import control.tower.core.commands.CreatePickListCommand;
import control.tower.core.commands.PickInventoryItemCommand;
import control.tower.core.events.InventoryItemAddedToPickListEvent;
import control.tower.core.events.InventoryItemPickedEvent;
import control.tower.core.events.PickListCreatedEvent;
import org.axonframework.modelling.command.IdentifierMissingException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PickListTest {

    private static final String PICK_ID = "pick_1";

    private static final String SKU = "1234";

    private static final Date DATE = new Date();

    private static List<String> SKU_LIST = new ArrayList<>();

    private FixtureConfiguration<PickList> fixture;

    @BeforeEach
    void setup() {
        SKU_LIST.add(SKU);
        fixture = new AggregateTestFixture<>(PickList.class);
    }

    @AfterEach
    void cleanup() {
        SKU_LIST = new ArrayList<>();
    }

    @Test
    void shouldCreatePickListAndAddInventoryItemToPickList() {
        // TODO: Will need to update once InventoryItem check is in place
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(PICK_ID, SKU_LIST, DATE))
                .expectEvents(
                        new PickListCreatedEvent(PICK_ID, SKU_LIST, DATE),
                        new InventoryItemAddedToPickListEvent(PICK_ID, SKU)
                );
    }

    @Test
    void shouldNotCreatePickListWhenPickIdIsNull() {
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(null, SKU_LIST, DATE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreatePickListWhenPickIdIsEmpty() {
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand("", SKU_LIST, DATE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreatePickListWhenASkuInListIsNull() {
        SKU_LIST.add(null);
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(PICK_ID, SKU_LIST, DATE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreatePickListWhenASkuInListIsEmpty() {
        SKU_LIST.add("");
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(PICK_ID, SKU_LIST, DATE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreatePickListWhenSkuListIsEmpty() {
        SKU_LIST = new ArrayList<>();
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(PICK_ID, SKU_LIST, DATE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreatePickListWhenDateIsNull() {
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(PICK_ID, SKU_LIST, null))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldPickInventoryItem() {
        fixture.given(new PickListCreatedEvent(PICK_ID, SKU_LIST, DATE))
                .when(new PickInventoryItemCommand(PICK_ID, SKU))
                .expectEvents(new InventoryItemPickedEvent(PICK_ID, SKU));
    }

    @Test
    void shouldNotPickInventoryItemWhenPickIdIsNull() {
        fixture.given(new PickListCreatedEvent(PICK_ID, SKU_LIST, DATE))
                .when(new PickInventoryItemCommand(null, SKU))
                .expectException(IdentifierMissingException.class);
    }

    @Test
    void shouldNotPickInventoryItemWhenSkuIsNull() {
        fixture.given(new PickListCreatedEvent(PICK_ID, SKU_LIST, DATE))
                .when(new PickInventoryItemCommand(PICK_ID, null))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotPickInventoryItemWhenSkuIsEmpty() {
        fixture.given(new PickListCreatedEvent(PICK_ID, SKU_LIST, DATE))
                .when(new PickInventoryItemCommand(PICK_ID, ""))
                .expectException(IllegalArgumentException.class);
    }
}
