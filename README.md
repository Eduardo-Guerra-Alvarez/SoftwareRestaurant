# SOFTWARE RESTAURANT

This is a software created with JAVA and Spring to implement a Software manage of a restaurant.

## Requirements

 ### Module 1: Administration of Tables and Request.
- Graphic view of tables (busy, free, reserve)
- Register of request per table.
- Change table, join tables.
- State of the request: open, cocking, delivered.

### Module 2: Digital Menu and Take order.
- Crud of order.
- Assign ingredients.

### Module 3: Inventory.
- Register of ingredients and quantity.
- Update automatically when you send an order
- Alert when an ingredient is low.

### Module 4: Invoices and payment method. 
- Register payments cash, card or transfer.
- Creation of tickets and invoices.

### Module 5: Employee Management.
- Create waters, chef and admin.
- Create role and permits.
- Check entries and exits.

### Module 6: Report and statistics.
- Daily, weekly and monthly sales.
- Best-sellign dishes.
- Inventory control.

App/web 

Automatically notifications by email or SMS.


## Progress of the application.
### Backend
- Employee
    - [x] List all Employees
    - [x] Show employee by Id
    - [x] Create employee
    - [x] Update employee
    - [x] Remove employee by id
- Menu
  - [x] List all Menu
  - [x] Get Menu by Id
  - [x] Create Menu
  - [x] Update Menu
  - [x] Delete Menu
- Order
  - [x] List Orders
  - [x] Get Order by Id
  - [x] Create Order
    - Assign Employee and Table
  - [x] Delete Order by Id
  - [ ] Update Order
- OrderDetails
  - [x] List All Orders Details
  - [x] Get order detail by Id
  - [x] create order detail
    - Assign Menu to Order details and this to Order
  - [x] Delete Order details
  - [ ] Update OrderDetails to get OrderDetailsDAO
- TableRestaurant
  - [x] List Tables
  - [x] Get Table by Id
  - [x] Create Table
  - [x] Update Table
  - [x] Delete Table
  - [x] Creation of TableDAO
