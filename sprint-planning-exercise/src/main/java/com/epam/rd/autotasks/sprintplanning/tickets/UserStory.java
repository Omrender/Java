package com.epam.rd.autotasks.sprintplanning.tickets;

// UserStory.java
public class UserStory extends Ticket {
    private UserStory[] dependencies;

    public UserStory(int id, String name, int estimate, UserStory[] dependencies) {
        super(id, name, estimate);
        if (dependencies != null) {
            this.dependencies = dependencies.clone();
        } else {
            this.dependencies = new UserStory[0]; // Initialize as empty array if null
        }
    }

    @Override
    public void complete() {
        for (UserStory dependency : dependencies) {
            if (!dependency.isCompleted()) {
                return; // Cannot complete as dependencies are not fulfilled
            }
        }
        super.complete(); // Call Ticket's complete method
    }

    public UserStory[] getDependencies() {
        return dependencies.clone(); // Defensive copy to avoid external modification
    }

    @Override
    public String toString() {
        return "[US " + getId() + "] " + getName();
    }
}

