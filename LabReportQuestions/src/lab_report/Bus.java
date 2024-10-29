
package lab_report;

/**
 *
 * @author alami
 */
public class Bus extends Vehicle{
    
     public Bus(String brand, String model, String year, String type) {
        super(brand, model, year, type);
    }
    
     // functionalities
    @Override
    public void startVehicle() {
        System.out.println("The Bus is starting.");
    }

    
    public void driveVehicle() {
        System.out.println("The Bus is driving.");
    }

    public void stopVehicle() {
        System.out.println("The Bus has stopped.");
    }
    
    
}
