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

    private static int counter = 0;  // Shared counter (not thread-safe)

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];

        // Create 10 threads
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    incrementCounter();  // Increment the counter
                    try {
                        // Randomly yield or sleep to simulate thread scheduling issues
                        Thread.sleep(0);  // Small sleep time to force context switching
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
        System.out.println("Final value of counter: " + counter);
    }

    // Non-atomic increment method that may cause race conditions
    private synchronized static void incrementCounter() {
        counter++;  // Increment the counter
    }
}
