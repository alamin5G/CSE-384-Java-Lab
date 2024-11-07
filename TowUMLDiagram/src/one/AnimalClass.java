
package one;

/**
 *
 * @author alami
 */


public class AnimalClass {

    public static void main(String[] args) {
        Duck duck = new Duck(5, "M");
        System.out.println("duck is mammal? : " + duck.isMammal());;
        duck.mate();
        duck.swim();
        duck.quack();
        System.out.println("Duck age : " + duck.age + ", Duck gender: " + duck.gender) ;
        System.out.println("===================================");
        Fish fish = new Fish(5, "F", 2, true);
        System.out.println("fish is mammal? : " + fish.isMammal());;
        fish.mate();
        System.out.println("===================================");
        Zebra zebra = new Zebra(10, "M", true);
        zebra.run();
        System.out.println("Zebra is mammal? : " + zebra.isMammal());;
        zebra.mate();
        System.out.println("Zebra is wild? : " + zebra.is_wild);

    }
}

