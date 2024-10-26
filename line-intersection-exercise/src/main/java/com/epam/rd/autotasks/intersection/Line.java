package com.epam.rd.autotasks.intersection;

public class Line {
    private int k;
    private int b;

    public Line(int k, int b) {
        this.k = k;
        this.b = b;
    }

    public Point intersection(Line other) {
        //throw new UnsupportedOperationException();
        if (this.k == other.k) {
            if (this.b == other.b) {
                // Lines are coinciding
                return null;
            } else {
                // Lines are parallel and do not intersect
                return null;
            }
        }

        // Calculate intersection point
        int x = (other.b - this.b) / (this.k - other.k);
        int y = this.k * x + this.b;

        return new Point(x, y);
    }
    @Override
    public String toString() {
        return "y = " + k + "x + " + b;
    }

}
