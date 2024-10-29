
package lab_report;

/**
 *
 * @author alami
 */
public class Car extends Vehicle{
    
    public Car(String brand, String model, String year, String type) {
        super(brand, model, year, type);
    }
    
     // functionalities
    @Override
    public void startVehicle() {
        System.out.println("The Car is starting.");
    }

    
    public void driveVehicle() {
        System.out.println("The Car is driving.");
    }

    public void stopVehicle() {
        System.out.println("The Car has stopped.");
    }
    
    
}
