package edu.epam.fop.lambdas;

public interface IntArrayReducers {

    // Sum of array values
    static IntArrayReducer SUMMARIZER = array -> {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    };

    // Product of array values
    static IntArrayReducer MULTIPLIER = array -> {
        int product = 1;
        for (int num : array) {
            product *= num;
        }
        return product;
    };

    // Minimum value in the array
    static IntArrayReducer MIN_FINDER = array -> {
        int min = array[0];
        for (int num : array) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    };

    // Maximum value in the array
    static IntArrayReducer MAX_FINDER = array -> {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    };

    // Average of array values, rounded mathematically
    // Average of array values, rounded mathematically
static IntArrayReducer AVERAGE_CALCULATOR = array -> {
  int sum = 0;
  for (int num : array) {
      sum += num;
  }
  // Cast to double to ensure division is floating-point, then round the result
  return (int) Math.round((double) sum / array.length);
};


    // Count of unique values in the array
    static IntArrayReducer UNIQUE_COUNTER = array -> {
        java.util.Set<Integer> uniqueValues = new java.util.HashSet<>();
        for (int num : array) {
            uniqueValues.add(num);
        }
        return uniqueValues.size();
    };

    // Sort direction of the array
    static IntArrayReducer SORT_DIRECTION_DEFINER = array -> {
        boolean ascending = true;
        boolean descending = true;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                descending = false;
            } else if (array[i] < array[i - 1]) {
                ascending = false;
            }
        }

        if (ascending && !descending) return 1;
        if (descending && !ascending) return -1;
        return 0;
    };
}
