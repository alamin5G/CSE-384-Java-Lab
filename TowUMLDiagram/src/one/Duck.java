/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package one;

/**
 *
 * @author alami
 */

public class Duck extends Animal{

    public String beakColor;

    public Duck(int age, String gender) {
        super(age, gender);
        beakColor = "yellow";
    }

    public void swim(){
        System.out.println("Swimming Duck");
    }

    public void quack(){
        System.out.println("Quacking Duck");
    }

    @Override
    public void mate(){
        System.out.println("Duck is looking for mate");
    }

}

