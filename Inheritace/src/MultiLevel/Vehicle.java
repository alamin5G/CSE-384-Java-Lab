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
public class Vehicle {
    protected int wheels;
    protected int doors;
    protected int seat;

    public Vehicle(int wheels, int doors, int seat) {
        this.wheels = wheels;
        this.doors = doors;
        this.seat = seat;
        
    }
    
    void created(String st){
        System.out.println(st + " created" );
    }
    
    void start()
    {
        System.out.println("Vehicle Started");
    }
    void stop()
    {
        System.out.println("Vehichle Stopped");
    }
    
    void drive(){
        System.out.println("Vehichle is running!");
    }

    @Override
    public String toString() {
        return " " + "wheels=" + wheels + ", doors=" + doors + ", seat=" + seat ;
    }
    
    
}
