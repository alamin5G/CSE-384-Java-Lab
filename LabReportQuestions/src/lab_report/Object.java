
package lab_report;

/**
 *
 * @author alami
 */
public class Object {
    public static void main(String[] args) {
         
    Vehicle privateCar = new Vehicle("Toyota", "corola-x", "2012", "Private Car");
    
    privateCar.setColor("Rose");
    privateCar.setEngine("R1Engine");
    privateCar.setFuelType("hybrid");
    privateCar.setMilagePerLiter(10);
    privateCar.setSeatingCapacity(4);
    privateCar.displayVehicleInfo();
    
    privateCar.startVehicle();
    privateCar.driveVehicle();
    privateCar.stopVehicle();
    }
    
}
