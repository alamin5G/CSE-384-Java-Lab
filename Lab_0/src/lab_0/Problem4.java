
package lab_0;
import java.util.Scanner;
/**
 *
 * @author Md. Alamin
 * @id 21303134
 * @section A
 * @semester - Fall-2024
 */
public class Problem4 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter principal amount: ");
        double p = input.nextDouble();
        System.out.print("Enter rate of interest: ");
        double r = input.nextDouble();
        System.out.print("Enter the number of years: ");
        int n = input.nextInt();
        double si = (p*r*n)/100;
        System.out.println("The simple interest is " + si + ".");
    }
}
