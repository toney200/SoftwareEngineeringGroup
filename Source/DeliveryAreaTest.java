package DeliveryArea.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DeliveryAreaTest {

    DeliveryArea testObj = new DeliveryArea();

    // Validation Tests

    // Test 1: Verify Valid Delivery Area Name (2 - 50 characters)
    @Test
    void testValidateDeliveryAreaName001() {
        assertTrue(testObj.validateDeliveryAreaName("Hickleberry")); // 11 characters
        assertTrue(testObj.validateDeliveryAreaName("NK")); // 2 characters
    }

    // Test 2: Verify Delivery Area Name less than min rejected (2 - 50 characters)
    @Test
    void testValidateDeliveryAreaName002() {
        assertFalse(testObj.validateDeliveryAreaName("E")); // 1 character
    }

    // Test 3: Verify Delivery Area Name greater than max rejected (2 - 50 characters)
    @Test
    void testValidateDeliveryAreaName003() {
        assertFalse(testObj.validateDeliveryAreaName("The Enchanted Valley of Serene Meadows and Whispering Pines")); // 59 characters
    }

    // Test 4: Verify delivery area name not entered rejected
    @Test
    void testValidateDeliveryAreaName004() {
        assertFalse(testObj.validateDeliveryAreaName(null)); // Null value
    }

    // Test 5: Verify delivery area name with numerical characters entered rejected
    @Test
    void testValidateDeliveryAreaName005() {
        assertFalse(testObj.validateDeliveryAreaName("8387398")); // 7 digits
    }

    // Test for creating and reading a DeliveryArea
    @Test
    void testCreateAndReadDeliveryArea() {
        // Arrange
        DeliveryArea areaToCreate = new DeliveryArea(1, "Downtown");

        // Act: Create a new delivery area in the database
        boolean created = DeliveryArea.createDeliveryAreaInDB(areaToCreate);

        // Assert: Ensure creation was successful
        assertTrue(created, "Delivery area should be successfully created in DB");

        // Act: Read the delivery area back from the database
        DeliveryArea readArea = DeliveryArea.readDeliveryAreaFromDB(1);

        // Check that the read operation returns the correct data
        assertNotNull(readArea, "Read delivery area should not be null");
        assertEquals(1, readArea.getDeliveryAreaID());
        assertEquals("Downtown", readArea.getDeliveryAreaName());
    }

    // Test for updating a DeliveryArea
    @Test
    void testUpdateDeliveryArea() {

        DeliveryArea areaToUpdate = new DeliveryArea(1, "Downtown");
        DeliveryArea.createDeliveryAreaInDB(areaToUpdate);

        // Update the delivery area’s name
        areaToUpdate.setDeliveryAreaName("City Center");
        boolean updated = DeliveryArea.updateDeliveryAreaInDB(areaToUpdate);

        // Ensure update was successful
        assertTrue(updated, "Delivery area should be successfully updated in DB");

        // Read the updated delivery area from the database
        DeliveryArea updatedArea = DeliveryArea.readDeliveryAreaFromDB(1);

        // Check that the updated data is reflected in the read operation
        assertNotNull(updatedArea, "Updated delivery area should not be null");
        assertEquals("City Center", updatedArea.getDeliveryAreaName(), "Delivery area name should be updated to 'City Center'");
    }
}
