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
	
	//PUBLICATION NAME CHECK
	
	/*
	 * Test #1
	 * Obj: Verify valid publication name (2 - 50)inclusive
	 * Inputs: Irish Independent
	 * Expected outputs: true
	 * 
	 */
	
	@Test
	void testValidatePublicationID001() {
		assertEquals(true, testObj.validatePublicationName("Irish Independent"));
	}
	
	/*
	 * Test #2
	 * Obj:Verify publication name character less than min rejected
	 * Inputs: f
	 * Expected outputs: false
	 * 
	 */
	
	@Test 
	void testValidatePublicationID002() {
		assertEquals(false, testObj.validatePublicationName("f"));
	}
	
	/*
	 * Test #3
	 * Obj: Verify publication name character greater than max rejected
	 * is out of the valid range
	 * Inputs: check if this is greater than fifty characters for testing purposes
	 * Expected outputs: false
	 * 
	 */
	
	@Test 
	void testValidatePublicationID003() {
		assertEquals(false, testObj.validatePublicationName("check if this is greater than fifty characters for testing purposes" ));
	}
	
	/*
	 * Test #4
	 * Obj:Verify publication name not entered rejected
	 * 
	 * Inputs: null
	 * Expected outputs: false
	 * 
	 */
	
	@Test 
	void testValidatePublicationID004() {
		assertEquals(false, testObj.validatePublicationName());
	}
	
	/*
	 * Test #5
	 * Obj:Verify publication name accepts no numbers 
	 * Inputs: 123
	 * Expected outputs: false
	 * 
	 */
	
	@Test 
	void testValidatePublicationID005() {
		assertEquals(false, testObj.validatePublicationName("hell0 Th3re"));
	}
	
	//PUBLICATION NAME CHECK
	
	
	
	
	
	
}
