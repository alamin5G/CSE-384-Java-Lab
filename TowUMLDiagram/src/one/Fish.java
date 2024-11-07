/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package one;

/**
 *
 * @author alami
 */

public class Fish extends Animal{

    private int sizeInFt;
    private boolean canEat;


    public Fish(int age, String gender, int sizeInFt, boolean canEat) {
        super(age, gender);
        this.sizeInFt = sizeInFt;
        this.canEat = canEat;
        swim();
    }

    private void swim(){
        System.out.println("fish swimming");
    }
}

