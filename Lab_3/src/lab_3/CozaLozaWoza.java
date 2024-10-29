package lab_3;

/**
 *
 * @author alami
 */
public class CozaLozaWoza {

    public static void main(String[] args) {
        boolean flag;
        for (int i = 1; i <= 110; i++) {
            flag = false;
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.print("CozaLoza ");
            } else if (i % 3 == 0 && i % 7 == 0) {
                System.out.print("CozaWoza ");
            } else if (i % 7 == 0) {
                System.out.print("Woza ");
            } else if (i % 3 == 0) {
                System.out.print("Coza ");
            } else if (i % 5 == 0) {
                System.out.print("Loza ");
            } else {
                if (i % 11 != 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.println(i + "");
                    flag = true;
                }
            }

            if (!flag && i % 11 == 0) {
                System.out.println();
            }

        }
    }
}
