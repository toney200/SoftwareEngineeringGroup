import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Order {
    private int orderID;                // this is a unique identifier attached to each individual order
    private java.sql.Date orderDate;             // date the order was created
    private int customerID;             // each order must have a corresponding customer
    private int publicationID;          // each order is only tied to one publication
    private java.sql.Date startAgainDate = null;        // orders can be placed on hold. This date represents the end of that hold
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
    private static MySQLConnector sqlConnector;

    public Order(){
        instantiateSQLInstance();
    }

    public Order(int orderID, java.sql.Date orderDate, int customerID, int publicationID){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.publicationID = publicationID;
        instantiateSQLInstance();
    }

    private void instantiateSQLInstance(){
        if (sqlConnector == null){
            try{
                sqlConnector = new MySQLConnector();
            }
            catch(Exception e){
                System.err.println("Unable to access database.");
            }
        }
    }

    /**
     * Releases this Connection object's database and JDBC resources immediately instead of waiting for
     * them to be automatically released.
     */
    public static void closeSQLConnection(){
        if (sqlConnector!= null){
            sqlConnector.closeDB();
        }
    }


    /**
     * Creates a new record in the MySQL database using data from members of the Order instance passed in as a parameter.
     * @param order an instance of the {@link Order} class passed into the database to be stored as a new record
     */
    public static void createOrderInDB(Order order){
        if(sqlConnector.insertOrderDetails(order)){
            System.out.println("Order created successfully.");
        }
        else if(!sqlConnector.insertOrderDetails(order)){
            System.out.println("Order could not be created.");
        }
    }

    public int getOrderID() {
		return orderID;
	}

	public java.sql.Date getOrderDate() {
		return orderDate;
	}

	public int getCustomerID() {
		return customerID;
	}

	public int getPublicationID() {
		return publicationID;
	}

	public java.sql.Date getStartAgainDate() {
		return startAgainDate;
	}

    /**
     * @param customerID the ID of the customer to which this order is tied
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Sets the Publication ID of this order such that the order can link to a specific item of inventory.
     * @param publicationID the ID of the publication that this order has been created for
     */
    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
    }

    /**
     * @param orderDate date for the order to be delivered
     * @throws Exception if the order is not in the yyyy/MM/dd format
     */
    public void setOrderDate(Date orderDate) throws Exception{
        if(validateOrderDate(orderDate)){
            this.orderDate = orderDate;
        }
        else throw new IllegalArgumentException("Invalid order date");
    }

    public void setStartAgainDate(java.sql.Date startAgainDate) {
        this.startAgainDate = startAgainDate;
    }

    /**
     * @return a Date object from a given String if it is in the correct format. Throws a ParseException when this is not
     * the case
     */
    private boolean validateOrderDate(Date date) throws Exception {
        try {
            if(date instanceof Date) {
            	return true;
            }
            else {
            	return false;
            }
        } catch (Exception pe) {
            return false;
        }
    }
}
