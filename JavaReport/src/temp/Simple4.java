/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package temp;

/**
 *
 * @author Student
 */
class Simple4{
	
	public static void main (String args[]){
		int LIMIT=1;
		try{	
			int a[]=new int [5];
			a[4]=50/LIMIT;
		}catch(ArithmeticException e){System.out.println("Task1 is completed"); }
		 catch(ArrayIndexOutOfBoundsException e){System.out.println("Task2 is completed"); }
		 catch(Exception e){System.out.println("Common Task Completed"); }
		 finally {
				System.out.println(" Rest Of the code");
		 }
	}
} 
