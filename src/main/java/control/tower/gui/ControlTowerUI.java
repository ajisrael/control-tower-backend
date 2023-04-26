package control.tower.gui;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import control.tower.core.commands.CreateInventoryItemCommand;
import control.tower.core.queryModels.InventoryItemSummary;
import control.tower.core.commands.MoveInventoryItemCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;

@SpringUI
@Push
public class ControlTowerUI extends UI {

    private final CommandGateway commandGateway;

    private final InventoryItemSummaryDataProvider inventoryItemSummaryDataProvider;

    public ControlTowerUI(CommandGateway commandGateway, InventoryItemSummaryDataProvider inventoryItemSummaryDataProvider) {
        this.commandGateway = commandGateway;
        this.inventoryItemSummaryDataProvider = inventoryItemSummaryDataProvider;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        HorizontalLayout commandBar = new HorizontalLayout(
                createInventoryItemPanel(), moveInventoryItemPanel()
        );
        commandBar.setSizeFull();

        VerticalLayout layout = new VerticalLayout(commandBar, summaryGrid());
        layout.setSizeFull();

        getUI().setErrorHandler(event -> {
            Throwable cause = event.getThrowable();
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            Notification.show("Error", cause.getMessage(), Notification.Type.ERROR_MESSAGE);
        });
        setContent(layout);
    }

    private Panel createInventoryItemPanel() {
        TextField sku = new TextField("Inventory Item SKU");
        TextField locationId = new TextField("Location ID");
        TextField binId = new TextField("Bin ID");
        TextField name = new TextField("Inventory Item Name");
        TextField price = new TextField("Price");


        Button submit = new Button("Submit", evt -> {
            commandGateway.sendAndWait(
                    new CreateInventoryItemCommand(
                            sku.getValue(),
                            locationId.getValue(),
                            binId.getValue(),
                            name.getValue(),
                            Double.parseDouble(price.getValue()))
            );
            Notification.show("Success", Notification.Type.HUMANIZED_MESSAGE);
            inventoryItemSummaryDataProvider.refreshAll();
        });

        FormLayout form = new FormLayout(sku, locationId, binId, name, price, submit);
        form.setMargin(true);

        return new Panel("Create Inventory Item", form);
    }

    private Panel moveInventoryItemPanel() {
        TextField sku = new TextField("Inventory Item SKU");
        TextField locationId = new TextField("New Location ID");
        TextField binId = new TextField("New Bin ID");


        Button submit = new Button("Submit", evt -> {
            commandGateway.sendAndWait(
                    new MoveInventoryItemCommand(
                            sku.getValue(),
                            locationId.getValue(),
                            binId.getValue()
                    )
            );
            Notification.show("Success", Notification.Type.HUMANIZED_MESSAGE);
            inventoryItemSummaryDataProvider.refreshAll();
        });

        FormLayout form = new FormLayout(sku, locationId, binId, submit);
        form.setMargin(true);

        return new Panel("Move Inventory Item", form);
    }

    private VerticalLayout summaryGrid() {
        Grid<InventoryItemSummary> grid = new Grid<>();
        grid.addColumn(InventoryItemSummary::getSku).setCaption("SKU");
        grid.addColumn(InventoryItemSummary::getName).setCaption("Name");
        grid.addColumn(InventoryItemSummary::getPrice).setCaption("Price");
        grid.addColumn(item ->
                item.getCurrentLocation().getLocationId() + "_" + item.getCurrentLocation().getBinId()
        ).setCaption("Current Location");
        grid.setSizeFull();
        grid.setDataProvider(inventoryItemSummaryDataProvider);
        return new VerticalLayout(grid, new Button("Refresh", e -> inventoryItemSummaryDataProvider.refreshAll()));
    }
}
