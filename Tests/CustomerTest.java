import  org.junit.jupiter.api.*;
import org.junit.jupiter.api.AssertionsKt;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest extends Customer{
    Customer testCust = new Customer();

    /**
     * Customer Test 1 </p>
     * Should return false when a name is more than 20 characters long
     */
    @Test
    public void testValidateFirstName001(){
        assertEquals(false, testCust.validateFirstName("fnthrdsfytunmightjkiw"));
    }

    /**
     * Customer Test 2</p>
     * Should return false when first name less than 2 characters long
     */
    @Test
    public void testValidateFirsName002(){
        assertEquals(false, testCust.validateFirstName("E"));
    }

    /**
     * Customer Test 3 </p>
     * Should return true when first name is 2 - 20(inclusive) characters and contains no numbers
     */
    @Test
    public void testValidateFirstName003(){
        assertEquals(true, testCust.validateFirstName("John"));
    }


    /**
     * Customer Test 4 </p>
     * Should return false when first name contains numbers
     */
    @Test
    public void testValidateFirstName004(){
        assertEquals(false, testCust.validateFirstName("S4oirse"));
    }

    /**
     * Customer Test 5 </p>
     * Should return false when last name is greater than 20 characters
     */
    @Test
    public void testValidateLastName(){
        assertEquals(false, testCust.validateLastName("hgnbtyghfjtuyvhfkhjyj"));
    }


     /**
     * Customer Test 6 </P>
     * Should return false when the last name is shorter than 2 characters 
     */
    @Test
    public void testValidateLastName002(){
        assertEquals(false, testCust.validateLastName("F"));
        assertFalse(testCust.validateLastName(""));
    }

    /**
     * Customer Test 7</p>
     * Should return true when valid name is entered 
     */
    @Test
    public void testValidateLastName003(){
        assertTrue(testCust.validateLastName("Murphy"));
    }

    /**
     * Customer Test 8</p>
     * Should return false when the name contains a digit.
     */
    @Test
    public void testValidateLastName004(){
        assertEquals(false, testCust.validateLastName("K3nndedy"));
    }

    /**
     * Customer Test 9</p>
     * Should return true when valid eircodes are entered.
     */
    @Test
    public void testValidateEircode001(){
        assertEquals(true, testCust.validateEircode("N37N6P6"));
    }

    /**
     * Customer Test 10</P>
     * Should return false when passed in strings are shorter than seven characters
     */
    @Test
    public void testValidateEircode000(){
        assertEquals(false, testCust.validateEircode("D014H789"));
        assertEquals(false, testCust.validateEircode("D01HJ1"));
    }

    /**
     * Customer Test 11</p>
     * Should return true when a valid phone number is passed in.
     */
    @Test
    public void testValidatePhoneNo000(){
        assertEquals(true, testCust.validatePhoneNo("0871234567"));
    }

    /**
     * Customer Test 12</p>
     * Phone numbers more, or less, than 10 characters should be rejected.
     */
    @Test
    public void testValidatePhoneNo001(){
        assertFalse(testCust.validatePhoneNo("083123"));
        assertFalse(testCust.validatePhoneNo(("08911111111")));
    }

    /**
     * Customer Test 13</p>
     * Customer phone numbers should be rejected if they contain non-numeric characters.
     */
    @Test
    public void testValidatePhoneNo002(){
        assertFalse(testCust.validatePhoneNo("083A567890"));
        assertFalse(testCust.validatePhoneNo("zeroonetwo"));
    }

    /**
     * Customer Test 14 </p>
     * Customer address should be rejected if greater than 100 characters or fewer than 10.
     */
    @Test
    public void testValidateAddressNo000(){
        assertFalse(testCust.validateAddress("Thisfails"));
        assertFalse(testCust.validateAddress("ThisfailsThisfailsThisfailsThisfailsThisfailsThisfailsThisfailsThisfailsThisfailsThisfailsThisfailsTh"));
        assertFalse(testCust.validateAddress(""));
    }

    /**
     * Customer Test 15 </P>
     * Customer address should be rejected if they contain non-standard characters (e.g., ^, ", $, !)
     */
    @Test
    public void testValidateAddressNo001(){
        assertFalse(testCust.validateAddress("32 Willow Park ())"));
        assertFalse(testCust.validateAddress("^^Moate Sports"));
        assertFalse(testCust.validateAddress("+=falseAddress.com.sql.javajdbc"));
    }

    /**
     * Customer Test 16</p>
     * Customer address should be accepted when within 10 to 100 character and contains limited special characters
     * such as ',' or '-'.
     */
    @Test
    public void testValidateAddressNo002(){
        assertTrue(testCust.validateAddress("Moate Motors, Main Street Moate"));
        assertTrue(testCust.validateAddress("Peddler Mac's Bar, Church St., Athlone"));
    }

    /**
     * Customer Test 17 </p>
     * Checks that, given invalid inputs, setters throw IllegalArgumentExceptions.
     */
    @Test
    public void testSetExceptions(){
        assertThrows(IllegalArgumentException.class, () -> {
            testCust.setAddress("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            testCust.setPhoneNo("090HHH3959");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            testCust.setFirstName("J1mmy");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            testCust.setLastName("0'Bri4n");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            testCust.setEircode("FailPlease");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            testCust.setAddress("Homeless");
        });
    }
}
