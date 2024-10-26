package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationNoRootsCasesTesting {

    private final double a;
    private final double b;
    private final double c;
    public QuadraticEquation quadraticEquation = new QuadraticEquation(); // Initialize here

    public QuadraticEquationNoRootsCasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {1, 2, 5},    // no roots
            {2, 4, 8},    // no roots
            {3, 1, 4}     // no roots
        });
    }

    @Test
    public void testNoRoots() {
        String result = quadraticEquation.solve(a, b, c);
        assertEquals("no roots", result);
    }
}
