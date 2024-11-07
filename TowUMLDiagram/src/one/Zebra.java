/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package one;

/**
 *
 * @author alami
 */

public class Zebra extends Animal {

    public boolean is_wild;

    public Zebra(int age, String gender, boolean is_wild) {
        super(age, gender);
        this.is_wild = is_wild;
    }

    public void run(){
        System.out.println("Zebra running");
    }

    @Override
    public boolean isMammal(){
        return true;
    }
}
