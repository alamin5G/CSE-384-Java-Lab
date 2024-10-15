
import java.util.Scanner;

public class ComputeArea {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number for radius: ");
        //input the radius
        double radius = input.nextDouble();
        // Compute area
        double area = radius * radius * 3.14159;

        // Display result
        System.out.println("The area for the circle of radius " + radius + " is " + area);
    }
}
