[ ] Create Database seeder

[ ] Create Pick List Aggregate and Query Models

[ ] Need to adjust aggregates as follows:
    - have picklist aggregate only keep track of high level state, may still be able to use sku list
    - each inventory item aggregate will have a pick list id to map it to for the projection
    - on create pick list command, issue an add to pick list command for each item,
  and throw errors for any items that don't exist before issuing the pick list created event
    - make sure pick list created event contains list of skus
    - for the pick list summary projection, on pick list created event loop through skus to get
  required data from inventory item summary projection
    - issue add to and remove from pick list commands in pick list aggregate
    - handle add to and remove from pick list events in inventory item

[ ] Create Order Aggregate and Query Models

[ ] Create Customer Aggregate and establish relationships with other entities:
    - View all inventory items for a customer
    - View all orders for a customer

[ ] Create authentication endpoint for api with JWT

[ ] Setup users and roles for access levels to endpoints

[ ] Add additional properties to Inventory Items:
    - boolean received
    - boolean delivered
    - Customer object?

[ ] Create administration endpoint to view statistics of system

[ ] Begin work on Front End

[ ] Tie in events to Kafka

[ ] Evaluate Axon Server vs RDS like Postgres

[ ] Convert storage type from XML to JSON

[x] Attach CodeScene and/or Sonarqube to this app for code analysis

[ ] Add logging to rest endpoints for all transactions

[ ] Add request id's to rest endpoints and cache them for idempotency

[ ] Implement the following checks as part of a Saga for creating a pick list:
    - private void throwErrorIfInventoryItemDoesNotExist(String sku) {}
    - private void throwErrorIfInventoryItemAssignedToPickList(String sku) {} 
    - private void throwErrorIfInventoryItemIsAlreadyPicked(String sku) {}