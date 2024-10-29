import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


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

	/*
	Queries the database for customers with a first or last name matching the parameters passed into this method and
	returns them as an ArrayList of Customer objects.
	 */
	public ArrayList<Customer> searchCustomerByName(String firstname, String lastName){
		ArrayList<Customer> customers = new ArrayList<Customer>();

		try{
			preparedStatement = connect.prepareStatement("select * from customers where firstname like ? or lastname like ?");
			preparedStatement.setString(1, "%"+firstname+"%");
			preparedStatement.setString(2, "%"+lastName+"%");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				customers.add(new Customer(resultSet.getInt(0), resultSet.getString(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getInt(6)));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return customers;
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
