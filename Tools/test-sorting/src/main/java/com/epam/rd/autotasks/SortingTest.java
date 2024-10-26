package com.epam.rd.autotasks;

import org.junit.Test;
import static org.junit.Assert.*;

public class SortingTest {

    Sorting sorting = new Sorting();

    // Test null case, expecting an exception
    @Test(expected = IllegalArgumentException.class)
    public void testNullCase() {
        sorting.sort(null);
    }

    // Test an empty array
    @Test
    public void testEmptyCase() {
        int[] array = {};
        sorting.sort(array);
        assertArrayEquals(new int[]{}, array);
    }

    // Test an array with a single element
    @Test
    public void testSingleElementArrayCase() {
        int[] array = {5};
        sorting.sort(array);
        assertArrayEquals(new int[]{5}, array);
    }

    // Test an already sorted array
    @Test
    public void testSortedArraysCase() {
        int[] array = {1, 2, 3, 4, 5};
        sorting.sort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    // Test an unsorted array with random numbers
    @Test
    public void testOtherCases() {
        int[] array = {4, 1, 3, 5, 2};
        sorting.sort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }
}
