package com.epam.rd.autotasks.sequence;
import java.util.Scanner;

public class FindMaxInSeq {
    public static int max() {

        // Put your code here
        Scanner scanner = new Scanner(System.in);

        // Initialize max value to the lowest possible integer value
        int maxValue = Integer.MIN_VALUE;

        while (true) {
            int number = scanner.nextInt();
            
            // Check for the end of the sequence
            if (number == 0) {
                break;
            }

            // Update max value if the current number is greater
            if (number > maxValue) {
                maxValue = number;
            }
        }

        scanner.close(); // Close the scanner to avoid resource leaks
        return maxValue; // Return the maximum value found
    }

    public static void main(String[] args) {

       // System.out.println("Test your code here!\n");

        // Get a result of your code

        System.out.println(max());
    }
}
