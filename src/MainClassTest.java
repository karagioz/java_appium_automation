import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainClassTest {
    private static MainClass mainClass;

    @BeforeClass
    public static void setUp() {
        mainClass = new MainClass();
    }

    @Test
    public void testGetLocalNumber() {
        int expected = 14;
        int actual = mainClass.getLocalNumber();
        Assert.assertEquals("Method getLocalNumber returns unexpected value", expected, actual);
    }

    @Test
    public void testGetClassNumber() {
        int threshold = 45;
        int actual = mainClass.getClassNumber();
        Assert.assertTrue("Method getClassNumber returns value less than threshold " + threshold,
                actual > threshold);
    }
}
