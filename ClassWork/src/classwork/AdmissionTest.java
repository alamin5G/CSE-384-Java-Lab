
package classwork;

import java.util.Scanner;

/**
 *
 * @author alami
 */

public class AdmissionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input marks for Student A
        System.out.print("Enter total marks: ");
        int totalMarks = scanner.nextInt();

        System.out.print("Enter Math marks: ");
        int mathMarks = scanner.nextInt();

        System.out.print("Enter Physics marks: ");
        int physicsMarks = scanner.nextInt();

        try {
            if (totalMarks < 40) {
                throw new ArithmeticException("Student did not qualify the admission test.");
            } else {
                
                if (mathMarks >= 20 && physicsMarks >= 20 && mathMarks != 0 && physicsMarks != 0 && mathMarks != -1 && physicsMarks != -1) { //assuming -1 means unanswered questions
                    System.out.println("Student qualifies for CSE department.");
                } else {
                    System.out.println("Student does not qualify for CSE department.");
                }
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

    }
}
