package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialRegularInputTesting {

    Factorial factorial = new Factorial();

    @Test
    void testZeroInput() {
        assertEquals("1", factorial.factorial("0"));
    }

    @Test
    void testSmallInput() {
        assertEquals("6", factorial.factorial("3")); // 3! = 6
    }

    @Test
    void testLargerInput() {
        assertEquals("40320", factorial.factorial("8")); // 8! = 40320
    }
}
