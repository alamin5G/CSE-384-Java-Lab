
package thirteen;

/**
 *
 * @author alami
 */
import java.util.Scanner;

public class StringCharacterCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt the user for input
        System.out.print("Enter a String: ");
        String input = sc.nextLine();

        // Initialize counters for vowels, consonants, and digits
        int vowels = 0, consonants = 0, digits = 0;

        // Convert string to lowercase to simplify checking
        input = input.toLowerCase();

        // Iterate over each character in the string
        for (char c : input.toCharArray()) {
            // Check for vowels
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            }
            // Check for consonants
            else if ((c >= 'a' && c <= 'z')) {
                consonants++;
            }
            // Check for digits
            else if (c >= '0' && c <= '9') {
                digits++;
            }
        }

        // Calculate the percentages
        int totalChars = input.length();
        double vowelPercentage = (vowels * 100.0) / totalChars;
        double consonantPercentage = (consonants * 100.0) / totalChars;
        double digitPercentage = (digits * 100.0) / totalChars;

        // Print the results
        System.out.printf("Number of vowels: %d (%.2f%%)\n", vowels, vowelPercentage);
        System.out.printf("Number of consonants: %d (%.2f%%)\n", consonants, consonantPercentage);
        System.out.printf("Number of digits: %d (%.2f%%)\n", digits, digitPercentage);
    }
}

