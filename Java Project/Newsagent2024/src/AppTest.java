import org.junit.Test;

import junit.framework.Assert;

public class AppTest {
    @Test

    public void testApp(){
        App app = new App();
        Assert.assertEquals(true, app.iAmTrue());
    }
}
