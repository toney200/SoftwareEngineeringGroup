package DeliveryArea.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeliveryAreaTest {

    DeliveryArea testObj = new DeliveryArea();

    //Test 1
    // Obj: Verify Valid Delivery Area Name (2 - 50 characters)
    // inputs: Hickleberry
    // Expected output: True

    @Test
    void testValidateDeliveryAreaName001(){
        assertEquals(true, testObj.validateDeliveryAreaName("Hickleberry")); //11 characters
        assertEquals(true, testObj.validateDeliveryAreaName("NK")); // 2 characters
    }


    // Test 2
    // Obj: Verify Delivery Area Name less than min rejected (2 - 50 characters)
    // inputs: E
    // Expected output: False

    @Test
    void testValidateDeliveryAreaName002() {
        assertEquals(false, testObj.validateDeliveryAreaName("E")); //1 character
    }

    // Test 3
    // Obj: Verify Delivery Area Name greater than max rejected (2 - 50 characters)
    // inputs: The Enchanted Valley of Serene Meadows and Whispering Pines
    // Expected output: False

    @Test
    void testValidateDeliveryAreaName003() {
        assertEquals(false, testObj.validateDeliveryAreaName("The Enchanted Valley of Serene Meadows and Whispering Pines")); //59 characters
    }

    // Test 4
    // Obj: Verify delivery area name not entered rejected
    // inputs: null
    // Expected outputs: false

    @Test 
	void testValidateDeliveryAreaName004(){
		assertEquals(false, testObj.validateDeliveryAreaName(null)); // 0 characters
	}

    // Test 5
    // Obj: Verify delivery area name with numerical characters entered rejected
    // inputs: 8387398
    // Expected outputs: false

    @Test 
	void testValidateDeliveryAreaName005(){
		assertEquals(false, testObj.validateDeliveryAreaName("8387398")); // 7 digits 
	}

}