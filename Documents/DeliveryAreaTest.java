import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeliveryAreaTest {

    private DeliveryArea deliveryArea;

    @BeforeEach
    public void setUp() {
        deliveryArea = new DeliveryArea(); 
    }

    @Test
    public void testGetName() {
        assertEquals("Main Area", deliveryArea.getDeliveryAreaName());
    }

    @Test
    public void testSetName() {
        deliveryArea.setDeliveryAreaName("Updated Area");
        assertEquals("Updated Area", deliveryArea.getDeliveryAreaName());
    }

    @Test
    public void testValidateName() {
        assertTrue(deliveryArea.validateDeliveryAreaName("Valid Name"));
        assertFalse(deliveryArea.validateDeliveryAreaName(""));
    }

    @Test
    public void testGetDeliveryAreaID() {
        deliveryArea.setDeliveryAreaID(100);
        assertEquals(100, deliveryArea.getDeliveryAreaID());
    }

    @Test
    public void testSetDeliveryAreaID() {
        deliveryArea.setDeliveryAreaID(200);
        assertEquals(200, deliveryArea.getDeliveryAreaID());
    }

    @Test
    public void testSetNameToNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            deliveryArea.setName(null); 
        });
    }
}
