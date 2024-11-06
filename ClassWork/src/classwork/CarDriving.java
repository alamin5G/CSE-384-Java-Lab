/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classwork;


/**
 *
 * @author alami
 */
// Class representing a Car with safety features
class Car {
    private final boolean safetyFeatureActive;
    private boolean approachingCarDetected;

    // Constructor to initialize the safety feature and detection status
    public Car(boolean safetyFeatureActive) {
        this.safetyFeatureActive = safetyFeatureActive;
        this.approachingCarDetected = false;
    }

    // Method to simulate driving on the wrong side
    public void driveWrongSide() {
        System.out.println("Driving on the wrong side of the road...");
        detectApproachingCar();
    }

    // Method to detect an approaching car
    private void detectApproachingCar() {
        // Simulating car detection
        this.approachingCarDetected = true;
        System.out.println("Warning: Approaching car detected!");

        if (safetyFeatureActive) {
            avoidAccident();
        } else {
            System.out.println("No safety feature active. High risk of accident!");
        }
    }

    // Method to avoid accident
    private void avoidAccident() {
        if (approachingCarDetected) {
            System.out.println("Activating safety feature to avoid accident...");
            // Simulating avoiding an accident
            System.out.println("Accident avoided! Some scratches on the car but you are safe.");
        }
    }
}

public class CarDriving {
    public static void main(String[] args) {
        // Create a Car instance with safety feature active
        Car myCar = new Car(true);

        // Simulate the scenario
        myCar.driveWrongSide();
    }
}

