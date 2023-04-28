package control.tower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControlTowerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControlTowerApplication.class, args);
    }

    // TODO: Create Database seeder

    // TODO: Create Pick List Aggregate and Query Models

    // TODO: Create Order Aggregate and Query Models

    // TODO: Create Customer Aggregate and establish relationships with other entities:
    //       - View all inventory items for a customer
    //       - View all orders for a customer

    // TODO: Create authentication endpoint for api with JWT

    // TODO: Setup users and roles for access levels to endpoints

    // TODO: Add additional properties to Inventory Items:
    //       - boolean received
    //       - boolean delivered
    //       - Customer object?

    // TODO: Create administration endpoint to view statistics of system

    // TODO: Begin work on Front End
}
