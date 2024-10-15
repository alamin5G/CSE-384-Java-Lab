package lab_2;

/**
 *
 * @author alami
 */
import java.util.Scanner;

public class GreatestCommonDivisor3 {

    /**
     * Main method
     */
    public static void main(String[] args) {
        // Create a Scanner
        int gcd = 1, n1, n2, p, t1, t2;
        long startTime, endTime, elapsedTime;

        Scanner input = new Scanner(System.in);

        // Prompt the user to enter two integers
        System.out.print("Enter first integer: ");
        n1 = input.nextInt();
        t1 = n1;

        System.out.print("Enter second integer: ");
        n2 = input.nextInt();
        t2 = n2;

        startTime = System.nanoTime();

        if (n2 < n1) {

            while (true) {

                if (n1 % n2 == 0) {
                    gcd = n2;
                    break;
                } else {
                    p = n1;
                    n1 = n2;
                    n2 = p % n2;
                }

            }
        } else {

            while (true) {
                if (n2 % n1 == 0) {
                    gcd = n1;
                    break;
                } else {
                    p = n2;
                    n2 = n1;
                    n1 = p % n1;
                }

            }
        }

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;

        System.out.println("Elapsed Time=" + elapsedTime);

        System.out.println("The greatest common divisor for " + t1 + " and " + t2 + " is " + gcd);
    }
}
