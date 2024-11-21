/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly2;

/**
 *
 * @author alami
 */
class Pig extends Animal {
    public Pig(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " says Oink!");
    }

    @Override
    public void describeEnclosure() {
        System.out.println(getName() + " enjoys a muddy enclosure.");
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
