/**
 * File: Circle.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Circle (2D) defined by radius.
 */
public class Circle extends TwoDimensionalShape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
        this.dimensions = 2;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String getName() {
        return "Circle";
    }
}
