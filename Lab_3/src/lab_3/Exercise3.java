
package lab_3;

/**
 *
 * @author alami
 */
public class Exercise3 {
    
    public static void main(String[] args) {
        
        // Auto-boxing
        Integer intValue = 42;
        Double doubleValue = 42.42;
        Character charValue = 'A';
        Boolean boolValue = true;

        // Unboxing
        int primitiveInt = intValue;
        double primitiveDouble = doubleValue;
        char primitiveChar = charValue;
        boolean primitiveBool = boolValue;

        // Using methods from wrapper classes
        Integer intFromString = Integer.valueOf("123");
        Double doubleFromString = Double.valueOf("123.45");
        Character charFromString = Character.valueOf('C');
        Boolean boolFromString = Boolean.valueOf("true");

        int parsedInt = Integer.parseInt("456");
        double parsedDouble = Double.parseDouble("456.78");
        
        String intString = intValue.toString();
        String doubleString = doubleValue.toString();
        String charString = charValue.toString();
        String boolString = boolValue.toString();

        // Printing results
        System.out.println("intValue (Integer): " + intValue);
        System.out.println("doubleValue (Double): " + doubleValue);
        System.out.println("charValue (Character): " + charValue);
        System.out.println("boolValue (Boolean): " + boolValue);

        System.out.println("primitiveInt (int): " + primitiveInt);
        System.out.println("primitiveDouble (double): " + primitiveDouble);
        System.out.println("primitiveChar (char): " + primitiveChar);
        System.out.println("primitiveBool (boolean): " + primitiveBool);

        System.out.println("intFromString (Integer.valueOf): " + intFromString);
        System.out.println("doubleFromString (Double.valueOf): " + doubleFromString);
        System.out.println("charFromString (Character.valueOf): " + charFromString);
        System.out.println("boolFromString (Boolean.valueOf): " + boolFromString);

        System.out.println("parsedInt (Integer.parseInt): " + parsedInt);
        System.out.println("parsedDouble (Double.parseDouble): " + parsedDouble);

        System.out.println("intString (toString): " + intString);
        System.out.println("doubleString (toString): " + doubleString);
        System.out.println("charString (toString): " + charString);
        System.out.println("boolString (toString): " + boolString);
        
    }
    
}
