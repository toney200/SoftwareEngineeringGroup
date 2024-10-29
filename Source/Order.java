import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private int orderID;                // this is a unique identifier attached to each individual order
    private Date orderDate;             // this is the date the order to be delivered, not when the order was created
    private int customerID;             // each order must have a corresponding customer
    private int publicationID;          // each order is only tied to one publication
    private Date startAgainDate;        // orders can be placed on hold. This date represents the end of that hold
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
    }

    /**
     * @param orderDate date for the order to be delivered
     * @throws Exception if the order is not in the yyyy/MM/dd format
     */
    public void setOrderDate(String orderDate) throws Exception{
        this.orderDate = validateOrderDate(orderDate);
    }

    /**
     * @return a Date object from a given String if it is in the correct format. Throws a ParseException when this is not
     * the case
     */
    private Date validateOrderDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }


}
