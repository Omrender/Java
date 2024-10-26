package com.epam.rd.autocode.queue;

import java.util.Deque;
import java.util.LinkedList;

public class CashBox {

    private final int number;
    private final Deque<Buyer> buyers;
    private State state;

    public enum State {
        ENABLED, DISABLED, IS_CLOSING
    }

    public CashBox(int number) {
        this.number = number;
        this.buyers = new LinkedList<>();
        this.state = State.DISABLED;
    }

    // Return a copy of the queue to prevent external modification
    public Deque<Buyer> getQueue() {
        return new LinkedList<>(buyers);
    }

    // Serve the first buyer in the queue, transition state if necessary
    public Buyer serveBuyer() {
        if (!buyers.isEmpty()) {
            Buyer servedBuyer = buyers.pollFirst();
            if (buyers.isEmpty() && state == State.IS_CLOSING) {
                state = State.DISABLED;
            }
            return servedBuyer;
        }
        return null;
    }

    // Check if the cashbox is in a given state
    public boolean inState(State state) {
        return this.state == state;
    }

    // Check if the cashbox is not in a given state
    public boolean notInState(State state) {
        return this.state != state;
    }

    // Set the state of the cashbox
    public void setState(State state) {
        this.state = state;
    }

    // Get the current state of the cashbox
    public State getState() {
        return state;
    }

    // Add a buyer to the end of the queue if the cashbox is enabled
    public void addLast(Buyer buyer) {
        if (state == State.ENABLED) {
            buyers.addLast(buyer);
        }
    }

    // Remove the last buyer in the queue and return them
    public Buyer removeLast() {
        return buyers.isEmpty() ? null : buyers.pollLast();
    }

    // String representation of the cashbox including state and queue
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("#" + number + "[" + stateSymbol() + "] ");
        buyers.forEach(buyer -> sb.append(buyer.toString()));
        return sb.toString();
    }

    // Map the state to a specific symbol
    private char stateSymbol() {
        switch (state) {
            case ENABLED:
                return '+';
            case IS_CLOSING:
                return '|';
            case DISABLED:
            default:
                return '-';
        }
    }
}
