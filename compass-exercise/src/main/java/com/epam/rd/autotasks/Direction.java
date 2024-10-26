package com.epam.rd.autotasks;

import java.util.Optional;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    private final int degrees;

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    public static Direction ofDegrees(int degrees) {
        degrees = (degrees % 360 + 360) % 360; // Normalize degrees to the range [0, 360)
        for (Direction direction : Direction.values()) {
            if (direction.degrees == degrees) {
                return direction;
            }
        }
        return null; // Return null if no matching direction
    }

    public static Direction closestToDegrees(int degrees) {
        degrees = (degrees % 360 + 360) % 360; // Normalize degrees to the range [0, 360)
        Direction closest = null;
        int minDifference = Integer.MAX_VALUE;

        for (Direction direction : Direction.values()) {
            int difference = Math.abs(direction.degrees - degrees);
            if (difference > 180) {
                difference = 360 - difference; // Find the shortest path in circular degrees
            }
            if (difference < minDifference) {
                minDifference = difference;
                closest = direction;
            }
        }
        return closest;
    }

    public Direction opposite() {
        int oppositeDegrees = (this.degrees + 180) % 360; // Calculate opposite direction degrees
        return closestToDegrees(oppositeDegrees);
    }

    public int differenceDegreesTo(Direction direction) {
        int difference = Math.abs(this.degrees - direction.degrees);
        return Math.min(difference, 360 - difference); // Smallest difference between two directions
    }
}
