package com.epam.rd.autotasks.max;

public class MaxMethod {
    public static int max(int[] values) {
      //  throw new UnsupportedOperationException();
        int maxValue = values[0];

        // Loop through the array starting from the second element
        for (int i = 1; i < values.length; i++) {
            // Update maxValue if the current element is greater
            if (values[i] > maxValue) {
                maxValue = values[i];
            }
        }

        // Return the maximum value found
        return maxValue;
    }
    public static void main(String[] args) {
        int[] vals = new int[]{ -2, 0, 10, 5 };
        int result = MaxMethod.max(vals);
        System.out.println(result == 10); // true
    }
}
