/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Single;

/**
 *
 * @author faculty
 */
public class Car extends Vehicle{

    public Car(int wheels, int doors, int seat) {
        super(wheels, doors, seat);
        
        //invoking the super method
        super.acc();
    }
    
}
