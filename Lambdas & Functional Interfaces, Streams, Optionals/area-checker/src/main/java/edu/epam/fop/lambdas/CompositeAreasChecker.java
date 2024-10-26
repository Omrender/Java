package edu.epam.fop.lambdas;

import java.util.function.Predicate;

import edu.epam.fop.lambdas.area.Circle;
import edu.epam.fop.lambdas.area.Rectangle;
import edu.epam.fop.lambdas.area.Triangle;

public interface CompositeAreasChecker {

  static Predicate<Point> task1() {
    // Define the circle
    Circle circle = Circle.c(Point.p(-0.5, 0.5), 1.5);
    
    // Define the rectangle
    Rectangle rectangle = Rectangle.r(Point.p(-3, -1), Point.p(0, 1));
    
    // Combine the areas using 'or' operation
    return circle.includeBorders().or(rectangle.includeBorders());
  }
  static Predicate<Point> task2() {
    // Define the rectangles
    Rectangle rect1 = Rectangle.r(Point.p(-1, 1.5), Point.p(1.5, 3));
    Rectangle rect2 = Rectangle.r(Point.p(2, 2.25), Point.p(2, 3));
    
    // Define the point P
    Point pointP = Point.p(0.5, 0);
    
    // Combine the areas using 'and' operation excluding the borders
    return rect1.test(true).and(rect2.test(true)).and(p -> !pointP.equals(p));
  }

  static Predicate<Point> task3() {
    // Define the triangle
    Triangle triangle = Triangle.t(Point.p(-2, 2), Point.p(-1, 3), Point.p(0, 1));
    
    // Define the rectangles
    Rectangle rect1 = Rectangle.r(Point.p(-2, 1), Point.p(0, 2));
    Rectangle rect2 = Rectangle.r(Point.p(-1, 2), Point.p(0, 3));
    
    // Define the point P
    Point pointP = Point.p(-1.5, 0);
    
    // Combine the areas using 'and' operation excluding the borders
    return triangle.test(true).and(rect1.test(true)).and(rect2.test(true)).and(p -> !pointP.equals(p));
  }
}
