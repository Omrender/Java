package com.epam.rd.autotasks.triangle;

class Triangle {

    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        //TODO
        if (isDegenerative(a, b, c)) {
            throw new IllegalArgumentException("Points are collinear and do not form a valid triangle");
        }
        this.a = a;
        this.b = b;
        this.c = c;

    }

    private boolean isDegenerative(Point p1, Point p2, Point p3) {
        double area = 0.5 * Math.abs(p1.getX() * (p2.getY() - p3.getY()) + p2.getX() * (p3.getY() - p1.getY()) + p3.getX() * (p1.getY() - p2.getY()));
        return area == 0;
    }

    public double area() {
        //TODO
        return 0.5 * Math.abs(a.getX() * (b.getY() - c.getY()) +
                              b.getX() * (c.getY() - a.getY()) +
                              c.getX() * (a.getY() - b.getY()));
        
    }

    public Point centroid(){
        //TODO
        double centroidX = (a.getX() + b.getX() + c.getX()) / 3.0;
        double centroidY = (a.getY() + b.getY() + c.getY()) / 3.0;
        return new Point(centroidX, centroidY);
    }

}
