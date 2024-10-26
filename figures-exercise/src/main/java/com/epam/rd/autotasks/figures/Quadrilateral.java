package com.epam.rd.autotasks.figures;

// Quadrilateral.java
public class Quadrilateral extends Figure {
    private Point a, b, c, d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
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
    public Point leftmostPoint() {
        return (a.getX() <= b.getX() && a.getX() <= c.getX() && a.getX() <= d.getX()) ? a :
               (b.getX() <= c.getX() && b.getX() <= d.getX()) ? b :
               (c.getX() <= d.getX()) ? c : d;
    }
}

