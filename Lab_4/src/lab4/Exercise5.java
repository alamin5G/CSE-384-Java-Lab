
package lab4;

/**
 *
 * @author alami
 */
class Exercise5 {
    // Instance method (non-parameterized)
    public void displayMessage() {
        System.out.println("Hello from a non-parameterized instance method!");
    }

    // Instance method (parameterized)
    protected void printSum(int a, int b) {
        System.out.println("Sum: " + (a + b));
    }

    // Static method (non-parameterized)
    static void showStaticMessage() {
        System.out.println("Hello from a static method!");
    }

    // Static method (parameterized)
    private static void multiply(int a, int b) {
        System.out.println("Product: " + (a * b));
    }

    public static void main(String[] args) {
        Exercise5 example = new Exercise5();

        // Calling instance methods
        example.displayMessage();
        example.printSum(5, 10);

        // Calling static methods
        showStaticMessage();
        multiply(3, 4);
    }
}

