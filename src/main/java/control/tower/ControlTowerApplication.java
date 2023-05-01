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
    //  need to adjust aggregates as follows:
    //    - have picklist aggregate only keep track of high level state, may still be able to use sku list
    //    - each inventory item aggregate will have a pick list id to map it to for the projection
    //    - on create pick list command, issue a add to pick list command for each item,
    //      and throw errors for any items that don't exist before issuing the pick list created event
    //          - make sure pick list created event contains list of skus
    //    - for the pick list summary projection, on pick list created event loop through skus to get
    //      required data from inventory item summary projection
    //    - issue add to and remove from pick list commands in pick list aggregate
    //    - handle add to and remove from pick list events in inventory item

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

    // TODO: Tie in events to Kafka

    // TODO: Evaluate Axon Server vs RDS like PostgreSQL

    // TODO: Convert storage type from XML to JSON

    // TODO: Attach codescene and/or sonarcube to this app for code analysis

    // TODO: Add logging to rest endpoints for all transactions

    // TODO: Add request id's to rest endpoints and cache them for idempotency
}
