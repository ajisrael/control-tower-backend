package control.tower;

import control.tower.core.CreateInventoryItemCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControlTowerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControlTowerApplication.class, args);
    }
}
