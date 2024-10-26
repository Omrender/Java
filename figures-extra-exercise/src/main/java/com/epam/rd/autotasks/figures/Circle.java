package com.epam.rd.autotasks.figures;

// Circle.java
public class Circle extends Figure {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Invalid radius");
        }
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
        return String.format("Circle[%s%.4f]", pointsToString(), radius);
    }

    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Circle)) {
            return false;
        }
        Circle other = (Circle) figure;
        return center.getX() == other.center.getX() &&
               center.getY() == other.center.getY() &&
               radius == other.radius;
    }
}

