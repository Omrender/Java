package com.epam.rd.autotasks;

import java.math.BigInteger;

public class Factorial {
    public String factorial(String n) {
        if (n == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        try {
            int num = Integer.parseInt(n);
            if (num < 0) {
                throw new IllegalArgumentException("Input must be a non-negative integer");
            }

            BigInteger result = BigInteger.ONE;
            for (int i = 2; i <= num; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }

            return result.toString();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a non-negative integer");
        }
    }
}
