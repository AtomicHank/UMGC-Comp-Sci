/**
 * File: Triangle.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Concrete class representing a Triangle.
 *          Demonstrates has-a relationship (has base and height).
 */
public class Triangle extends TwoDimensionalShape {
    private final double base;   // has-a relationship: Triangle has base
    private final double height; // has-a relationship: Triangle has height

    /**
     * Constructor for Triangle
     * @param base the base length of the triangle
     * @param height the height of the triangle
     */
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    /**
     * Calculates the area of the triangle
     * @return area using formula: (base * height) / 2
     */
    @Override
    public double getArea() {
        return (base * height) / 2.0;
    }

    /**
     * Gets the base of the triangle
     * @return base value
     */
    public double getBase() {
        return base;
    }

    /**
     * Gets the height of the triangle
     * @return height value
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the name of this shape
     * @return "Triangle"
     */
    @Override
    public String getName() {
        return "Triangle";
    }
}
