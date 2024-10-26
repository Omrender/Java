package com.epam.rd.autotasks;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("a cannot be zero.");
        }

        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return x1 + " " + x2;
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            return String.valueOf(x);
        } else {
            return "no roots";
        }
    }
}
