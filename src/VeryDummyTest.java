import org.junit.Test;

public class VeryDummyTest {
    int a = 3;
    int b = 7;
    MathHelper Math = new MathHelper();

    @Test
    public void myFirstTest() {
        double a = 3.5;
        double b = 7.5;
        System.out.println(a + " " + b);
        System.out.println(this.a + " " + this.b);
        this.typeString();
        int r1 = Math.calc(10, 3, '+');
        int r2 = Math.calc(10, 20, '*');
        System.out.println("Results: " + r1 + " " + r2);
    }

    @Test
    public void mySecondTest() {
        System.out.println("Second test. Before changing static_int: " + MathHelper.static_int);
        MathHelper.static_int = 8;
        MathHelper mathObject = new MathHelper();
        System.out.println("Second test. Before changing simple_int: " + mathObject.simple_int);
        mathObject.simple_int = 8;
    }

    @Test
    public void myThirdTest() {
        System.out.println("Third test. Before changing static_int: " + MathHelper.static_int);
        MathHelper.static_int = 8;
        MathHelper mathObject = new MathHelper();
        System.out.println("Third test. Before changing simple_int: " + mathObject.simple_int);
        mathObject.simple_int = 8;
    }

    public void typeString() {
        System.out.println("Hello from typeString!");
    }
}
