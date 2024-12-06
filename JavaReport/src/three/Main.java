package three;
// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class Main {
    public static void main(String[] args) {
        System.out.println("Enter a String: ");
        Scanner input = new Scanner(System.in);
        String a = input.next();
        StringBuffer bf = new StringBuffer(a);
        a = bf.reverse().toString();
        System.out.println("Reverse String: "+a );
    }
}