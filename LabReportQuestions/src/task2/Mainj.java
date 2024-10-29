package task2;

/**
 *
 * @author alami
 */
// A Simple Java program to demonstrate 
// Overriding and Access-Modifiers 
  
class Parent { 
    // private methods are not overridden 
  
    String name;
  
    Parent(String name){
  	    this.name=name;
  	}
    private void m1() 
    { 
        System.out.println("From parent m1()"); 
    } 
  
    protected void m2() 
    { 
        System.out.println("From parent m2()"); 
    } 
    public String toString(){//overriding the toString() method  
  		return "Class name Parent, Parent name= "+name+"\n";  
 	}  	
} 
  
class Child extends Parent { 
    // new m1() method 
    // unique to Child class 
    int id;

	Child(String name, int id)
	{
		super(name);
		this.id=id;
	}
   
    private void m1() 
    { 
        System.out.println("From child m1()"); 
    } 
  
    // overriding method 
    // with more accessibility 
    @Override
    public void m2() 
    { 
        System.out.println("From child m2()"); 
    } 
    public String toString(){//overriding the toString() method  
  		return "Class name child, child name= "+name+"\n";  
 	}  	
} 
  
// Driver class 
class Mainj { 
    public static void main(String[] args) 
    { 
        Parent obj1 = new Parent("Shamim"); 
        obj1.m2(); 
        System.out.println(	obj1.toString());
        Parent obj2 = new Child("Saifan", 2); 
        obj2.m2(); 
        System.out.println(	obj2.toString());
        	
    } 
} 