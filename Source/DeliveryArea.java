import java.util.ArrayList;

public class DeliveryArea {

    // Class Variables
    private int deliveryAreaID;
    private String deliveryAreaName;

    // Static SQL Connector
    private static MySQLConnector sqlConnector;

    // Constructors
    public DeliveryArea() {
        if (sqlConnector == null) {
            instantiateSQLInstance();
        }
    }

    public DeliveryArea(int deliveryAreaID, String deliveryAreaName) {
        if (sqlConnector == null) {
            instantiateSQLInstance();
        }
        this.deliveryAreaID = deliveryAreaID;
        this.deliveryAreaName = deliveryAreaName;
    }

    // Getters and Setters
    public int getDeliveryAreaID() {
        return this.deliveryAreaID;
    }

    public int setDeliveryAreaID() {
        return this.deliveryAreaID;
    }

    public String getDeliveryAreaName() {
        return this.deliveryAreaName;
    }

    public void setDeliveryAreaName(String deliveryAreaName) {
        if (validateDeliveryAreaName(deliveryAreaName)) {
            this.deliveryAreaName = deliveryAreaName;
        }
    }

    // Validation method for delivery area name
    public boolean validateDeliveryAreaName(String deliveryAreaName) {
        return deliveryAreaName != null && deliveryAreaName.length() >= 2 && deliveryAreaName.length() <= 50;
    }

    // Initialize the SQL connector as a singleton instance
    private static void instantiateSQLInstance() {
        try {
            sqlConnector = new MySQLConnector();
        } catch (Exception e) {
            System.err.println("Error occurred linking application to database. Ref DeliveryArea.instantiateSQLInstance() method.");
        }
    }

    // Method to create a new DeliveryArea in the database
    public static boolean createDeliveryAreaInDB(DeliveryArea da) {
        return sqlConnector.insertDeliveryAreaDetails(da);
    }

    // Read method to retrieve a DeliveryArea by ID from the database
    public static DeliveryArea readDeliveryAreaFromDB(int id) {
        if (sqlConnector == null) {
            instantiateSQLInstance();
        }
        return sqlConnector.searchDeliveryAreaByID(id); 
    }

    // Update method to modify an existing DeliveryArea in the database
    public static boolean updateDeliveryAreaInDB(DeliveryArea updatedDeliveryArea) {
        if (sqlConnector == null) {
            instantiateSQLInstance();
        }
        return sqlConnector.updateDeliveryAreaDetails(updatedDeliveryArea); 
    }

    // Method to retrieve all DeliveryAreas from the database
    public static ArrayList<DeliveryArea> getAllDeliveryAreasFromDB() {
        if (sqlConnector == null) {
            instantiateSQLInstance();
        }
        return sqlConnector.searchDeliveryAreas();
    }

        public static boolean deleteDeliveryAreaByID(int id) {
        if (sqlConnector == null) {
            instantiateSQLInstance(); // Ensure SQL connection is active
        }


        // delete method
        DeliveryArea deliveryArea = sqlConnector.searchDeliveryAreaByID(id);
        if (deliveryArea != null) {
            boolean isDeleted = sqlConnector.deleteDeliveryArea(deliveryArea); 
            if (isDeleted) {
                System.out.println("DeliveryArea with ID " + id + " was successfully deleted.");
                return true;
            } else {
                System.err.println("Failed to delete DeliveryArea with ID " + id + "."); 
                return false;
            }
        } else {
            System.err.println("DeliveryArea with ID " + id + " not found."); 
            return false;
        }
    }

    @Override
    public String toString() {
        return "DeliveryArea{" +
                "deliveryAreaID=" + deliveryAreaID +
                ", deliveryAreaName='" + deliveryAreaName + '\'' +
                '}';
    }
}
