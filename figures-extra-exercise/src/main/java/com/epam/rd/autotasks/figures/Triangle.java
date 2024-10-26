package com.epam.rd.autotasks.figures;

// Triangle.java
import java.util.Arrays;

public class Triangle extends Figure {
    private Point a, b, c;

    public Triangle(Point a, Point b, Point c) {
        if (area(a, b, c) == 0) {
            throw new IllegalArgumentException("Degenerative triangle");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        return area(a, b, c);
    }

    private static double area(Point a, Point b, Point c) {
        return Math.abs(a.getX() * (b.getY() - c.getY()) +
                        b.getX() * (c.getY() - a.getY()) +
                        c.getX() * (a.getY() - b.getY())) / 2.0;
    }

    @Override
    public String pointsToString() {
        return a + "" + b + "" + c;
    }

    @Override
    public String toString() {
        return String.format("Triangle[%s]", pointsToString());
    }

    @Override
    public Point centroid() {
        double x = (a.getX() + b.getX() + c.getX()) / 3.0;
        double y = (a.getY() + b.getY() + c.getY()) / 3.0;
        return new Point(x, y);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Triangle)) {
            return false;
        }
        Triangle other = (Triangle) figure;
        Point[] thisPoints = {a, b, c};
        Point[] otherPoints = {other.a, other.b, other.c};
        Arrays.sort(thisPoints, (p1, p2) -> Double.compare(p1.getX(), p2.getX()));
        Arrays.sort(otherPoints, (p1, p2) -> Double.compare(p1.getX(), p2.getX()));
        return Arrays.equals(thisPoints, otherPoints);
    }
}
