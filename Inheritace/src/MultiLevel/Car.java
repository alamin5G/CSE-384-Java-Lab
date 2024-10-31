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
public class Car extends Vehicle {

    private String carType;
    private String color;
    private String make;
    private String model;
    private int year;

    public Car(int wheels, int doors, int seat, String carType, String color, String make, String model, int year) {
        super(wheels, doors, seat);
        this.carType = carType;
        this.color = color;
        this.make = make;
        this.model = model;
        this.year = year;

        //invoking method
        //super.created("Car Class");
    }

    @Override
    void start() {
        System.out.println("Car Started");
    }

    @Override
    void drive() {
        System.out.println("Car Driving");
    }

    @Override
    void stop() {
        //System.out.println("Car Stopped");
        super.stop();
    }

    @Override
    public String toString() {
        return " " + "carType=" + carType + ", color=" + color + ", make=" + make + ", model=" + model + ", year=" + year + super.toString() ;
    }

}
