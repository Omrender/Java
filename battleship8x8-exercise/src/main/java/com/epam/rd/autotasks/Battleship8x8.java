package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        // Convert the shot string (e.g., "A1", "B2") to a bit position
        int bitPosition = convertShotToBitPosition(shot);
        long shotBit = 1L << bitPosition;

        // Register the shot
        shots |= shotBit;

        // Check if the shot hits a ship and return the result
        return (ships & shotBit) != 0;
    }

    public String state() {
        StringBuilder map = new StringBuilder();

        // Loop through each cell of the 8x8 grid
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Calculate the bit position for the current cell
                int bitPosition = (7 - row) * 8 + (7 - col);
                long mask = 1L << bitPosition;

                // Determine the character to append based on the shots and ships bits
                if ((shots & mask) != 0) {
                    if ((ships & mask) != 0) {
                        map.append('☒');  // Ship present and hit
                    } else {
                        map.append('×');  // Shot that missed
                    }
                } else {
                    if ((ships & mask) != 0) {
                        map.append('☐');  // Ship present but not hit
                    } else {
                        map.append('.');  // Empty cell
                    }
                }
            }

            // Append a newline after each row except the last one
            if (row < 7) map.append('\n');
        }

        return map.toString();
    }

    private int convertShotToBitPosition(String shot) {
        // Convert column character to a number (A -> 0, B -> 1, ..., H -> 7)
        char column = shot.charAt(0);
        int colIndex = column - 'A';

        // Convert row character to a number (1 -> 7, 2 -> 6, ..., 8 -> 0)
        int rowIndex = 8 - Character.getNumericValue(shot.charAt(1));

        // Calculate the bit position
        return rowIndex * 8 + colIndex;
    }
}
