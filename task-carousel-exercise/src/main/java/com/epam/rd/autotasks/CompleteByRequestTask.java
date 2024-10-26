package com.epam.rd.autotasks;

// CompleteByRequestTask.java
public class CompleteByRequestTask implements Task {
    private boolean completeRequested = false; // Indicates if completion has been requested
    private boolean finished = false; // Indicates if the task is finished

    @Override
    public void execute() {
        if (completeRequested) {
            finished = true; // Mark task as finished after completion request
        }
    }

    @Override
    public boolean isFinished() {
        return finished; // Task is finished based on the finished flag
    }

    public void complete() {
        completeRequested = true; // Mark task for completion
    }
}

