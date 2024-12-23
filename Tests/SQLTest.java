import static org.junit.jupiter.api.Assertions.*;
import  org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

class SQLTest {
	/*
	 * Test #1
	 * Obj: Verify Customer insertion is successful
	 * Inputs: validated customer object
	 * Expected outputs: true
	 * 
	 */
	@Test
	void insertCustomerTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		Customer obj = new Customer();
		obj.setFirstName("Dimitrije");
		obj.setLastName("Sreckovic");
		obj.setAddress("1 Mars, Milky Way");
		obj.setDeliveryAreaId(1);
		obj.setEircode("N37Y5F4");
		obj.setPhoneNo("0896875219");
		assertTrue(dbLink.insertCustomerDetails(obj));
	}

	/*
	 * Test #2
	 * Obj: Verify Customer insertion is unsuccessful
	 * Inputs: null
	 * Expected outputs: true
	 * 
	 */
	@Test
	void insertCustomerFalse() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertFalse(dbLink.insertCustomerDetails(null));
	}
	
	/*
	 * Test #3
	 * Obj: Verify Publication insertion is successful
	 * Inputs: publication object
	 * Expected outputs: true
	 * 
	 */
	@Test
	void insertPublicationTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		Publication obj = new Publication();
		obj.setPubName("TUS Weekly");
		obj.setPubAuthor("George Washington");
		obj.setPubType("Magazine");
		obj.setPubFrequency("Weekly");
		obj.setPubCost(3.46);
		assertTrue(dbLink.insertPublicationDetails(obj));
	}
	
	/*
	 * Test #4
	 * Obj: Verify Publication insertion is unsuccessful
	 * Inputs: null
	 * Expected outputs: true
	 * 
	 */
	@Test
	void insertPublicationFalse() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertFalse(dbLink.insertPublicationDetails(null));
	}
	
	/*
	 * Test #5
	 * Obj: Verify Delivery Area insertion is successful
	 * Inputs: Delivery Area object
	 * Expected outputs: true
	 * 
	 */
	@Test
	void insertDeliveryAreaTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		DeliveryArea obj = new DeliveryArea();
		obj.setDeliveryAreaName("Earth");
		assertTrue(dbLink.insertDeliveryAreaDetails(obj));
	}
	
	/*
	 * Test #6
	 * Obj: Verify Publication insertion is successful
	 * Inputs: null
	 * Expected outputs: true
	 * 
	 */
	@Test
	void insertDeliveryAreaFalse() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertFalse(dbLink.insertDeliveryAreaDetails(null));
	}
	
	/*
	 * Test #7
	 * Obj: Verify Customer is updated
	 * Inputs: valid customer object
	 * Expected outputs: true
	 * 
	 */
	@Test
	void updateCustomerTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		Customer obj = new Customer(1, "Dimitrije", "Sreckovic", "0896875219", "57 Moon, Milky Way", "N37Y5F4", 2);
		assertTrue(dbLink.updateCustomerDetails(obj));
	}
	
	/*
	 * Test #8
	 * Obj: Verify Customer is not updated
	 * Inputs: null
	 * Expected outputs: false
	 * 
	 */
	@Test
	void updateCustomerFail() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertFalse(dbLink.updateCustomerDetails(null));
	}
	
	/*
	 * Test #9
	 * Obj: Verify Delivery Area is updated
	 * Inputs: valid Delivery Area object
	 * Expected outputs: true
	 * 
	 */
	@Test
	void updateDeliveryAreaTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		DeliveryArea obj = new DeliveryArea(1, "Willow Park");

		assertTrue(dbLink.updateDeliveryAreaDetails(obj));
	}
	
	/*
	 * Test #10
	 * Obj: Verify Delivery Area is not updated
	 * Inputs: null
	 * Expected outputs: false
	 * 
	 */
	@Test
	void updateDeliveryAreaFail() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertFalse(dbLink.updateCustomerDetails(null));
	}
	
	/*
	 * Test #11
	 * Obj: Verify Publication is updated
	 * Inputs: valid publication object
	 * Expected outputs: true
	 * 
	 */
	@Test
	void updatePublicationTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		Publication obj = new Publication(1, "TUS Daily", "Magazine", "Abraham Lincoln", "Daily", 2.77);
		assertTrue(dbLink.updatePublication(obj));
	}
	
	/*
	 * Test #12
	 * Obj: Verify Publication is not updated
	 * Inputs: null
	 * Expected outputs: false
	 * 
	 */
	@Test
	void updatePublicationFalse() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertFalse(dbLink.updatePublication(null));
	}
	
	/*
	 * Test #13
	 * Obj: Verify Customer is retrieved by ID
	 * Inputs: 1
	 * Expected outputs: Customer object with ID 1
	 * 
	 */
	@Test
	void searchCustomerByIDTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertTrue(dbLink.searchCustomerByID(3).getID() == 3);
	}
	
	/*
	 * Test #14
	 * Obj: Verify Customer is not retrieved by ID
	 * Inputs: 99
	 * Expected outputs: null
	 * 
	 */
	@Test
	void searchCustomerByIDFalse() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertTrue(dbLink.searchCustomerByID(99) == null);
	}
	
	/*
	 * Test #15
	 * Obj: Verify Delivery Area is retrieved by ID
	 * Inputs: 1
	 * Expected outputs: Delivery Area object with ID 1
	 * 
	 */
	@Test
	void searchDeliveryAreaByIDTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertTrue(dbLink.searchDeliveryAreaByID(1).getDeliveryAreaID() == 1);
	}
	
	/*
	 * Test #16
	 * Obj: Verify Delivery Area is not retrieved by ID
	 * Inputs: 
	 * Expected outputs: null
	 * 
	 */
	@Test
	void searchDeliveryAreaByIDFalse() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertTrue(dbLink.searchDeliveryAreaByID(99) == null);
	}
	
	/*
	 * Test #17
	 * Obj: Verify Publication is retrieved by ID
	 * Inputs: 1
	 * Expected outputs: Publication object with ID 1
	 * 
	 */
	@Test
	void searchPublicationByIDTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertTrue(dbLink.searchPublicationByID(2).getPubID() == 2);
	}
	
	/*
	 * Test #18
	 * Obj: Verify Publication is not retrieved by ID
	 * Inputs: 99
	 * Expected outputs: null
	 * 
	 */
	@Test
	void searchPublicationByIDFalse() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertTrue(dbLink.searchPublicationByID(99) == null);
	}
	
	/*
	 * Test #19
	 * Obj: Verify Publications are all retrieved
	 * Inputs: method called
	 * Expected outputs: ArrayList of Publications of a given size
	 * 
	 */
	@Test
	void searchPublicationsTest() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertTrue(dbLink.searchPublications().size() > 0);
	}
	
	/*
	 * Test #20
	 * Obj: Verify Customers are all retrieved
	 * Inputs: method called
	 * Expected outputs: ArrayList of Customers of a given size
	 * 
	 */
	@Test
	void searchCustomersTest() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertTrue(dbLink.searchCustomers().size() > 0);
	}
	
	/*
	 * Test #21
	 * Obj: Verify Delivery Areas are all retrieved
	 * Inputs: method called
	 * Expected outputs: ArrayList of Delivery Areas of a given size
	 * 
	 */
	@Test
	void searchDeliveryAreasTest() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		assertTrue(dbLink.searchDeliveryAreas().size() > 0);
	}
	
	/*
	 * Test #22
	 * Obj: Verify the customer was successfully deleted
	 * Inputs: Existing customer in the database
	 * Expected outputs: true
	 * 
	 */
	@Test
	void deleteCustomerTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		Customer obj = new Customer(1, "Dimitrije", "Sreckovic", "0896875219", "57 Moon, Milky Way", "N37Y5F4", 2);
		assertTrue(dbLink.deleteCustomer(obj));
	}
	
	/*
	 * Test #23
	 * Obj: Verify that deletion of the customer was unsuccessful
	 * Inputs: Non-existing customer in the database
	 * Expected outputs: false
	 * 
	 */
	@Test
	void deleteCustomerFalse() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		Customer obj = new Customer(58, "Keanu", "Reeves", "0899517532", "569 Mars, Milky Way", "N37T725", 2);
		assertFalse(dbLink.deleteCustomer(obj));
	}
	
	/*
	 * Test #24
	 * Obj: Verify the publication was successfully deleted
	 * Inputs: Existing publication in the database
	 * Expected outputs: true
	 * 
	 */
	@Test
	void deletePublicationTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		Publication obj = new Publication(1, "TUS Daily", "Magazine", "Abraham Lincoln", "Daily", 2.77);
		assertTrue(dbLink.deletePublication(obj));
	}
	
	/*
	 * Test #25
	 * Obj: Verify that deletion of the publication was unsuccessful
	 * Inputs: Non-existing publication in the database
	 * Expected outputs: false
	 * 
	 */
	@Test
	void deletePublicationFalse() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		Publication obj = new Publication(99, "TUS Monthly", "Magazine", "Ericsson", "Monthly", 2.99);
		assertFalse(dbLink.deletePublication(obj));
	}
	
	/*
	 * Test #26
	 * Obj: Verify the delivery area was successfully deleted
	 * Inputs: Existing delivery area in the database
	 * Expected outputs: true
	 * 
	 */
	@Test
	void deleteDeliveryAreaTrue() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		DeliveryArea obj = new DeliveryArea(3, "Earth");
		assertTrue(dbLink.deleteDeliveryArea(obj));
	}
	
	/*
	 * Test #27
	 * Obj: Verify that deletion of the delivery area was unsuccessful
	 * Inputs: Non-existing delivery area in the database
	 * Expected outputs: false
	 * 
	 */
	@Test
	void deleteDeliveryAreaFalse() throws Exception {
		MySQLConnector dbLink = new MySQLConnector();
		DeliveryArea obj = new DeliveryArea(99, "France");
		assertFalse(dbLink.deleteDeliveryArea(obj));
	}
}
