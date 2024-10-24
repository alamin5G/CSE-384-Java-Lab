
package lab_report;

/**
 *
 * @author alami
 */
public class Vehicle {
    
    //properties
    private final String brand;
    private final String model;
    private final String year;
    private int tyre;
    private final String type;
    private String color;
    private int milagePerLiter;
    private String engine;
    private String fuelType;
    private int seatingCapacity;
    
    Vehicle(String brand, String model, String year,String type){
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.tyre = 4;
        this.type = type;
        this.color = "Silver";
        this.milagePerLiter = 1;
        this.engine = "engine";
        this.fuelType = "petrol";
        this.seatingCapacity = 2;
    }
    
    public void setTyre(int tyre){
        this.tyre = tyre;
    }
    
    public int getTyre(){
        return this.tyre;
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    public String getColor(){
        return this.color;
    }
    
    public void setMilagePerLiter(int milagePerLiter){
        this.milagePerLiter = milagePerLiter;
    }
    
    public int getMilagePerLiter(){
        return milagePerLiter;
    }
    
    public void setEngine(String engine){
        this.engine = engine;
    }
    
    public String getEngine(){
        return this.engine;
    }
    
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
    
    // functionalities
    public void startVehicle() {
        System.out.println("The vehicle is starting.");
    }

    public void driveVehicle() {
        System.out.println("The vehicle is driving.");
    }

    public void stopVehicle() {
        System.out.println("The vehicle has stopped.");
    }

    public void displayVehicleInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Type: " + type);
        System.out.println("Color: " + color);
        System.out.println("Tyre: " + tyre);
        System.out.println("Milage Per Liter: " + milagePerLiter);
        System.out.println("Engine: " + engine);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Seating Capacity: " + seatingCapacity);
    }

}
