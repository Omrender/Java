package com.epam.rd.autotasks.figures;

// Triangle.java
public class Triangle extends Figure {
    private Point a, b, c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
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
    public Point leftmostPoint() {
        return (a.getX() <= b.getX() && a.getX() <= c.getX()) ? a :
               (b.getX() <= c.getX()) ? b : c;
    }
}

