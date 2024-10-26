package com.epam.rd.autotasks.segments;

public class Segment {

    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new IllegalArgumentException("Segment cannot have identical start and end points");
        }
        this.start = start;
        this.end = end;
    }

    public double length() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2.0;
        double midY = (start.getY() + end.getY()) / 2.0;
        return new Point(midX, midY);
    }

    public Point intersection(Segment another) {
        double x1 = start.getX(), y1 = start.getY();
        double x2 = end.getX(), y2 = end.getY();
        double x3 = another.start.getX(), y3 = another.start.getY();
        double x4 = another.end.getX(), y4 = another.end.getY();

        // Calculate determinants
        double denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (denom == 0) {
            return null; // Lines are parallel or collinear
        }

        double xNum = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
        double yNum = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);

        double intersectionX = xNum / denom;
        double intersectionY = yNum / denom;

        Point intersectionPoint = new Point(intersectionX, intersectionY);

        // Check if intersection point is within both segments
        if (isPointOnSegment(intersectionPoint, this) && isPointOnSegment(intersectionPoint, another)) {
            return intersectionPoint;
        }

        return null;
    }

    private boolean isPointOnSegment(Point p, Segment segment) {
        double minX = Math.min(segment.start.getX(), segment.end.getX());
        double maxX = Math.max(segment.start.getX(), segment.end.getX());
        double minY = Math.min(segment.start.getY(), segment.end.getY());
        double maxY = Math.max(segment.start.getY(), segment.end.getY());

        return p.getX() >= minX && p.getX() <= maxX && p.getY() >= minY && p.getY() <= maxY;
    }

    @Override
    public String toString() {
        return "Segment from " + start + " to " + end;
    }
}
