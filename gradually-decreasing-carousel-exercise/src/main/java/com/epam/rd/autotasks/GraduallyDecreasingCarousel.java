package com.epam.rd.autotasks;

import java.util.Arrays;

public class GraduallyDecreasingCarousel extends DecrementingCarousel {
    public GraduallyDecreasingCarousel(final int capacity) {
        super(capacity);
    }

    @Override
    public CarouselRun run() {
        if (!isRunning) {
            isRunning = true;
            return new GraduallyDecreasingCarouselRun(this);
        }
        return null;
    }
}

class GraduallyDecreasingCarouselRun extends CarouselRun {
    private int[] decrements; // Array to track decrement values for each element
    private int currentIndex = 0; // Index for iterating over elements

    public GraduallyDecreasingCarouselRun(DecrementingCarousel carousel) {
        super(carousel);
        decrements = new int[carousel.size];
        Arrays.fill(decrements, 1); // Start decrementing by 1
    }

    @Override
    public int next() {
        // Iterate through elements
        while (currentIndex < carousel.size) {
            if (carousel.elements[currentIndex] > 0) {
                int value = carousel.elements[currentIndex];
                // Decrement element by the current value in the decrements array
                carousel.elements[currentIndex] -= decrements[currentIndex];
                // Increment the decrement value for next time
                decrements[currentIndex]++;
                // If an element becomes non-positive, set it to 0
                if (carousel.elements[currentIndex] < 0) {
                    carousel.elements[currentIndex] = 0;
                }
                currentIndex++;
                return value;
            }
            currentIndex++;
        }
        resetIndex();
        return -1; // All elements are processed or none left to decrement
    }

    @Override
    public boolean isFinished() {
        // Check if all elements are fully decremented
        for (int element : carousel.elements) {
            if (element > 0) {
                return false;
            }
        }
        return true;
    }

    private void resetIndex() {
        currentIndex = 0;
    }
}
