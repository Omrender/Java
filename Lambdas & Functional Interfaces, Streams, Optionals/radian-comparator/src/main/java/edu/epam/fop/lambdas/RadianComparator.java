package edu.epam.fop.lambdas;

import java.util.Comparator;

public final class RadianComparator {

    private static final double TWO_PI = 2 * Math.PI;
    private static final double PRECISION = 0.001;

    /**
     * Normalizes the angle to the range [0, 2π).
     *
     * @param angle the angle in radians
     * @return the normalized angle
     */
    private static double normalize(double angle) {
        // Normalize angle to [0, 2π) range
        return ((angle % TWO_PI) + TWO_PI) % TWO_PI;
    }

    /**
     * Creates a comparator for comparing radians with precision of 0.001.
     *
     * @return the comparator
     */
    public static Comparator<Double> get() {
        return new Comparator<Double>() {
            @Override
            public int compare(Double a, Double b) {
                if (a == null && b == null) {
                    return 0;
                } else if (a == null) {
                    return -1;
                } else if (b == null) {
                    return 1;
                } else {
                    double normalizedA = normalize(a);
                    double normalizedB = normalize(b);

                    // Compare with precision
                    if (Math.abs(normalizedA - normalizedB) < PRECISION) {
                        return 0;
                    } else if (normalizedA < normalizedB) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        };
    }
}
