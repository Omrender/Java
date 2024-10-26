package com.epam.rd.autotasks;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        int[][] result = new int[rows][columns];
        
        int value = 1;
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = columns - 1;
        
        while (top <= bottom && left <= right) {
            // Fill the top row
            for (int i = left; i <= right; i++) {
                result[top][i] = value++;
            }
            top++;
            
            // Fill the right column
            for (int i = top; i <= bottom; i++) {
                result[i][right] = value++;
            }
            right--;
            
            // Fill the bottom row
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result[bottom][i] = value++;
                }
                bottom--;
            }
            
            // Fill the left column
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[i][left] = value++;
                }
                left++;
            }
        }
        
        return result;
    }
    public static void main(String[] args) {
        int[][] spiral1 = spiral(3, 4);
        printSpiral(spiral1);

        int[][] spiral2 = spiral(4, 3);
        printSpiral(spiral2);

        int[][] spiral3 = spiral(5, 6);
        printSpiral(spiral3);

        int[][] spiral4 = spiral(5, 5);
        printSpiral(spiral4);
    }

    private static void printSpiral(int[][] spiral) {
        for (int[] row : spiral) {
            for (int num : row) {
                System.out.print(String.format("%4s", num));
            }
            System.out.println();
        }
    }
}
