import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import  org.junit.jupiter.api.*;
import org.junit.jupiter.api.AssertionsKt;

public class CustomerTest extends Customer{
    Customer testCust = new Customer();
    
    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 1
     * Should return false when a name is more than 20
     */
    public void testValidateFirstName001(){
        assertEquals(false, testCust.validateFirstName("fnthrdsfytunmightjkiw"));
    }

    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 2
     * Should return false when first name less than 2
     */
    public void testValidateFirsName002(){
        assertEquals(false, testCust.validateFirstName("E"));
    }

    @SuppressWarnings("deprecation")
    @Test 
    /*
     * Customer Test 3 
     * Should return true when first name is 2 - 20(inclusive) characters and contains no numbers
     */
    public void testValidateFirstName003(){
        assertEquals(true, testCust.validateFirstName("John"));
    }

    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 4
     * Should return false when first name contains numbers
     */
    public void testValidateFirstName004(){
        assertEquals(false, testCust.validateFirstName("S4oirse"));
    }

    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 5
     * Should return false when last name is greater than 20 characters
     */
    public void testValidateLastName(){
        fail("Test fails");
    }

    @SuppressWarnings("deprecation")
    @Test
     /*
     * Customer Test 6 
     * Should return false when the last name is shorter than 2 characters 
     */
    public void testValidateLastName002(){
        fail();
    }

    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 7
     * Should return true when valid name is entered 
     */
    public void testValidateLastName003(){
        assertTrue(testCust.validateLastName("Murphy"));
    }

    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 8
     * Should return false when the name contains a digit.
     */
    public void testValidateLastName004(){
        assertEquals(false, testCust.validateLastName("K3nndedy"));
    }

    @Test
    /*
     * Customer Test 9 
     * Should return true when valid eircodes are entered.
     */
    public void testValidateEircode001(){
        assertEquals(false, testCust.validateEircode("N37N6P6"));
    }

    @Test
    /*
     * Customer Test 10
     * Should return false when passed in strings are shorter than seven characters
     */
    public void testValidateEircode(){
        assertEquals(false, testCust.validateEircode("D01HJ1"));
    }

    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 13
     * Should return true when a valid phone number is passed in.e
     */
    public void testValidatePhoneNo001(){
        assertEquals(true, testCust.validatePhoneNo("0873925974"));
    }


}
