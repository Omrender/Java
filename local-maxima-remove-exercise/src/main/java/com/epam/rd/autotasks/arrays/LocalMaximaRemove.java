package com.epam.rd.autotasks.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocalMaximaRemove {

    public static void main(String[] args) {
        int[] array = new int[]{18, 1, 3, 6, 7, -5};

        System.out.println(Arrays.toString(removeLocalMaxima(array)));
    }

    public static int[] removeLocalMaxima(int[] array){

        //put your code here

       // throw new UnsupportedOperationException();
        List<Integer> resultList = new ArrayList<>();

        // Iterate through the array
        for (int i = 0; i < array.length; i++) {
            // First element is never a local maximum
            if (i == 0) {
                if (array[i] <= array[i + 1]) {
                    resultList.add(array[i]);
                }
            } 
            // Last element is never a local maximum
            else if (i == array.length - 1) {
                if (array[i] <= array[i - 1]) {
                    resultList.add(array[i]);
                }
            } 
            // Middle elements can be local maxima
            else {
                if (array[i] <= array[i - 1] || array[i] <= array[i + 1]) {
                    resultList.add(array[i]);
                }
            }
        }

        // Convert the list back to an array
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }

        return resultArray;

    }
}
