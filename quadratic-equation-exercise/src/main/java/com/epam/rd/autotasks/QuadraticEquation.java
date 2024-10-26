package com.epam.rd.autotasks;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read coefficients a, b, c
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        
        // Close the scanner to avoid resource leaks
        scanner.close();
        
        // Calculate the discriminant
        double discriminant = b * b - 4 * a * c;
        
        if (discriminant > 0) {
            // Two distinct real roots
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println(x1 + " " + x2);
        } else if (discriminant == 0) {
            // One real root
            double x = -b / (2 * a);
            System.out.println(x);
        } else {
            // No real roots
            System.out.println("no roots");
        }



    }

}