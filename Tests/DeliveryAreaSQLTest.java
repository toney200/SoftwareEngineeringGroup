import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeliveryAreaSQLAccessTest {

    private MySQLConnector dbLink;

    @BeforeEach
    public void setUp() throws Exception {
        dbLink = new MySQLConnector();

        // clean up the DeliveryArea table before each test
        dbLink.executeSQL("DELETE FROM DeliveryArea");
    }

    @Test
    public void testInsertDeliveryAreaDetails() throws Exception {
        DeliveryArea deliveryArea = new DeliveryArea("Test Area");

        boolean result = dbLink.insertDeliveryAreaDetails(deliveryArea);

        assertTrue(result, "Delivery area should be successfully inserted into the database");

        // verify the record exists in the database
        DeliveryArea retrievedArea = dbLink.searchDeliveryAreaByName("Test Area");
        assertNotNull(retrievedArea, "Inserted delivery area should be found in the database");
        assertEquals("Test Area", retrievedArea.getDeliveryAreaName(), "Delivery area name should match");
    }

    @Test
    public void testUpdateDeliveryAreaName() throws Exception {
        // insert a delivery area
        DeliveryArea deliveryArea = new DeliveryArea("Old Name");
        dbLink.insertDeliveryAreaDetails(deliveryArea);

        // update the delivery area's name
        boolean result = dbLink.updateDeliveryAreaName(1, "New Name");
        
        assertTrue(result, "Delivery area name should be successfully updated");

        // verify the updated name
        DeliveryArea updatedArea = dbLink.searchDeliveryAreaByID(1);
        assertNotNull(updatedArea, "Updated delivery area should exist in the database");
        assertEquals("New Name", updatedArea.getDeliveryAreaName(), "Delivery area name should be updated");
    }

    @Test
    public void testDeleteDeliveryAreaByID() throws Exception {
        // insert a delivery area
        DeliveryArea deliveryArea = new DeliveryArea("Delete Me");
        dbLink.insertDeliveryAreaDetails(deliveryArea);

        // delete the delivery area by its ID
        boolean result = dbLink.deleteDeliveryAreaByID(1);

        assertTrue(result, "Delivery area should be successfully deleted");

        // verify the record no longer exists
        DeliveryArea deletedArea = dbLink.searchDeliveryAreaByID(1);
        assertNull(deletedArea, "Deleted delivery area should not exist in the database");
    }
}
