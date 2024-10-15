
import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter seconds: ");
        int seconds = input.nextInt();
        int minutes = seconds > 59 ? (seconds / 60) : 0;
        seconds = seconds - (minutes * 60);

        System.out.println(minutes + " minutes and " + seconds + " seconds.");
    }

}
