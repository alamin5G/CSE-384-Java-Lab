
package six;

/**
 *
 * @author alami
 */
import java.util.Scanner;

class AdmissionTestException extends Exception {
    public AdmissionTestException(String message) {
        super(message);
    }
}

public class Admission {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Take total admission test marks
            System.out.print("Enter total admission test marks: ");
            int totalMarks = sc.nextInt();

            // Check if marks are less than 40
            if (totalMarks < 40) {
                throw new ArithmeticException("Total marks are less than 40. Admission test failed.");
            }

            // Take individual subject marks
            System.out.print("Enter marks for Math: ");
            int mathMarks = sc.nextInt();
            System.out.print("Enter marks for Physics: ");
            int physicsMarks = sc.nextInt();

            // Check for invalid input
            if (mathMarks == 0 || physicsMarks == 0) {
                throw new AdmissionTestException("Marks in neither subject can be zero.");
            }

            if (mathMarks < 0 || physicsMarks < 0) {
                throw new AdmissionTestException("Marks cannot be negative.");
            }

            if (mathMarks < 20 || physicsMarks < 20) {
                throw new AdmissionTestException("Marks in each subject must be at least 20.");
            }

            // If all checks pass, admit the student
            System.out.println("Congratulations! You are eligible for admission in the CSE department.");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (AdmissionTestException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

