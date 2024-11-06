import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private int orderID;                // this is a unique identifier attached to each individual order
    private Date orderDate;             // date the order was created
    private int customerID;             // each order must have a corresponding customer
    private int publicationID;          // each order is only tied to one publication
    private Date startAgainDate;        // orders can be placed on hold. This date represents the end of that hold
    public int getOrderID() {
		return orderID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public int getCustomerID() {
		return customerID;
	}

	public int getPublicationID() {
		return publicationID;
	}

	public Date getStartAgainDate() {
		return startAgainDate;
	}

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

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
    public void setOrderDate(String orderDate) throws Exception{
        if(validateOrderDate(orderDate)){
            this.orderDate = dateFormat.parse(orderDate);
        }
        else throw new IllegalArgumentException("Invalid order date");
    }

    public void setStartAgainDate(Date startAgainDate) {
        this.startAgainDate = startAgainDate;
    }

    /**
     * @return a Date object from a given String if it is in the correct format. Throws a ParseException when this is not
     * the case
     */
    private boolean validateOrderDate(String date) throws ParseException {
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
