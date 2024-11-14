/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classwork;


/**
 *
 * @author alami
 */

class Car {
    private final boolean safetyFeatureActive;
    private boolean approachingCarDetected;

    
    public Car(boolean safetyFeatureActive) {
        this.safetyFeatureActive = safetyFeatureActive;
        this.approachingCarDetected = false;
    }

    public void driveWrongSide() {
        System.out.println("Driving on the wrong side of the road...");
        detectApproachingCar();
    }

    private void detectApproachingCar() {
        this.approachingCarDetected = true;
        System.out.println("Warning: Approaching car detected!");

        if (safetyFeatureActive) {
            avoidAccident();
        } else {
            System.out.println("No safety feature active. High risk of accident!");
        }
    }

    private void avoidAccident() {
        if (approachingCarDetected) {
            System.out.println("Activating safety feature to avoid accident...");
            
            System.out.println("Accident avoided! Some scratches on the car but you are safe.");
        }
    }
}

public class CarDriving {
    public static void main(String[] args) {
        Car myCar = new Car(true);

        // Simulate the scenario
        myCar.driveWrongSide();
    }
}

