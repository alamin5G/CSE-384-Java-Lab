/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eight;

/**
 *
 * @author alami
 */

import java.util.Scanner;

class ATM {
    private double balance = 10000; // Initial balance

    // Withdraw money
    public void withdraw(double amount) {
        if (amount % 500 != 0) {
            System.out.println("Amount must be divisible by 500.");
        } else if (amount > balance - 500) {
            System.out.println("Insufficient funds. Maintain minimum balance of 500.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful! Remaining balance: " + balance);
        }
    }

    // Check balance
    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM();

        while (true) {
            System.out.print("\n1. Withdraw\n2. Check Balance\n3. Exit\nChoose: ");
            switch (sc.nextInt()) {
                case 1:
                    System.out.print("Enter amount: ");
                    atm.withdraw(sc.nextDouble());
                    break;
                case 2:
                    atm.checkBalance();
                    break;
                case 3:
                    System.out.println("Thank you. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
