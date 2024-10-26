package com.epam.rd.autotasks.pizzasplit;

import java.util.Scanner;

public class PizzaSplit {
    public static void main(String[] args) {
        //Write a program, reading number of people and number of pieces per pizza and then
        //printing minimum number of pizzas to order to split all the pizzas equally and with no remainder
        Scanner scanner = new Scanner(System.in);

        // Read number of people
        int n = scanner.nextInt();

        // Read number of pieces per pizza
        int p = scanner.nextInt();

        // Close the scanner to avoid resource leaks
        scanner.close();

        // Find the least common multiple (LCM) of n and p
        int lcm = lcm(n, p);

        // Calculate the number of pizzas needed
        int pizzasNeeded = lcm / p;

        // Print the result
        System.out.println(pizzasNeeded);

    }
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Function to calculate the Least Common Multiple (LCM)
    private static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}
