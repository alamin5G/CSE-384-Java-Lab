package electronics_equipment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alami
 */

abstract class ElectronicsEquipment implements Runnable{
    private String manufacturerName;
    private int cost;
    private double weight;
    private String powerRating;
    private boolean isTurnedOn;

    public ElectronicsEquipment(String manufacturerName, int cost, double weight, String powerRating, boolean isTurnedOn) throws TypeNotPresentException, Exception{ 
        this.manufacturerName = manufacturerName;
        
        if (cost < 1){
            throw new Exception("Price can't be less one");
        }
        
        this.weight = weight;
        this.powerRating = powerRating;
        this.isTurnedOn = isTurnedOn;
    }

  

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPowerRating() {
        return powerRating;
    }

    public void setPowerRating(String powerRating) {
        this.powerRating = powerRating;
    }

    public boolean isIsTurnedOn() {
        return isTurnedOn;
    }

    public void setIsTurnedOn(boolean isTurnedOn) {
        this.isTurnedOn = isTurnedOn;
    }
    
    
    

    public abstract String display_product_information();
    
    public abstract boolean isWorking();
    
}

class Computers extends ElectronicsEquipment{

    public Computers(String manufacturerName, int cost, double weight, String powerRating, boolean isTurnedOn) throws TypeNotPresentException, Exception {
        super(manufacturerName, cost, weight, powerRating, isTurnedOn);
    }

   

    @Override
    public boolean isWorking() {
     if(!super.isIsTurnedOn()){
         System.out.println("No, computer is not working.");
         return false;
     }else{
         System.out.println("Yes, computer is working now!");
         return true;
     }
         
    }
    
    
     @Override
    public String display_product_information() {
    return "Computers information: " + "manufacturerName=" + getManufacturerName() + ", cost=" + getCost() + ", weight=" + getWeight() + ", powerRating=" + getPowerRating() + ", isTurnedOn=" + isIsTurnedOn();
    }

    @Override
    public void run() {
        System.out.println("Computer class is running!");
         }

   
    
    
    
}

class CellPhone extends ElectronicsEquipment{

    public CellPhone(String manufacturerName, int cost, double weight, String powerRating, boolean isTurnedOn) throws TypeNotPresentException, Exception {
        super(manufacturerName, cost, weight, powerRating, isTurnedOn);
    }

   

    @Override
    public boolean isWorking() {
     if(!super.isIsTurnedOn()){
         System.out.println("No, cellphone is not working.");
         return false;
     }else{
         System.out.println("Yes, cellphone is working now!");
         return true;
     }
         
    }
    
    @Override
    public void run() {
        System.out.println("Cell Phone class is running!");
         }

   
    
    
      @Override
    public String display_product_information() {
    return "Cell Phone information: " + "manufacturerName=" + getManufacturerName() + ", cost=" + getCost() + ", weight=" + getWeight() + ", powerRating=" + getPowerRating() + ", isTurnedOn=" + isIsTurnedOn();
    }
    
}

class DigitalCamera extends ElectronicsEquipment{

    public DigitalCamera(String manufacturerName, int cost, double weight, String powerRating, boolean isTurnedOn) throws TypeNotPresentException, Exception {
        super(manufacturerName, cost, weight, powerRating, isTurnedOn);
    }

    @Override
    public boolean isWorking() {
     if(!super.isIsTurnedOn()){
         System.out.println("No, Camera is not working.");
         return false;
     }else{
         System.out.println("Yes, Camera is working now!");
         return true;
     }
         
    }
    
    @Override
    public void run() {
        System.out.println("Digital Cameras class is running!");
         }

   
    
      @Override
    public String display_product_information() {
    return "Cell Phone information: " + "manufacturerName=" + super.getManufacturerName() + ", cost=" + super.getCost() + ", weight=" + super.getWeight() + "grams, powerRating=" + super.getPowerRating() + "Watt, isTurnedOn=" + super.isIsTurnedOn();
    }
    
}

public class Equipments {
    
    public static void main(String[] args) {
        
        try{
            Computers computer = new Computers("HP", 50000, 2000, "150", true);
            computer.isWorking();
            computer.display_product_information();
           
            
        }catch(TypeNotPresentException e){
            System.out.println("" + e.getMessage());
        }catch(Exception e){
            System.out.println("" + e.getMessage());
        }finally{
            System.out.println("Executed the full program.");
        }
        
         
        
        try{
           CellPhone cellPhone = new CellPhone("Samsung", 0, 200, "25", false);
            cellPhone.isWorking();
            cellPhone.display_product_information();
           
            
        }catch(TypeNotPresentException e){
            System.out.println("" + e.getMessage());
        }catch(Exception e){
            System.out.println("" + e.getMessage());
        }finally{
            System.out.println("Executed the full program.");
        }
         
         
        
         try{
            DigitalCamera camera = new DigitalCamera("Canon", 100000, 800, "15", true);
            camera.isWorking();
            camera.display_product_information();           
            
        }catch(TypeNotPresentException e){
            System.out.println("" + e.getMessage());
        }catch(Exception e){
            System.out.println("" + e.getMessage());
        }finally{
            System.out.println("Executed the full program.");
        }
         
         
            
            
        
        
       
    }
}
