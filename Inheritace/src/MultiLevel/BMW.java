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
public class BMW  extends Car{
    
    boolean hasTurboEngine;
    
    public BMW(int wheels, int doors, int seat, String carType, String color, String make, String model, int year) {
        super(wheels, doors, seat,carType, color, make, model, year);
        this.hasTurboEngine = false;
        super.created("BMW class");
    }

    public void setHasTurboEngine(boolean hasTurboEngine) {
        this.hasTurboEngine = hasTurboEngine;
    }

    @Override
    void start() {
        System.out.println("BMW Started");
        }

    @Override
    void drive() {
        System.out.println("BMW Driving");
        }

    @Override
    void stop() {
        //System.out.println("BMW Stopped");
        super.stop();
       }
    
    

    @Override
    public String toString() {
        return " " + "hasTurboEngine=" + hasTurboEngine + super.toString() ;
    }
    
    
}
