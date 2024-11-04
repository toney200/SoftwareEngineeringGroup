import java.sql.*;
import java.util.ArrayList;


public class MySQLConnector {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	final private String host ="localhost:3306";
	final private String user = "root";
	final private String password = "password";
	
	
	public MySQLConnector() throws Exception {
		
		try {
			
			//Load MySQL Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://" + host + "/newsagent?useTimezone=true&serverTimezone=UTC";
			//Setup the connection with the DB
			connect = DriverManager.getConnection(url, user, password);
		}
		catch (Exception e) {
			throw e;
		}
		
		
	}	

	public boolean insertCustomerDetails(Customer c) {
	
		boolean insertSucessfull = true;
	
		try {
		
			//Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into customers values (default, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, c.getFirstName());
			preparedStatement.setString(2, c.getLastName());
			preparedStatement.setString(3, c.getPhoneNo());
			preparedStatement.setString(4, c.getAddress());
			preparedStatement.setString(5, c.getEircode());
			preparedStatement.setInt(6, c.getDeliveryAreaId());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			insertSucessfull = false;
		}
	
		return insertSucessfull;
	}

	/**
	 * @todo Enable search by ID and establish full functionality in Customer and CLI classes
	 * @param customerID
	 * @return
	 */
	public ArrayList<Customer> searchCustomerByID(int customerID) {
		return null;
	}

	/**
	 * @param firstname the first name of the customer to be searched within the Customers table
	 * @param lastName the last name of the customer to be searched within the Customers table
	 * @return an ArrayList of Customer objects where the first and/or last name match those of the parameters
	 */
	public ArrayList<Customer> searchCustomerByName(String firstname, String lastName){
		ArrayList<Customer> customers = new ArrayList<Customer>();

		try{
			preparedStatement = connect.prepareStatement("select * from customers where firstname like ? or lastname like ?");
			preparedStatement.setString(1, "%"+firstname+"%");
			preparedStatement.setString(2, "%"+lastName+"%");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				customers.add(new Customer(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getInt(7)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return customers;
    }

	/**
	 *
	 * @param c the Customer object to be updated
	 * @return the true/false depending on success of the update attempt in the database
	 */
	public boolean updateCustomerDetails(Customer c) {
		try{
			preparedStatement = connect.prepareStatement("update customers set firstName = ?, lastName = ?, " +
					"phoneNumber = ?, address = ?, eircode = ?, deliveryAreaID = ? where customerID = ?");
			preparedStatement.setString(1, c.getFirstName());
			preparedStatement.setString(2, c.getLastName());
			preparedStatement.setString(3, c.getPhoneNo());
			preparedStatement.setString(4, c.getAddress());
			preparedStatement.setString(5, c.getEircode());
			preparedStatement.setInt(6, c.getDeliveryAreaId());
			preparedStatement.setInt(7, c.getID());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertDeliveryAreaDetails(DeliveryArea da) {
		
		boolean insertSucessfull = true;
	
		try {
		
			//Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into deliveryareas values (default, ?)");
			preparedStatement.setString(1, da.getDeliveryAreaName());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			insertSucessfull = false;
		}
	
		return insertSucessfull;
	}
	
	public boolean insertPublicationDetails(Publication p) {
		
		boolean insertSucessfull = true;
	
		try {
		
			//Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into publications values (default, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, p.getPubName());
			preparedStatement.setString(2, p.getPubAuthor());
			preparedStatement.setString(3, p.getPubType());
			preparedStatement.setString(4, p.getPubFrequency());
			preparedStatement.setDouble(5, p.getPubCost());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
			insertSucessfull = false;
		}
	
		return insertSucessfull;
	}

	 public boolean updatePublication(Publication p) {
        try{
            preparedStatement = connect.prepareStatement("update publications set title = ?, author = ?, " +
                    "type = ?, frequency = ?, cost = ? where publicationID = ?");
            preparedStatement.setString(1, p.getPubName());
            preparedStatement.setString(2, p.getPubAuthor());
            preparedStatement.setString(3, p.getPubType());
            preparedStatement.setString(4, p.getPubFrequency());
            preparedStatement.setDouble(5, p.getPubCost());
            preparedStatement.setInt(6, p.getPubID());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Publication> searchPublication(String title){
        ArrayList<Publication> publication = new ArrayList<Publication>();
        boolean searchSuccessfull = true;

        try{
            preparedStatement = connect.prepareStatement("select * from publications where title like ?");
            preparedStatement.setString(1, "%"+title+"%");
            resultSet = preparedStatement.executeQuery();
            // Create a new Publication object using data from the current row of the resultSet
            while(resultSet.next()) {
                publication.add(new Publication(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDouble(6)));
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            searchSuccessfull = false;
        }

        return publication;

    }

	
//	public boolean insertOrderDetails(Order p) {
//		
//		boolean insertSucessfull = true;
//	
//		try {
//		
//			//Create prepared statement to issue SQL query to the database
//			preparedStatement = connect.prepareStatement("insert into orders values (default, ?, ?, ?, ?, ?)");
//			preparedStatement.setInt(1, p.getID());
//			preparedStatement.setDate(2, p.getDate());
//			preparedStatement.setDate(3, p.getStartAgainDate());
//			preparedStatement.setInt(4, p.getCustomerId());
//			preparedStatement.setInt(5, p.getPublicationId());
//			preparedStatement.executeUpdate();
//		}
//		catch (Exception e) {
//			insertSucessfull = false;
//		}
//	
//		return insertSucessfull;
//	}

	// public static void main(String[] args) {
	// 	try {
	// 		MySQLConnector connector = new MySQLConnector();
	// 	} catch(Exception e) {
	// 		e.printStackTrace();
	// 	}
		
	// }

}
