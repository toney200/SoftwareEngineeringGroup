import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeliveryAreaSQLAccessTest {

    private DeliveryAreaSQLAccess deliveryAreaSQLAccess;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws Exception {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        deliveryAreaSQLAccess = new DeliveryAreaSQLAccess(mockConnection);  
    }

    @Test
    public void testInsertDeliveryAreaDetails() throws Exception {
        DeliveryArea deliveryArea = new DeliveryArea("Test Area");
        
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);  

        boolean result = deliveryAreaSQLAccess.insertDeliveryAreaDetails(deliveryArea);
        assertTrue(result);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateDeliveryAreaName() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);  

        boolean result = deliveryAreaSQLAccess.updateDeliveryAreaName(1, "New Name");
        assertTrue(result);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteDeliveryAreaByID() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); 

        boolean result = deliveryAreaSQLAccess.deleteDeliveryAreaByID(1);
        assertTrue(result);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}
