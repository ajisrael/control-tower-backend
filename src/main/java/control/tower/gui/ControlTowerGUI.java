package control.tower.gui;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import control.tower.core.CreateInventoryItemCommand;
import control.tower.core.MoveInventoryItemCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;

@SpringUI
@Push
public class ControlTowerGUI extends UI {

    private final CommandGateway commandGateway;

    public ControlTowerGUI(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        HorizontalLayout commandBar = new HorizontalLayout(
                createInventoryItemPanel(), moveInventoryItemPanel()
        );
        commandBar.setSizeFull();

        VerticalLayout layout = new VerticalLayout(commandBar);
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
        });

        FormLayout form = new FormLayout(sku, locationId, binId, submit);
        form.setMargin(true);

        return new Panel("Move Inventory Item", form);
    }
}
