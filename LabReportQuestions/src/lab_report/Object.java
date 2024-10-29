package lab_report;

/**
 *
 * @author alami
 */
public class Object {

    public static void main(String[] args) {

        //first vehicle object
        Vehicle privateCar = new Vehicle("Toyota", "corola-x", "2012", "Private Car");

        privateCar.setColor("Rose");
        privateCar.setEngine("R1Engine");
        privateCar.setFuelType("hybrid");
        privateCar.setMilagePerLiter(10);
        privateCar.setSeatingCapacity(4);
        System.out.println(privateCar);;

        //invoking the functions
        System.out.println("");
        privateCar.startVehicle();
        privateCar.driveVehicle();
        privateCar.stopVehicle();

        //second vehicle object
         //first vehicle object
         System.out.println("");
         System.out.println("");
        Vehicle microCar = new Vehicle("Hiace", "premium-x", "2014", "Micro Car");

        microCar.setColor("White");
        microCar.setEngine("REngine");
        microCar.setFuelType("hybrid");
        microCar.setMilagePerLiter(12);
        microCar.setSeatingCapacity(9);
        System.out.println(microCar);

        //invoking the functions
        System.out.println("");
        microCar.startVehicle();
        microCar.driveVehicle();
        microCar.stopVehicle();
        
        
        Car myCar = new Car("Toyota", "corola-x", "2012", "Private Car");
        myCar.setColor("Rose Gold");
        myCar.setEngine("R5Engine");
        myCar.setFuelType("hyrid");
        myCar.setMilagePerLiter(20);
        myCar.setSeatingCapacity(4);
        System.out.println(myCar);
        
         //invoking the overriden method
        System.out.println("");
        myCar.startVehicle();
        myCar.driveVehicle();
        myCar.stopVehicle();
        
        System.out.println("");
        System.out.println("");
        
        Bus myBus = new Bus("Hino", "1K-J", "2005", "Chair-coach Bus");

        myBus.setColor("Blue Light");
        myBus.setEngine("Turbo Engine");
        myBus.setFuelType("Disel");
        myBus.setMilagePerLiter(15);
        myBus.setSeatingCapacity(40);
        System.out.println(myBus);

        //invoking the overriden method
        System.out.println("");
        myBus.startVehicle();
        myBus.driveVehicle();
        myBus.stopVehicle();
        
        
        
        
    }

}
