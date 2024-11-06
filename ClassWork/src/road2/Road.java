
package road2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alami
 */
// Interface for cars
interface Car {
    void drive();
    void detectApproachingCar(Car otherCar);
    void avoidAccident(Car otherCar);
}

// Abstract class for common car behaviors
abstract class AbstractCar implements Car {
    protected String name;
    protected boolean safetyFeatureActive;

    public AbstractCar(String name, boolean safetyFeatureActive) {
        this.name = name;
        this.safetyFeatureActive = safetyFeatureActive;
    }

    @Override
    public void detectApproachingCar(Car otherCar) {
        System.out.println(this.name + ": Approaching car detected! ");
        if (safetyFeatureActive) {
            avoidAccident(otherCar);
        } else {
            System.out.println(this.name + ": No safety feature active. High risk of accident!");
        }
    }

    @Override
    public void avoidAccident(Car otherCar) {
        System.out.println(this.name + ": Avoiding collision with " + ((AbstractCar) otherCar).getName() + ".");
        System.out.println(this.name + ": Accident avoided! Some scratches on the car but you are safe.");
    }

    public String getName() {
        return this.name;
    }
}

// Class for a specific car model with safety features
class SafeCar extends AbstractCar {
    public SafeCar(String name) {
        super(name, true);
    }

    @Override
    public void drive() {
        System.out.println(this.name + " is driving...");
    }
}

// Class for a car model without safety features
class UnsafeCar extends AbstractCar {
    public UnsafeCar(String name) {
        super(name, false);
    }

    @Override
    public void drive() {
        System.out.println(this.name + " is driving without safety features...");
    }
}

// Road class to manage multiple cars
public class Road {
    private List<Car> carsOnRoad;

    public Road() {
        this.carsOnRoad = new ArrayList<>();
    }

    public void addCar(Car car) {
        carsOnRoad.add(car);
        System.out.println(((AbstractCar) car).getName() + " is on the road.");
    }

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

        // Create cars with and without safety features
        Car car1 = new SafeCar("Car 1");
        Car car2 = new SafeCar("Car 2");
        Car car3 = new UnsafeCar("Car 3");

        // Add cars to the road
        road.addCar(car1);
        road.addCar(car2);
        road.addCar(car3);

        // Simulate driving and interactions
        road.simulateDriving();
    }
}

