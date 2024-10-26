package com.epam.rd.autotasks;

import java.util.LinkedList;
import java.util.Queue;

public class DecrementingCarousel {

    private final int capacity;
    private final Queue<Integer> elements;
    private boolean isRunning;
    private CarouselRun carouselRun;

    public DecrementingCarousel(int capacity) {
        this.capacity = capacity;
        this.elements = new LinkedList<>();
        this.isRunning = false;
        this.carouselRun = null;
    }

    public boolean addElement(int element) {
        if (isRunning || element <= 0 || elements.size() >= capacity) {
            return false;
        }
        return elements.add(element);
    }

    public CarouselRun run() {
        if (isRunning) {
            return null;
        }
        isRunning = true;
        carouselRun = new CarouselRun(elements);
        return carouselRun;
    }
}
