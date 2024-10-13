import org.junit.Test;
import junit.framework.Assert;

public class CustomerTest extends Customer{
    Customer testCust = new Customer();
    
    @Test
    public void testValidateName001(){
        Assert.assertEquals(false, testCust.validateEircode(""));
    }
}
