/**
 * 
 */
package Publications;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Publications.Publications.InvalidPublicationNameException;

/**
 * 
 */
class PublicationTest {

	
	Publications testObj = new Publications();
	
	//PUBLICATION NAME TESTS
	
	/*
	 * Test #1
	 * Obj: Verify valid publication name (2 - 50)inclusive
	 * Inputs: Irish Independent
	 * Expected outputs: true
	 * 
	 */
	
	@Test
	void testValidatePublicationName001(){
		assertEquals(true, testObj.validatePublicationName("Irish Independent"));//17 characters
		assertEquals(true, testObj.validatePublicationName("E!"));//2 characters
		assertEquals(true, testObj.validatePublicationName("The 5 Love Languages: The Secret to Love That Last"));//50 characters
	}
	
	/*
	 * Test #2
	 * Obj:Verify publication name character less than min rejected
	 * Inputs: f
	 * Expected outputs: false
	 * 
	 */
	
	@Test 
	void testValidatePublicationName002() {
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
	void testValidatePublicationName003(){
		assertEquals(false, testObj.validatePublicationName("check if this is greater than fifty characters for testing purposes" ));
	}
	
	/*
	 * Test #4
	 * Obj:Verify publication name not entered rejected
	 * Inputs: null
	 * Expected outputs: false
	 * 
	 */
	
	@Test 
	void testValidatePublicationName004(){
		assertEquals(false, testObj.validatePublicationName(null));
	}
	
	
	//PUBLICATION COST TESTS
	
	/*
	 * Test # 6
	 *Obj:  Verify valid cost(€1.00 - €75.00 inclusive) 
	 *Inputs: 45.00
	 *Expected output: True
	 */
	@Test 
	void testValidatePublicationCost001() {
		assertEquals(true, testObj.validatePublicationCost(45.00));
	}
	
	/*
	 * Test # 7
	 *Obj: Verify negative numbers are rejected
	 *Inputs: -10.00
	 *Expected output: False
	 */
	@Test 
	void testValidatePublicationCost002() {
		assertEquals(false, testObj.validatePublicationCost(-10.00));
	}
	
	/*
	 * Test # 8
	 *Obj:Verify number greater than max are rejected
	 *Inputs: 100.00
	 *Expected output: False
	 */
	@Test 
	void testValidatePublicationCost003() {
		assertEquals(false, testObj.validatePublicationCost(100.00));
	}
	
	/*
	 * Test # 9
	 *Obj:Verify number below min are rejected
	 *Inputs: 0.50
	 *Expected output: False
	 */
	@Test 
	void testValidatePublicationCost004() {
		assertEquals(false, testObj.validatePublicationCost(0.50));
	}
	
	/*
	 * Test # 10
	 *Obj:Verify number left empty is rejected
	 *Inputs: 0.00
	 *Expected output: False
	 */
	@Test 
	void testValidatePublicationCost005() {
		assertEquals(false, testObj.validatePublicationCost(0.00));
	}
	
	//PUBLICATION TYPE TESTS
	
	/*
	 * Test # 11
	 *Obj:Verify valid type of publication entered 
	 *Inputs: Magazine/Newspaper/Book
	 *Expected output: True
	 */
	@Test 
	void testValidatePublicationType001() {
		assertEquals(true, testObj.validatePublicationType("Newspaper"));
		assertEquals(true, testObj.validatePublicationType("Magazine"));
		assertEquals(true, testObj.validatePublicationType("Book"));
		assertEquals(true, testObj.validatePublicationType("Once"));
	}
	
	/*
	 * Test # 12
	 *Obj:Verify invalid type is rejected
	 *Inputs: Journal/Blog/Article
	 *Expected output: False
	 */
	@Test 
	void testValidatePublicationType002() {
		assertEquals(false, testObj.validatePublicationType("Journal"));
		assertEquals(false, testObj.validatePublicationType("Blog"));
		assertEquals(false, testObj.validatePublicationType("Article"));
	}
	
	/*
	 * Test # 13
	 *Obj:Verify invalid type is rejected
	 *Inputs: null
	 *Expected output: False
	 */
	@Test 
	void testValidatePublicationType003() {
		assertEquals(false, testObj.validatePublicationType(null));
	}
	
	/*
	 * Test # 14
	 *Obj:Verify valid author (2 - 50 characters inclusive)
	 *Inputs: J.K Rowling/JK/Celebrated Author Inspires Generations with Words
	 *Expected output: True
	 */
	
	//PUBLICATION AUTHOR TESTS
	@Test 
	void testValidatePublicationAuthor001() {
		assertEquals(true, testObj.validatePublicationAuthor("J.K Rowling")); // 11 characters
		assertEquals(true, testObj.validatePublicationAuthor("JK"));// 2 characters 
		assertEquals(true, testObj.validatePublicationAuthor("Celebrated Author Inspires Generations with Words"));//50 characters
	}
	
	/*
	 * Test # 15
	 *Obj:Verify author characters less than min is rejected
	 *Inputs: t
	 *Expected output: False
	 */
	@Test 
	void testValidatePublicationAuthor002() {
		assertEquals(false, testObj.validatePublicationAuthor("t")); //less than 2 characters
	}
	
	/*
	 * Test # 16
	 *Obj:Verify author characters greater than max is rejected 
	 *Inputs: check if this is greater than fifty characters for testing purposes
	 *Expected output: False
	 */
	@Test 
	void testValidatePublicationAuthor003() {
		assertEquals(false, testObj.validatePublicationAuthor("check if this is greater than fifty characters for testing purposes")); // over 50 characters
		
	}
	
	/*
	 * Test # 17
	 *Obj:Verify author not entered is rejected 
	 *Inputs: null
	 *Expected output: False
	 */
	@Test 
	void testValidatePublicationAuthor004() {
		assertEquals(false, testObj.validatePublicationAuthor(null));//check empty
		
	}
	
	/*
	 * Test # 18
	 *Obj:Verify author accepts no numbers  
	 *Inputs: hell0 th3re
	 *Expected output: False
	 */
	@Test 
	void testValidatePublicationAuthor005() {
		assertEquals(false, testObj.validatePublicationAuthor("hell0 th3re"));
		
		
	}
	
	//PUBLICATION FREQUENCY TESTS
	
	/*
	 * Test # 19
	 * Obj: Verify valid frequency of publication entered 
	 * Inputs: Daily/Weekly/Monthly
	 * Expected output: True
	 */
	@Test
	void testValidatePublicationFrequency001() {
		assertEquals(true, testObj.validatePublicationFrequency("Weekly"));
		assertEquals(true, testObj.validatePublicationFrequency("Daily"));
		assertEquals(true, testObj.validatePublicationFrequency("Monthly"));
	}
	
	/*
	 * Test # 20
	 * Obj: Verify invalid frequency displays error message
	 * Inputs: Today
	 * Expected output: False
	 */
	@Test
	void testValidatePublicationFrequency002() {
		assertEquals(false, testObj.validatePublicationFrequency("Today"));
	}
	
	/*
	 * Test # 21
	 * Obj: Verify empty frequency displays error message
	 * Inputs: null
	 * Expected output: False
	 */
	@Test
	void testValidatePublicationFrequency003() {
		assertEquals(false, testObj.validatePublicationFrequency(null));
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
}
