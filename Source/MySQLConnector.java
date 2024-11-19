import java.sql.*;
import java.util.ArrayList;


public class MySQLConnector {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	final private String host ="localhost:3306";
	final private String user = "root";
	final private String password = "root";
	
	
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

	/**
	 * Closes the connection to the database
	 */
	public void closeDB() {
		try {
			connect.close();
		} catch(Exception e) {
			System.out.println("Failed to close connection: " + e.getMessage());
		}
	}

	/**
	 * Inserts a new customer in the database
	 * @param c - valid customer to be inserted
	 * @return true or false depending on whether the insertion was successful
	 */
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
	 * @param customerID the unique identifier for Customer entries in the database
	 * @return a {@link Customer} object populated with data from the SQL database
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
	
	/**
	 * Searches for a specific delivery area by ID in the database
	 * @param deliveryAreaID - delivery area ID to search
	 * @return a DeliveryArea object with data retrieved from the database
	 */
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
	
	/**
	 * Searches for a specific publication by ID in the database
	 * @param publicationID - publication ID to search
	 * @return a Publication object with data retrieved from the database
	 */
	public Publication searchPublicationByID(int publicationID) {
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
	
    public ArrayList<Publication> searchPublicationByName(String title){
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

	/**
	 * @param firstname - the first name of the customer to be searched within the Customers table
	 * @param lastName - the last name of the customer to be searched within the Customers table
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
	 * Updates an existing customer in the database
	 * @param c - the Customer object to be updated
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
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Updates an existing delivery area in the database
	 * @param da - the DeliveryArea object with info to update the existing delivery area in the database
	 * @return the true/false depending on success of the update attempt in the database
	 */
	public boolean updateDeliveryAreaDetails(DeliveryArea da) {
		try{
			preparedStatement = connect.prepareStatement("Update deliveryAreas Set name = ? Where DeliveryAreaID = ?");
			preparedStatement.setString(1, da.getDeliveryAreaName());
			preparedStatement.setInt(2, da.getDeliveryAreaID());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Inserts a new delivery area in the database
	 * @param da - valid delivery area to be inserted
	 * @return true or false depending on whether the insertion was successful
	 */
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
	
	/**
	 * Inserts a new publication in the database
	 * @param p - valid publication to be inserted
	 * @return true or false depending on whether the insertion was successful
	 */
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

	/**
	 * Updates an existing publication in the database
	 * @param p - the Publication object with info to update the existing publication in the database
	 * @return the true/false depending on success of the update attempt in the database
	 */
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
        } catch (Exception e) {
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
    
    /**
     * Deletes an existing customer in the database
     * @param c - the customer to be deleted
     * @return true or false depending on if the customer was successfully deleted
     */
    public boolean deleteCustomer(Customer c) {
        try{
            preparedStatement = connect.prepareStatement("Delete From customers Where customerID = ?");
            preparedStatement.setInt(1, c.getID());
            int result = preparedStatement.executeUpdate();
            if(result > 0) {
            	return true;
            } else {
            	return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Deletes an existing delivery area in the database
     * @param da - the delivery area to be deleted
     * @return true or false depending on if the delivery area was successfully deleted
     */
    public boolean deleteDeliveryArea(DeliveryArea da) {
        try{
            preparedStatement = connect.prepareStatement("Delete From deliveryAreas Where deliveryAreaID = ?");
            preparedStatement.setInt(1, da.getDeliveryAreaID());
            int result = preparedStatement.executeUpdate();
            if(result > 0) {
            	return true;
            } else {
            	return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Deletes an existing publication in the database
     * @param p - the publication to be deleted
     * @return true or false depending on if the publication was successfully deleted
     */
    public boolean deletePublication(Publication p) {
        try{
            preparedStatement = connect.prepareStatement("Delete From publications Where publicationID = ?");
            preparedStatement.setInt(1, p.getPubID());
            int result = preparedStatement.executeUpdate();
            if(result > 0) {
            	return true;
            } else {
            	return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Deletes an existing order in the database
     * @param o - the order to be deleted
     * @return true or false depending on if the order was successfully deleted
     */
    public boolean deleteOrder(Order o) {
    	return false;
    }

	
	public boolean insertOrderDetails(Order p) {

		boolean insertSucessfull = true;

		try {

			//Create prepared statement to issue SQL query to the database
			preparedStatement = connect.prepareStatement("insert into orders values (default, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, p.getOrderID());
			preparedStatement.setDate(2, p.getOrderDate());
			preparedStatement.setDate(3, p.getStartAgainDate());
			preparedStatement.setInt(4, p.getCustomerID());
			preparedStatement.setInt(5, p.getPublicationID());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			insertSucessfull = false;
		}

		return insertSucessfull;
	}

	// public static void main(String[] args) {
	// 	try {
	// 		MySQLConnector connector = new MySQLConnector();
	// 	} catch(Exception e) {
	// 		e.printStackTrace();
	// 	}
		
	// }

}
