package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class QuadraticEquationZeroACasesTesting {

    private final double a;
    private final double b;
    private final double c;
    public QuadraticEquation quadraticEquation = new QuadraticEquation();
    //protected final QuadraticEquation quadraticEquation;

    public QuadraticEquationZeroACasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        //this.quadraticEquation = new QuadraticEquation();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {0, 2, 1},
            {0, -5, 6},
            {0, 1, 1},
            {0, 0, 0}
        });
    }

    @Test
    public void testZeroAThrowsException() {
        try {
            quadraticEquation.solve(a, b, c);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Exception was thrown as expected
        }
    }
}
