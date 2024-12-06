/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fourteen;

/**
 *
 * @author alami
 */
public class SharedCounter {
    // Shared counter
    private static int counter = 0;

    public static void main(String[] args) {
        // Create an array to hold threads
        Thread[] threads = new Thread[10];

        // Create 10 threads that increment the counter
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    int temp = counter; // Read the value of the counter
                    Thread.yield();    // Encourage thread switch to create interference
                    counter = temp + 1; // Increment and store
                }
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the final value of the counter
        System.out.println("Final Counter Value: " + counter);
    }
}

