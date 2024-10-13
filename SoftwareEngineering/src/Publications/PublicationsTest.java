/**
 * 
 */
package Publications;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class PublicationsTest {

	Publications testObj = new Publications();
	
	/*
	 * Test #1
	 * Obj: Verify valid publication ID (4 digits)
	 * Inputs: 1234
	 * Expected outputs: true
	 * 
	 */
	
	@Test
	void testValidatePublicationID001() {
		assertEquals(true, testObj.validatePublicationID( 1234));
	}
	
	/*
	 * Test #2
	 * Obj:Verify negative publications ID entered displays error message
	 * Inputs: -1536
	 * Expected outputs: false
	 * 
	 */
	
	@Test 
	void testValidatePublicationID002() {
		assertEquals(false, testObj.validatePublicationID(-1536));
	}
	
	/*
	 * Test #3
	 * Obj:Verify that an error message is displayed when the publication ID 
	 * is out of the valid range
	 * Inputs: 12345
	 * Expected outputs: false
	 * 
	 */
	
	@Test 
	void testValidatePublicationID003() {
		assertEquals(false, testObj.validatePublicationID(12345));
	}
	
	/*
	 * Test #4
	 * Obj:Verify that an error message is displayed when the publication ID 
	 * is out of the valid range
	 * Inputs: 23
	 * Expected outputs: false
	 * 
	 */
	
	@Test 
	void testValidatePublicationID004() {
		assertEquals(false, testObj.validatePublicationID(23));
	}
	
	/*
	 * Test #5
	 * Obj:Verify publication ID not entered displays error message
	 * Inputs: null
	 * Expected outputs: false
	 * 
	 */
	
	@Test 
	void testValidatePublicationID005() {
		assertEquals(false, testObj.validatePublicationID(null));
	}
	
	//PUBLICATION NAME CHECK
	
	
	
	
	
	
}
