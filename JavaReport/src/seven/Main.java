/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seven;

/**
 *
 * @author alami
 */
class Car {
    private String driverName;
    private int speed; // Speed in km/h
    private String roadSide; // "left" or "right"
    private boolean isSafetyFeatureEnabled;

    // Constructor
    public Car(String driverName, int speed, String roadSide, boolean isSafetyFeatureEnabled) {
        this.driverName = driverName;
        this.speed = speed;
        this.roadSide = roadSide;
        this.isSafetyFeatureEnabled = isSafetyFeatureEnabled;
    }

    // Getters
    public String getDriverName() {
        return driverName;
    }

    public int getSpeed() {
        return speed;
    }

    public String getRoadSide() {
        return roadSide;
    }

    public boolean isSafetyFeatureEnabled() {
        return isSafetyFeatureEnabled;
    }

    // Method to simulate driving
    public void drive(String newRoadSide, int newSpeed) {
        this.roadSide = newRoadSide;
        this.speed = newSpeed;
        System.out.println(driverName + " is driving on the " + roadSide + " side at " + speed + " km/h.");
    }
}

class SafetySystem {
    // Method to detect approaching car
    public void detectOppositeCar(Car car, Car oppositeCar) {
        if (!car.getRoadSide().equals(oppositeCar.getRoadSide())) {
            System.out.println("Warning! An opposite car is approaching.");
            if (car.isSafetyFeatureEnabled()) {
                System.out.println("Safety feature activated! Speed reduced and collision avoided.");
                car.drive(car.getRoadSide(), 0); // Stops the car
            } else {
                System.out.println("Safety feature is disabled. Collision risk high!");
            }
        } else {
            System.out.println("No danger detected. Continue driving safely.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a car with a safety feature enabled
        Car myCar = new Car("John", 80, "right", true);

        // Create an opposite car
        Car oppositeCar = new Car("Doe", 60, "left", false);

        // Safety system to monitor cars
        SafetySystem safetySystem = new SafetySystem();

        // Simulate driving on the wrong side
        myCar.drive("left", 100); // John drives on the wrong side

        // Detect potential danger
        safetySystem.detectOppositeCar(myCar, oppositeCar);
    }
}

