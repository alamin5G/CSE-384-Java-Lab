//Order of constructor calls in multilevel inheritance in java


//: c07:Sandwich.java
// Order of constructor calls.
// package c07;
// import com.bruceeckel.simpletest.*;
//http://stackoverflow.com/questions/17806342/order-of-constructor-calls-in-multilevel-inheritance-in-java



import java.util.*;

class Meal {
  Meal() { System.out.println("Meal()"); }
}

class Bread {
  Bread() { System.out.println("Bread()"); }
}

class Cheese {
  Cheese() { System.out.println("Cheese()"); }
}

class Lettuce {
  Lettuce() { System.out.println("Lettuce()"); }
}

class Lunch extends Meal {
  Lunch() { System.out.println("Lunch()"); }
}

class PortableLunch extends Lunch {
  PortableLunch() { System.out.println("PortableLunch()");}
}

public class Sandwich {

  private Bread b = new Bread();
  private Cheese c = new Cheese();
  private Lettuce l = new Lettuce();
  
 public Sandwich() {
    System.out.println("Sandwich()");
  }
  
  public static void main(String[] args) {
    new Sandwich();
  
  }
} 