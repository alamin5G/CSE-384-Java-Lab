
package one;

/**
 *
 * @author Student
 */
public class Calculation {
    int z;
    
    public void addition(int x, int y){
        z = x + y;
        System.out.println(" " +x + " + " + y + " = " + z);
    }
    
    public void substraction(int x, int y){
        z = x - y;
        System.out.println(" " +x + " - " + y + " = " + z);
    }
    
}

class My_Calculation extends Calculation{
    
    public void multiplication(int x, int y){
        z = x * y;
         System.out.println(" " +x + " x " + y + " = " + z);
    }
    
    public static void main(String[] args) {
        My_Calculation myCalc = new My_Calculation(); //creating object
        myCalc.addition(5, 7); //invoking addition method
        myCalc.substraction(10, 5); // invoking substract method
        myCalc.multiplication(5, 2); // invoking multiplication method
    }
}
