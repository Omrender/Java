package com.epam.rd.autotasks.figures;

// Figure.java
public abstract class Figure {
    public abstract double area();
    public abstract String pointsToString();
    public abstract String toString();
    public abstract Point centroid();
    public abstract boolean isTheSame(Figure figure);
}

