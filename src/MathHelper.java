public class MathHelper {

    public int simple_int = 7;
    public static int static_int = 7;
    final public static int final_int = 7;

    public void changeSimpleInt() {
        this.simple_int = 8;
        static_int = 8;
    }

    public static void changeSimpleIntByStaticFunction() {
//        this.simple_int = 8; // cannot do it
//        final_int = 8; // cannot do it
        static_int = 9;
    }

    public int calc(int a, int b, char action) {
        if (action == '+') {
            return plus(a, b);
        } else if (action == '-') {
            return minus(a, b);
        } else if (action == '*') {
            return multiply(a, b);
        } else if (action == '/') {
            return divide(a, b);
        } else {
            return typeAnErrorAndReturnDefaultValues("Wrong action: " + action);
        }
    }

    private int typeAnErrorAndReturnDefaultValues(String error_message) {
        System.out.println(error_message);
        return 0;
    }

    private int plus(int a, int b) {
        return a + b;
    }

    private int minus(int a, int b) {
        return a - b;
    }

    private int multiply(int a, int b) {
        return a * b;
    }

    private int divide(int number, int divider) {
        if (divider == 0) {
            return typeAnErrorAndReturnDefaultValues("Cannot divide by zero");
        } else {
            return number / divider;
        }
    }
}
