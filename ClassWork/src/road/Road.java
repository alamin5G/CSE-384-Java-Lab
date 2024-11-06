
package road;

/**
 *
 * @author alami
 */
import java.util.ArrayList;
import java.util.List;

// Class representing a car with safety features
class Car {
    private String name;
    private boolean safetyFeatureActive;

    // Constructor to initialize the car's name and safety feature status
    public Car(String name, boolean safetyFeatureActive) {
        this.name = name;
        this.safetyFeatureActive = safetyFeatureActive;
    }

    // Method to detect and react to an approaching car
    public void detectApproachingCar(Car otherCar) {
        if (this.safetyFeatureActive) {
            System.out.println(this.name + ": Approaching car detected! Activating safety feature...");
            avoidAccident(otherCar);
        } else {
            System.out.println(this.name + ": Approaching car detected but no safety feature is active.");
        }
    }

    // Method to avoid an accident
    private void avoidAccident(Car otherCar) {
        System.out.println(this.name + ": Avoiding collision with " + otherCar.getName() + ".");
        // Simulating avoiding an accident
        System.out.println(this.name + ": Accident avoided! Some scratches on the car but you are safe.");
    }

    // Getter for car name
    public String getName() {
        return this.name;
    }
}

public class Road {
    private List<Car> carsOnRoad;

    // Constructor to initialize the list of cars on the road
    public Road() {
        this.carsOnRoad = new ArrayList<>();
    }

    // Method to add a car to the road
    public void addCar(Car car) {
        carsOnRoad.add(car);
        System.out.println(car.getName() + " is on the road.");
    }

    // Method to simulate cars encountering each other on the road
    public void simulateDriving() {
        for (int i = 0; i < carsOnRoad.size(); i++) {
            for (int j = i + 1; j < carsOnRoad.size(); j++) {
                Car car1 = carsOnRoad.get(i);
                Car car2 = carsOnRoad.get(j);
                car1.detectApproachingCar(car2);
                car2.detectApproachingCar(car1);
            }
        }
    }

    public static void main(String[] args) {
        Road road = new Road();

        // Create cars with their safety feature status
        Car car1 = new Car("Car 1", true);
        Car car2 = new Car("Car 2", true);
        Car car3 = new Car("Car 3", false); // No safety feature

        // Add cars to the road
        road.addCar(car1);
        road.addCar(car2);
        road.addCar(car3);

        // Simulate driving and interactions
        road.simulateDriving();
    }
}
