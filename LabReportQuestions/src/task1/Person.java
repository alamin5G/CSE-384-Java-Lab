package task1;

/**
 *
 * @author alami
 */
public class Person {

    String FirstName;
    String LastName;

    Person(String fName, String lName) {
        FirstName = fName;
        LastName = lName;
    }

    void Display() {
        System.out.println("First Name : " + FirstName);
        System.out.println("Last Name : " + LastName);
    }

}
