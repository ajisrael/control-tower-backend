package control.tower.aggregates;

import control.tower.core.commands.CreatePickListCommand;
import control.tower.core.events.PickListCreatedEvent;
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
        fixture = new AggregateTestFixture<>(PickList.class);
    }

    @AfterEach
    void cleanup() {
        SKU_LIST = new ArrayList<>();
    }

    @Test
    void shouldCreatePickList() {
        // TODO: Will need to update once InventoryItem check is in place
        SKU_LIST.add(SKU);
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(PICK_ID, SKU_LIST, DATE))
                .expectEvents(new PickListCreatedEvent(PICK_ID, SKU_LIST, DATE));
    }

    @Test
    void shouldNotCreatePickListWhenPickIdIsNull() {
        SKU_LIST.add(SKU);
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(null, SKU_LIST, DATE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreatePickListWhenPickIdIsEmpty() {
        SKU_LIST.add(SKU);
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand("", SKU_LIST, DATE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreatePickListWhenASkuInListIsNull() {
        SKU_LIST.add(SKU);
        SKU_LIST.add(null);
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(PICK_ID, SKU_LIST, DATE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreatePickListWhenASkuInListIsEmpty() {
        SKU_LIST.add(SKU);
        SKU_LIST.add("");
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(PICK_ID, SKU_LIST, DATE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreatePickListWhenSkuListIsEmpty() {
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(PICK_ID, SKU_LIST, DATE))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    void shouldNotCreatePickListWhenDateIsNull() {
        SKU_LIST.add(SKU);
        fixture.givenNoPriorActivity()
                .when(new CreatePickListCommand(PICK_ID, SKU_LIST, null))
                .expectException(IllegalArgumentException.class);
    }
}
