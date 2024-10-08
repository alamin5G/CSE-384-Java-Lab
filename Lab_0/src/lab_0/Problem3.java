package lab_0;

import java.util.Scanner;

/**
 *
 * @author Md. Alamin
 * @id 21303134
 * @section A
 * @semester - Fall-2024
 */
public class Problem3 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter any three names sperated by a space: (ex: John Bob Alice) - ");
        String firstName = input.next();
        
        String secondName = input.next();
        
        String thirdName = input.next();
        
        System.out.println("Hi " + firstName + ", " + secondName + ", and " + thirdName + ".");
                
    }
}
