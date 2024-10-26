package com.epam.rd.autotasks.arrays;

public class SumOfEvenNumbers {

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 8, 15, 199};

        System.out.println(sum(array));
    }

    public static int sum(int[] values){

        //put your code here
        if (values == null || values.length == 0) {
            return 0;
        }

        // Initialize sum for even numbers
        int sum = 0;

        // Loop through the array to find and sum even numbers
        for (int value : values) {
            if (value % 2 == 0) { // Check if the number is even
                sum += value;
            }
        }

        // Return the sum of even numbers
        return sum;

        //throw new UnsupportedOperationException();
    }
}
