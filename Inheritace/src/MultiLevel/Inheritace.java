/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiLevel;

/**
 *
 * @author faculty
 */
public class Inheritace {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Car a = new BMW(3, 2, 4, "private", "red", "BMW", "Z4", 2018);
        System.out.println("Car Info: " + a.toString());
        a.start();
        a.drive();
        a.stop();
        System.out.println();
        
        //creating another class
        BMW b = new BMW(3, 5, 9, "micro", "white", "Toyota", "hiace", 2009);
        b.start();
        b.drive();
        b.stop();
        b.setHasTurboEngine(true);
        System.out.println("Car Info: " + b.toString());
    }

}
