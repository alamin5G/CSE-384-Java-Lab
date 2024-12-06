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
        System.out.println("Custom Exception Message: " + message);
    }
}

// Main class to demonstrate the custom exception
public class CustomExceptionDemo {
    public static void main(String[] args) {
        try {
            // Simulate a condition that causes the custom exception
            throw new MyCustomException("Something went wrong in the program!");
        } catch (MyCustomException e) {
            // Handle the custom exception
            System.out.println("Caught the custom exception.");
            e.printMessage(); // Print the stored message
        }
    }
}

