package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

// Sprint.java
public class Sprint {
    private int timeCapacity;
    private int ticketLimit;
    private Ticket[] tickets;
    private int ticketCount;
    private int totalEstimate;

    public Sprint(int timeCapacity, int ticketLimit) {
        this.timeCapacity = timeCapacity;
        this.ticketLimit = ticketLimit;
        this.tickets = new Ticket[ticketLimit];
        this.ticketCount = 0;
        this.totalEstimate = 0;
    }

    public boolean addUserStory(UserStory userStory) {
        if (userStory == null || userStory.isCompleted() || userStory.getEstimate() > (timeCapacity - totalEstimate)) {
            return false; // Null, completed, or exceeds capacity
        }
        // Check if all dependencies are already accepted in the sprint
        for (UserStory dependency : userStory.getDependencies()) {
            boolean dependencyAccepted = false;
            for (int i = 0; i < ticketCount; i++) {
                if (tickets[i] instanceof UserStory && tickets[i].getId() == dependency.getId()) {
                    dependencyAccepted = true;
                    break;
                }
            }
            if (!dependencyAccepted) {
                return false; // If any dependency is not accepted, return false
            }
        }
        return addTicket(userStory);
    }

    public boolean addBug(Bug bugReport) {
        if (bugReport == null || bugReport.isCompleted() || bugReport.getEstimate() > (timeCapacity - totalEstimate)) {
            return false; // Null, completed, or exceeds capacity
        }
        return addTicket(bugReport);
    }

    private boolean addTicket(Ticket ticket) {
        if (ticketCount >= ticketLimit) {
            return false; // Exceeds ticket limit
        }
        tickets[ticketCount++] = ticket;
        totalEstimate += ticket.getEstimate();
        return true;
    }

    public Ticket[] getTickets() {
        Ticket[] copy = new Ticket[ticketCount];
        for (int i = 0; i < ticketCount; i++) {
            copy[i] = tickets[i];
        }
        return copy; // Return a copy to prevent external modification
    }

    public int getTotalEstimate() {
        return totalEstimate;
    }
}

