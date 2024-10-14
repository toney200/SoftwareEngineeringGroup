
import org.junit.Test;

import junit.framework.Assert;

public class CustomerTest extends Customer{
    Customer testCust = new Customer();
    
    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 1
     * Should return false when a name is more than 20
     */
    public void testValidateFirstName001(){
        Assert.assertEquals(false, testCust.validateFirstName("fnthrdsfytunmightjkiw"));
    }

    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 2
     * Should return false when first name less than 2
     */
    public void testValidateFirsName002(){
        Assert.assertEquals(false, testCust.validateFirstName("E"));
    }

    @SuppressWarnings("deprecation")
    @Test 
    /*
     * Customer Test 3 
     * Should return true when first name is 2 - 20(inclusive) characters and contains no numbers
     */
    public void testValidateFirstName003(){
        Assert.assertEquals(true, testCust.validateFirstName("John"));
    }

    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 4
     * Should return false when first name contains numbers
     */
    public void testValidateFirstName004(){
        Assert.assertEquals(false, testCust.validateFirstName("S4oirse"));
    }

    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 5
     * Should return false when last name is greater than 20 characters
     */
    public void testValidateLastName(){
        Assert.fail("Test fails");
    }

    @SuppressWarnings("deprecation")
    @Test
     /*
     * Customer Test 6 
     * Should return false when the last name is shorter than 2 characters 
     */
    public void testValidateLastName002(){
        Assert.fail();
    }

    @SuppressWarnings("deprecation")
    @Test
    /*
     * Customer Test 13
     * Should return true when a valid phone number is passed in
     */
    public void testValidatePhoneNo001(){
        Assert.assertEquals(true, testCust.validatePhoneNo("0873925974"));
    }
}
