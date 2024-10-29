/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hierarchical;

import MultiLevel.*;
import Single.*;

/**
 *
 * @author faculty
 */
public class Vehicle {
    int wheels;
    int doors;
    int seat;

    public Vehicle(int wheels, int doors, int seat) {
        this.wheels = wheels;
        this.doors = doors;
        this.seat = seat;
        
    }
    
    void acc()
    {
        System.out.println("Accelarated");
    }
    void brake()
    {
        System.out.println("braking");
    }
}
