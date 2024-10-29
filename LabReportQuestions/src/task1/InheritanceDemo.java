package task1;

/**
 *
 * @author alami
 */
public class InheritanceDemo
{
       public static void main(String args[])
       {
               Person pObj = new Person("Alamin","Rakib");
               Student sObj = new Student("Arafat","Muhammad",1,"1 - B","Rabeya");
               Teacher tObj = new Teacher("Nuha","Akter","Arabic", 5000,"Islamic Teacher");
               System.out.println("Person :");
               pObj.Display();
               System.out.println("");
               System.out.println("Student :");
               sObj.Display();
               System.out.println("");
               System.out.println("Teacher :");
               tObj.Display();
        }
}

