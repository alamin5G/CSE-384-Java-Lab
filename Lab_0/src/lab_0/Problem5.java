
package lab_0;

import java.util.Scanner;

/**
 *
 * @author Md. Alamin
 * @id 21303134
 * @section A
 * @semester - Fall-2024
 */
public class Problem5 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the Temperature in Celsius degree: ");
        double temperature = input.nextDouble();
        double fahrenheit = (temperature * 9/5) + 32;
        System.out.println("The fahrenheit temperature is " + fahrenheit + " degree fahrenheit.");
    }
}
