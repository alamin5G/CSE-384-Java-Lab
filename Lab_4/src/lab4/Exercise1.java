package lab4;

import java.util.Scanner;
/**
 *
 * @author alami
 */
public class Exercise1 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Insert array size: ");
        int n = input.nextInt();
        System.out.print("Enter 1st array: ");
        int[] a = new int[n];
        for(int i = 0; i<n; i++){
            a[i] = input.nextInt();
        }
        System.out.print("Enter 2nd array: ");
        int[] b = new int[n];
        for(int i = 0; i<n; i++){
            b[i] = input.nextInt();
        }
        
        //method which has passed the size of the array, array 1 and array 2;
        int sum = sum(n, a, b);
        System.out.println("sum of two arrays = " + sum);
    }
    
    
    private static int sum(int size, int[] arr1, int[] arr2){
        int sum = 0;
        for(int i = 0; i<size; i++){
            sum = sum + arr1[i] + arr2[i];
        }
        return sum;
    }
}
