/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapping;

/**
 *
 * @author ROMA
 */
public class Wrapper {
    public static void main(String[] args)
    {
        byte b = 10;             //Primitive byte data
        Byte B = new Byte(b);   //Wrapping primitive byte data into Byte Object
 
        short s = 15;              //Primitive short data
        Short S = new Short(s);   //Wrapping of primitive short data into Short Object
 
        int i = 20;                   //Primitive int Data
        Integer I = new Integer(i);   //Wrapping of primitive int data into Integer Object
 
        long l = 25;            //Primitive long data
        Long L = new Long(l);   //Wrapping primitive long data into Long Object
 
        float f = 12;            //Primitive float data
        Float F = new Float(f);  //Wrapping primitive float data into Float Object
 
        double d = 18.58;           //Primitive double data
        Double D = new Double(d);   //Wrapping primitive double data into Double Object
 
        boolean bln = true;               //Primitive boolean data
        Boolean BLN = new Boolean(bln);   //Wrapping primitive boolean data into Boolean Object
 
        char c = 'C';                     //Primitive char data
        Character C = new Character(c);   //Wrapping primitive char data into Character Object
    }
    
}
