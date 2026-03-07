/**
 * File: Circle.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Concrete class representing a Circle.
 *          Demonstrates has-a relationship (has radius).
 */
public class Circle extends TwoDimensionalShape {
    private final double radius; // has-a relationship: Circle has a radius

    /**
     * Constructor for Circle
     * @param radius the radius of the circle
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Calculates the area of the circle
     * @return area using formula: π * r²
     */
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /**
     * Gets the radius of the circle
     * @return radius value
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the name of this shape
     * @return "Circle"
     */
    @Override
    public String getName() {
        return "Circle";
    }
}
