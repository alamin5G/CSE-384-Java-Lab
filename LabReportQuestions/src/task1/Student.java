
package task1;

/**
 *
 * @author alami
 */
public class Student extends Person
{
     int id;
     String standard;
     String instructor;
 
     Student(String fName, String lName, int nId, String stnd, String instr)
     {
          super(fName,lName);
          id = nId;
          standard = stnd;
          instructor = instr;         
      }
     void Display()
     {
            super.Display();

            System.out.println("ID : " + id);
            System.out.println("Standard : " + standard);
            System.out.println("Instructor : " + instructor);
     }
}