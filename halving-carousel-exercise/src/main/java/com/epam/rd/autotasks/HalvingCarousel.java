package com.epam.rd.autotasks;

import java.util.LinkedList;
import java.util.Queue;

public class HalvingCarousel extends DecrementingCarousel {

    public HalvingCarousel(final int capacity) {
        super(capacity);
    }

    @Override
    public CarouselRun run() {
        if (isRunning()) {
            return null; // Carousel is already running
        }
        setRunning(true);
        return new HalvingCarouselRun(getElements());
    }

    // Custom CarouselRun for HalvingCarousel
    private static class HalvingCarouselRun extends CarouselRun {

        public HalvingCarouselRun(Queue<Integer> elements) {
            super(elements);
        }

        @Override
        public int next() {
            while (currentElement != null && currentElement <= 0) {
                currentElement = carouselQueue.poll();
            }

            if (currentElement == null) {
                return -1;
            }

            int returnValue = currentElement;
            currentElement /= 2;

            if (currentElement > 0) {
                carouselQueue.add(currentElement);
            }

            currentElement = carouselQueue.poll();
            return returnValue;
        }
    }
}
