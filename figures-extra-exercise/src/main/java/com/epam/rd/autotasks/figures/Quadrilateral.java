package com.epam.rd.autotasks.figures;

// Quadrilateral.java
import java.util.Arrays;

public class Quadrilateral extends Figure {
    private Point a, b, c, d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (area() == 0) {
            throw new IllegalArgumentException("Degenerative quadrilateral");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double area() {
        Triangle t1 = new Triangle(a, b, c);
        Triangle t2 = new Triangle(a, c, d);
        return t1.area() + t2.area();
    }

    @Override
    public String pointsToString() {
        return a + "" + b + "" + c + "" + d;
    }

    @Override
    public String toString() {
        return String.format("Quadrilateral[%s]", pointsToString());
    }

    @Override
    public Point centroid() {
        double x = (a.getX() + b.getX() + c.getX() + d.getX()) / 4.0;
        double y = (a.getY() + b.getY() + c.getY() + d.getY()) / 4.0;
        return new Point(x, y);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Quadrilateral)) {
            return false;
        }
        Quadrilateral other = (Quadrilateral) figure;
        Point[] thisPoints = {a, b, c, d};
        Point[] otherPoints = {other.a, other.b, other.c, other.d};
        Arrays.sort(thisPoints, (p1, p2) -> Double.compare(p1.getX(), p2.getX()));
        Arrays.sort(otherPoints, (p1, p2) -> Double.compare(p1.getX(), p2.getX()));
        return Arrays.equals(thisPoints, otherPoints);
    }
}

