package com.epam.rd.autotasks;

import java.util.LinkedList;
import java.util.Queue;

public class CarouselRun {

    private final Queue<Integer> carouselQueue;
    private Integer currentElement;

    public CarouselRun(Queue<Integer> elements) {
        this.carouselQueue = new LinkedList<>(elements);
        this.currentElement = carouselQueue.poll();
    }

    public int next() {
        while (currentElement == null) {
            if (carouselQueue.isEmpty()) {
                return -1; // No more elements to process
            }
            currentElement = carouselQueue.poll();
        }
        int value = currentElement;
        currentElement--; // Decrement the current element
        if (currentElement > 0) {
            carouselQueue.add(currentElement); // Add the decremented element back to the queue
        }
        // Fetch the next element if available
        if (carouselQueue.isEmpty() && currentElement == null) {
            return -1;
        }
        currentElement = carouselQueue.poll();
        return value;
    }

    public boolean isFinished() {
        return carouselQueue.isEmpty() && currentElement == null;
    }
}
