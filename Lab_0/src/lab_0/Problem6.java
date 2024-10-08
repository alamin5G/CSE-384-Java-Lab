package lab_0;
import java.util.Scanner;
/**
 *
 * @author Md. Alamin
 * @id 21303134
 * @section A
 * @semester - Fall-2024
 */
public class Problem6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = input.nextInt();
        
        if(number % 2 != 0){
            System.out.println("Your entered " + number + " is odd number.");
        }else{
            System.out.println("Your entered " + number + " is even number.");
        }
    }
}
