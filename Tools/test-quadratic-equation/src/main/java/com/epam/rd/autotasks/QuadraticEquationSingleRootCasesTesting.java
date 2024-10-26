package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {

    private final double a;
    private final double b;
    private final double c;
    private final double expected; 
    //public QuadraticEquation quadraticEquation = new QuadraticEquation();
    protected QuadraticEquation quadraticEquation;

    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected=expected;
        this.quadraticEquation = new QuadraticEquation();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {1, -2, 1},  // one root
            {4, -4, 1},  // one root
            {9, -6, 1}   // one root
        });
    }

    @Test
    public void testSingleRoot() {
        String result = quadraticEquation.solve(a, b, c);
        assertEquals("one root expected", result);
    }
}
