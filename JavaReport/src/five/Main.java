/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package five;

/**
 *
 * @author alami
 */
import java.util.Scanner;

// Interface A with methods for user input and checking automorphic number
interface A {
    void takeInputForAutomorphic();
    boolean isAutomorphic(int number);
}

// Interface B with methods for user input and checking duck number
interface B {
    void takeInputForDuck();
    boolean isDuckNumber(int number);
}

// Class C implements both interfaces
class C implements A, B {
    private int automorphicNumber;
    private int duckNumber;

    // Take input for automorphic number
    @Override
    public void takeInputForAutomorphic() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to check if it's Automorphic: ");
        automorphicNumber = sc.nextInt();
    }

    // Check if the number is automorphic
    @Override
    public boolean isAutomorphic(int number) {
        int square = number * number;
        String numStr = String.valueOf(number);
        String squareStr = String.valueOf(square);

        return squareStr.endsWith(numStr);
    }

    // Take input for duck number
    @Override
    public void takeInputForDuck() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to check if it's a Duck Number: ");
        duckNumber = sc.nextInt();
    }

    // Check if the number is a duck number
    @Override
    public boolean isDuckNumber(int number) {
        String numStr = String.valueOf(number);
        if (numStr.charAt(0) == '0') {
            return false; // Leading zero is not allowed
        }
        return numStr.contains("0");
    }

    // Main logic to run the checks
    public void runChecks() {
        // Automorphic Number Check
        takeInputForAutomorphic();
        if (isAutomorphic(automorphicNumber)) {
            System.out.println(automorphicNumber + " is an Automorphic Number.");
        } else {
            System.out.println(automorphicNumber + " is NOT an Automorphic Number.");
        }

        // Duck Number Check
        takeInputForDuck();
        if (isDuckNumber(duckNumber)) {
            System.out.println(duckNumber + " is a Duck Number.");
        } else {
            System.out.println(duckNumber + " is NOT a Duck Number.");
        }
    }
}

// Main class to test the functionality
public class Main {
    public static void main(String[] args) {
        C obj = new C();
        obj.runChecks();
    }
}

