/**
 * File: Shape.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Abstract base class for all shapes in the hierarchy.
 *          Demonstrates inheritance using is-a relationships.
 */
public abstract class Shape {
    protected int dimensions; // 2 for 2D shapes, 3 for 3D shapes

    /**
     * Returns the number of dimensions for this shape
     * @return 2 for 2D shapes, 3 for 3D shapes
     */
    public int getNumberOfDimensions() {
        return dimensions;
    }

    /**
     * Returns the name of the shape
     * @return String representing the shape name
     */
    public abstract String getName();
}
