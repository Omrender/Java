package com.epam.rd.autotasks;

import java.util.Arrays;

class CycleSwap {
    static void cycleSwap(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int lastElement = array[array.length - 1];

        for (int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = lastElement;
    }

    static void cycleSwap(int[] array, int shift) {
        if (array == null || array.length <= 1 || shift <= 0) {
            return;
        }

        int n = array.length;
        shift = shift % n;

        if (shift == 0) {
            return;
        }

        int[] temp = Arrays.copyOf(array, n);

        for (int i = 0; i < n; i++) {
            array[(i + shift) % n] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 3, 2, 7, 4};
        CycleSwap.cycleSwap(array1);
        System.out.println(Arrays.toString(array1)); // Output: [4, 1, 3, 2, 7]

        int[] array2 = new int[]{1, 3, 2, 7, 4};
        CycleSwap.cycleSwap(array2, 2);
        System.out.println(Arrays.toString(array2)); // Output: [7, 4, 1, 3, 2]
    }
}
