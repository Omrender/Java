package com.epam.rd.autotasks.figures;

// Circle.java
// Circle.java
public class Circle extends Figure {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String pointsToString() {
        return center.toString();
    }

    @Override
    public String toString() {
        // Format the radius to match the expected precision
        return String.format("Circle[%s%.4f]", pointsToString(), radius);
    }

    @Override
    public Point leftmostPoint() {
        return new Point(center.getX() - radius, center.getY());
    }
}

