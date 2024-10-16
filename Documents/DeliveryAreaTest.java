import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeliveryAreaTest {

    private DeliveryArea deliveryArea;

    @BeforeEach
    public void setUp() {
        deliveryArea = new DeliveryArea("Main Area"); 
    }

    @Test
    public void testGetName() {
        assertEquals("Main Area", deliveryArea.getName());
    }

    @Test
    public void testSetName() {
        deliveryArea.setName("Updated Area");
        assertEquals("Updated Area", deliveryArea.getName());
    }

    @Test
    public void testValidateName() {
        assertTrue(deliveryArea.validateName("Valid Name"));
        assertFalse(deliveryArea.validateName(""));
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
