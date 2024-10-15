Create Table deliveryAreas(
    DeliveryAreaID Integer(2) Primary key Not Null,
    name Varchar(50) Not Null);

Create Table customers(
    customerID Integer(8) Primary key Not Null,
    firstName Varchar(20) Not Null,
    lastName Varchar(20) Not Null,
    phoneNumber Integer(10) Not Null,
    address Varchar(100) Not Null,
    eircode Varchar(7) Not Null,
    deliveryAreaID Integer(2) Not Null,
    Constraint fkCustomerDeliveryArea
    Foreign Key(deliveryAreaID)
    References deliveryAreas(deliveryAreaID)
    On Delete Cascade
    On Update Cascade);

Create Table publications(
    publicationID Integer(4) Primary key Not Null,
    title Varchar(50) Not Null,
    author Varchar(50),
    type Varchar(9) Not Null,
    frequency Varchar(7),
    cost Decimal(2, 2) Not Null);

Create Table orders(
    orderID Integer(8) Primary Key Not Null,
    orderDate Date Not Null,
    startAgainDate Date,
    customerID Integer(8) Not Null,
    publicationID Integer(4) Not Null,
    Constraint fkOrdersPublicationsCustomers
    Foreign Key(customerID)
    References customers(customerID)
    On Delete Cascade
    On Update Cascade,
    Foreign Key(publicationID)
    References publications(publicationID)
    On Delete Cascade
    On Update Cascade);