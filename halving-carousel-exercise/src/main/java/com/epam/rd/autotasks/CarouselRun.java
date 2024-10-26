package com.epam.rd.autotasks;

import java.util.LinkedList;
import java.util.Queue;

public class CarouselRun {
    protected final Queue<Integer> carouselQueue;
    protected Integer currentElement;

    public CarouselRun(Queue<Integer> elements) {
        this.carouselQueue = new LinkedList<>(elements);
        this.currentElement = this.carouselQueue.poll();
    }

    public int next() {
        while (currentElement != null && currentElement <= 0) {
            currentElement = carouselQueue.poll();
        }
        
        if (currentElement == null) {
            return -1;
        }
        
        int returnValue = currentElement;
        currentElement--;

        if (currentElement > 0) {
            carouselQueue.add(currentElement);
        }

        currentElement = carouselQueue.poll();
        return returnValue;
    }

    public boolean isFinished() {
        return carouselQueue.isEmpty() && (currentElement == null || currentElement <= 0);
    }
}
