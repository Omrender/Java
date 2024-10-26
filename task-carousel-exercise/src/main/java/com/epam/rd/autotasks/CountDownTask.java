package com.epam.rd.autotasks;

// CountDownTask.java
public class CountDownTask implements Task {
    private int value;

    public CountDownTask(int initialValue) {
        this.value = Math.max(initialValue, 0); // Initialize value, ensuring non-negative
    }

    @Override
    public void execute() {
        if (value > 0) {
            value--; // Decrement value if above zero
        }
    }

    @Override
    public boolean isFinished() {
        return value == 0; // Task is finished when value reaches zero
    }

    public int getValue() {
        return value; // Getter for the current countdown value
    }
}

