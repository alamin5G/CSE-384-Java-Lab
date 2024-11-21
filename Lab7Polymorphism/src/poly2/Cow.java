/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly2;

/**
 *
 * @author alami
 */
// Subclass 2: Cow
class Cow extends Animal {
    public Cow(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says Moo!");
    }

    @Override
    public void describeEnclosure() {
        System.out.println(getName() + " enjoys a grassy pasture.");
    }

    @Override
    public void eat() {
      System.out.println(getName() + " eating... ");
    }

    @Override
    public void sleep() {
      System.out.println(getName() + " sleeping... ");
    }
}
