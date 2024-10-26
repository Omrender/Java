package com.epam.rd.autotasks;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalSum = 0; // To store the sum of the numbers
        int count = 0;    // To count the number of numbers

        while (true) {
            int number = scanner.nextInt();

            // Check for the end of the sequence
            if (number == 0) {
                break;
            }

            totalSum += number; // Add the number to the total sum
            count++;            // Increment the count
        }

        // Close the scanner to avoid resource leaks
        scanner.close();

        // Calculate and print the average
        if (count > 0) {
            System.out.println(totalSum / count);
        } else {
            System.out.println(0); // Handle the edge case if no numbers are entered
        }

    }

}