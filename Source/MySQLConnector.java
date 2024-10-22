import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;


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
