/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vector;

//import java.util.Enumeration;
//import java.util.Vector;
import java.lang.*;
import java.io.*;
import java.util.*;
public class vec1 
{
     /*public static void main(String a[]){
        Vector<String> vct = new Vector<String>();
        //adding elements to the end
        vct.add("First");
        vct.add("Second");
        vct.add("Third");
        vct.add("Random");
        Enumeration<String> enm = vct.elements();
        while(enm.hasMoreElements()){
            System.out.println(enm.nextElement());
        }
    }*/
    
    
    
      public static void main(String a[])
      {
        Vector<String> vct = new Vector<String>();
        //adding elements to the end
        vct.add("First");
        vct.add("Second");
        vct.add("Third");
        System.out.println(vct);
        //adding element at specified index
        vct.add(2,"Random");
        System.out.println(vct);
        //getting elements by index
        System.out.println("Element at index 3 is: "+vct.get(3));
        //getting first element
        System.out.println("The first element of this vector is: "+vct.firstElement());
        //getting last element
        System.out.println("The last element of this vector is: "+vct.lastElement());
        //how to check vector is empty or not
        System.out.println("Is this vector empty? "+vct.isEmpty());
      }
   
    
      
    /*public static void main(String args[])
    { 
    // initial size is 3, increment is 2 
    Vector v = new Vector(3, 2); 
    System.out.println("Initial size: " + v.size()); 
    System.out.println("Initial capacity: " + v.capacity()); 
    v.addElement(new Integer(1)); 
    v.addElement(new Integer(2)); 
    v.addElement(new Integer(3)); 
    v.addElement(new Integer(4)); 
    System.out.println("Capacity after four additions: " + v.capacity()); 
    v.addElement(new Double(5.45)); 
    System.out.println("Current capacity: " +  v.capacity()); 
    v.addElement(new Double(6.08)); 
    v.addElement(new Integer(7)); 
    System.out.println("Current capacity: " + v.capacity()); 
    v.addElement(new Float(9.4)); 
    v.addElement(new Integer(10)); 
    System.out.println("Current capacity: " + v.capacity()); 
    v.addElement(new Integer(11)); 
    v.addElement(new Integer(12)); 
    System.out.println("First element: " + (Integer)v.firstElement()); 
    System.out.println("Last element: " + (Integer)v.lastElement()); 
    if(v.contains(new Integer(3))) 
    System.out.println("Vector contains 3."); 
    // enumerate the elements in the vector. 
    Enumeration vEnum = v.elements(); 
    System.out.println("\\nElements in vector:"); 
    while(vEnum.hasMoreElements()) 
    System.out.print(vEnum.nextElement() + " "); 
    System.out.println(); 
    }*/
    
    /*public static void main(String arggs[])
	{
                String args[]={"A","B","C","S"};
		Vector list = new Vector();
		int len=args.length;
		for(int i=0;i<len;i++)
		{
			list.addElement(args[i]);
		}
		int size=list.size();
		String str[]= new String[size];
		list.copyInto(str);
		for(int i=0;i<size;i++)
		{
			System.out.println ("Element of Vector at position "+i+":"+str[i]);
                    
		}
	}*/
}
    
