package com.epam.rd.autotasks;

import java.util.LinkedList;
import java.util.Queue;

public class DecrementingCarousel {
    private final int capacity;
    private final Queue<Integer> elements;
    private boolean running;

    public DecrementingCarousel(int capacity) {
        this.capacity = capacity;
        this.elements = new LinkedList<>();
        this.running = false;
    }

    public boolean addElement(int element) {
        if (element > 0 && elements.size() < capacity && !running) {
            elements.add(element);
            return true;
        }
        return false;
    }

    public CarouselRun run() {
        if (running) {
            return null; // Carousel is already running
        }
        running = true;
        return new CarouselRun(elements);
    }

    protected Queue<Integer> getElements() {
        return elements;
    }
    
    protected boolean isRunning() {
        return running;
    }
    
    protected void setRunning(boolean running) {
        this.running = running;
    }
}
