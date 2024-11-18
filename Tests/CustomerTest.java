import  org.junit.jupiter.api.*;
import org.junit.jupiter.api.AssertionsKt;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest extends Customer{
    Customer testCust = new Customer();

    @Test
    /*
     * Customer Test 1
     * Should return false when a name is more than 20
     */
    public void testValidateFirstName001(){
        assertEquals(false, testCust.validateFirstName("fnthrdsfytunmightjkiw"));
    }

    @Test
    /*
     * Customer Test 2
     * Should return false when first name less than 2
     */
    public void testValidateFirsName002(){
        assertEquals(false, testCust.validateFirstName("E"));
    }

    @Test 
    /*
     * Customer Test 3 
     * Should return true when first name is 2 - 20(inclusive) characters and contains no numbers
     */
    public void testValidateFirstName003(){
        assertEquals(true, testCust.validateFirstName("John"));
    }

    @Test
    /*
     * Customer Test 4
     * Should return false when first name contains numbers
     */
    public void testValidateFirstName004(){
        assertEquals(false, testCust.validateFirstName("S4oirse"));
    }

    @Test
    /*
     * Customer Test 5
     * Should return false when last name is greater than 20 characters
     */
    public void testValidateLastName(){
        fail("Test fails");
    }

    @Test
     /*
     * Customer Test 6 
     * Should return false when the last name is shorter than 2 characters 
     */
    public void testValidateLastName002(){
        fail();
    }

    @Test
    /*
     * Customer Test 7
     * Should return true when valid name is entered 
     */
    public void testValidateLastName003(){
        assertTrue(testCust.validateLastName("Murphy"));
    }

    @Test
    /*
     * Customer Test 8
     * Should return false when the name contains a digit.
     */
    public void testValidateLastName004(){
        assertEquals(false, testCust.validateLastName("K3nndedy"));
    }

    /**
     * Customer Test 9
     * Should return true when valid eircodes are entered.
     */
    @Test
    public void testValidateEircode001(){
        assertEquals(false, testCust.validateEircode("N37N6P6"));
    }

    /**
     * Customer Test 10
     * Should return false when passed in strings are shorter than seven characters
     */
    @Test
    public void testValidateEircode000(){
        assertEquals(false, testCust.validateEircode("D014H789"));
        assertEquals(false, testCust.validateEircode("D01HJ1"));
    }

    /**
     * Customer Test 11
     * Should return true when a valid phone number is passed in.
     */
    @Test
    public void testValidatePhoneNo000(){
        assertEquals(true, testCust.validatePhoneNo("0871234567"));
    }

    /**
     * Customer Test 12
     * Phone numbers more, or less, than 10 characters should be rejected.
     */
    @Test
    public void testValidatePhoneNo001(){
        assertFalse(testCust.validatePhoneNo("083123"));
        assertFalse(testCust.validatePhoneNo(("08911111111")));
    }

    /**
     * Customer Test 13
     * Customer phone numbers should be rejected if they contain non-numeric characters.
     */
    @Test
    public void testValidatePhoneNo002(){
        assertFalse(testCust.validatePhoneNo("083A567890"));
    }
}
