/**
 * File: ThreeDimensionalShape.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Abstract class for 3D shapes. Extends Shape.
 *          Demonstrates inheritance hierarchy (is-a relationship).
 */
public abstract class ThreeDimensionalShape extends Shape {

    /**
     * Constructor sets dimensions to 3 for all 3D shapes
     */
    public ThreeDimensionalShape() {
        this.dimensions = 3;
    }

    /**
     * Calculates and returns the volume of the 3D shape
     * @return volume as a double
     */
    public abstract double getVolume();
}
