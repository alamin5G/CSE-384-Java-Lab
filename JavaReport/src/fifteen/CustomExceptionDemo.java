/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fifteen;

/**
 *
 * @author alami
 */
// Custom exception class
class MyCustomException extends Exception {
    // Field to store the message
    private String message;

    // Constructor
    public MyCustomException(String message) {
        this.message = message;
    }

    // Method to print the stored message
    public void printMessage() {
        System.out.println("Custom Exception : " + message);
    }
}

// Main class to demonstrate the custom exception
public class CustomExceptionDemo {
    public static void main(String[] args) {
       int dividend = 10;
        int divisor = 0; // This will cause the exception

        try {
            // Simulate a condition that causes the custom exception
            if (divisor == 0) {
                throw new MyCustomException("Division by zero error! Cannot divide " + dividend + " by zero.");
            }

            int result = dividend / divisor;
            System.out.println("Result: " + result);

        } catch (MyCustomException e) {
            // Handle the custom exception
            System.out.println("Caught the custom exception.");
            e.printMessage(); // Print the stored message
        }
    }
}