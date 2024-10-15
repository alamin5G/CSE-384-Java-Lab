package lab_2;

/**
 *
 * @author alami
 */
import java.util.Scanner;

public class GreatestCommonDivisor {

    /**
     * Main method
     */
    public static void main(String[] args) {
        // Create a Scanner
        int gcd = 1, n1, n2;
        long startTime, endTime, elapsedTime;

        Scanner input = new Scanner(System.in);

        // Prompt the user to enter two integers
        System.out.print("Enter first integer: ");
        n1 = input.nextInt();

        System.out.print("Enter second integer: ");
        n2 = input.nextInt();

        startTime = System.nanoTime();

        // Initial gcd is 1
        int k = 2; // Possible gcd

        /*
        while (k <= n1 && k <= n2) {
            if (n1 % k == 0 && n2 % k == 0) {
                gcd = k; // Update gcd
            }
            k++;
        }*/
        
       /* for (; k <= n1 && k <= n2; k++) {
            if (n1 % k == 0 && n2 % k == 0) {
                gcd = k;
            }
        }
        
       */
       
       /*for (; k <= n1/2 && k <= n2/2; k++) {
            if (n1 % k == 0 && n2 % k == 0) {
                gcd = k;
            }
        }
        */
       
       for (; k <= n1 && k <= n2; k++) {
            if (n1 % k == 0 && n2 % k == 0) {
                gcd = k;
            }
        }
        
        

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;

        System.out.println("Elapsed Time=" + elapsedTime);

        System.out.println("The greatest common divisor for " + n1 + " and " + n2 + " is " + gcd);
    }
}
