package com.epam.rd.autotasks;

// TaskCarousel.java
public class TaskCarousel {
    private Task[] tasks; // Array to store tasks
    private int capacity; // Max number of tasks the carousel can hold
    private int taskCount = 0; // Current count of tasks in the carousel
    private int currentIndex = 0; // Index to track the next task to execute

    public TaskCarousel(int capacity) {
        this.capacity = capacity;
        this.tasks = new Task[capacity]; // Initialize task array with given capacity
    }

    public boolean isEmpty() {
        return taskCount == 0; // Carousel is empty if no tasks are present
    }

    public boolean isFull() {
        return taskCount == capacity; // Carousel is full if task count reaches capacity
    }

    public boolean addTask(Task task) {
        if (task == null || task.isFinished() || isFull()) {
            return false; // Reject adding null, finished tasks, or when full
        }
        tasks[taskCount++] = task; // Add task and increment task count
        return true; // Task successfully added
    }

    public boolean execute() {
        if (isEmpty()) {
            return false; // No task to execute if carousel is empty
        }

        // Find the next task to execute
        int initialIndex = currentIndex;
        do {
            Task currentTask = tasks[currentIndex];
            if (!currentTask.isFinished()) {
                currentTask.execute(); // Execute current task
                if (currentTask.isFinished()) {
                    removeTask(currentIndex); // Remove task if finished
                } else {
                    moveToNextTask(); // Move to next task if not finished
                }
                return true; // Successfully executed a task
            } else {
                removeTask(currentIndex); // Remove finished task
            }
        } while (currentIndex != initialIndex && !isEmpty());

        return false; // No task executed if all are finished
    }

    private void moveToNextTask() {
        currentIndex = (currentIndex + 1) % taskCount; // Circular move to next task
    }

    private void removeTask(int index) {
        // Shift tasks to fill the gap left by removed task
        for (int i = index; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[--taskCount] = null; // Decrease task count and clear last task slot

        if (currentIndex >= taskCount) {
            currentIndex = 0; // Reset index if it goes out of bounds
        }
    }
}

