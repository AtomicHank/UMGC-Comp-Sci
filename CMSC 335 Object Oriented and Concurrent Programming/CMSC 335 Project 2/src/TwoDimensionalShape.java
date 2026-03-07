/**
 * File: TwoDimensionalShape.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Abstract class for 2D shapes. Extends Shape.
 *          Demonstrates inheritance hierarchy (is-a relationship).
 */
public abstract class TwoDimensionalShape extends Shape {

    /**
     * Constructor sets dimensions to 2 for all 2D shapes
     */
    public TwoDimensionalShape() {
        this.dimensions = 2;
    }

    /**
     * Calculates and returns the area of the 2D shape
     * @return area as a double
     */
    public abstract double getArea();
}
