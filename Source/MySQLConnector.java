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

	public void closeDB() {
		try {
			connect.close();
		} catch(Exception e) {
			System.out.println("Failed to close connection: " + e.getMessage());
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
	public Customer searchCustomerByID(int customerID) {
		Customer foundCustomer = null;

		try{
			preparedStatement = connect.prepareStatement("Select * From customers Where customerID = ?");
			preparedStatement.setInt(1, customerID);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				foundCustomer = new Customer(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getInt(7));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return foundCustomer;
	}
	
	/**
	 * @todo Enable search for all customers and establish full functionality in Customer and CLI classes
	 * @return List of customers
	 */
	public ArrayList<Customer> searchCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();

		try{
			preparedStatement = connect.prepareStatement("select * from customers");
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
	 * @todo Enable search for all delivery areas and establish full functionality in DeliveryArea and CLI classes
	 * @return List of delivery areas
	 */
	public ArrayList<DeliveryArea> searchDeliveryAreas() {
		ArrayList<DeliveryArea> deliveryAreas = new ArrayList<DeliveryArea>();

		try{
			preparedStatement = connect.prepareStatement("select * from deliveryAreas");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				deliveryAreas.add(new DeliveryArea(resultSet.getInt(1), resultSet.getString(2)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return deliveryAreas;
	}
	
	public DeliveryArea searchDeliveryAreaByID(int deliveryAreaID) {
		DeliveryArea foundDeliveryArea = null;

		try{
			preparedStatement = connect.prepareStatement("Select * From deliveryAreas Where deliveryAreaID = ?");
			preparedStatement.setInt(1, deliveryAreaID);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				foundDeliveryArea = new DeliveryArea(resultSet.getInt(1), resultSet.getString(2));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return foundDeliveryArea;
	}
	
	/**
	 * @todo Enable search for all publications and establish full functionality in Publication and CLI classes
	 * @return List of publications
	 */
	public ArrayList<Publication> searchPublications() {
		ArrayList<Publication> publications = new ArrayList<Publication>();

		try{
			preparedStatement = connect.prepareStatement("select * from publications");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				publications.add(new Publication(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
						resultSet.getDouble(6)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return publications;
	}
	
	public Publication searchPublicationID(int publicationID) {
		Publication foundPublication = null;

		try{
			preparedStatement = connect.prepareStatement("Select * From publications Where publicationID = ?");
			preparedStatement.setInt(1, publicationID);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				foundPublication = new Publication(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
						resultSet.getDouble(6));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return foundPublication;
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
			preparedStatement = connect.prepareStatement("Update customers Set firstName = ?, lastName = ?, " +
					"phoneNumber = ?, address = ?, eircode = ?, deliveryAreaID = ? Where customerID = ?");
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
	
	public boolean updatePublicationDetails(Publication p) {
		try{
			preparedStatement = connect.prepareStatement("Update publications Set title = ?, author = ?, " +
					"type = ?, frequency = ?, cost = ? Where publicationID = ?");
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
	
	public boolean updateDeliveryAreaDetails(DeliveryArea da) {
		try{
			preparedStatement = connect.prepareStatement("Update deliveryAreas Set name = ? Where DeliveryAreaID = ?");
			preparedStatement.setString(1, da.getDeliveryAreaName());
			preparedStatement.setInt(2, da.getDeliveryAreaID());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateOrderDetails(Order o) {
		try{
			preparedStatement = connect.prepareStatement("Update orders Set orderDate = ?, startAgainDate = ? Where orderID = ?");
			preparedStatement.setObject(1, o.getOrderDate());
			preparedStatement.setInt(2, o.getOrderID());
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
