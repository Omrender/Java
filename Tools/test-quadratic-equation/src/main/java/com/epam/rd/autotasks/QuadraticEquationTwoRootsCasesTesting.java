package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {

    private final double a;
    private final double b;
    private final double c;
    private final String expected;
    protected QuadraticEquation quadraticEquation; // Keep it final

    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c,String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected=expected;
        this.quadraticEquation = createQuadraticEquation(); // Use factory method
    }

    protected QuadraticEquation createQuadraticEquation() {
        return new QuadraticEquation(); // Default implementation
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {1, -5, 6},    // two roots
            {1, 0, -1},    // two roots
            {2, -8, 6}     // two roots
        });
    }

    @Test
    public void testTwoRoots() {
        String result = quadraticEquation.solve(a, b, c);
        assertEquals("two roots expected", result);
    }
}
