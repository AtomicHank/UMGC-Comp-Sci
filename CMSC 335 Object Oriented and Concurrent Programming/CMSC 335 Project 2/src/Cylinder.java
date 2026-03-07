/**
 * File: Cylinder.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Concrete class representing a Cylinder.
 *          Demonstrates has-a relationship (has radius and height).
 */
public class Cylinder extends ThreeDimensionalShape {
    private final double radius; // has-a relationship: Cylinder has radius
    private final double height; // has-a relationship: Cylinder has height

    /**
     * Constructor for Cylinder
     * @param radius the radius of the cylinder's base
     * @param height the height of the cylinder
     */
    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    /**
     * Calculates the volume of the cylinder
     * @return volume using formula: π * r² * h
     */
    @Override
    public double getVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    /**
     * Gets the radius of the cylinder
     * @return radius value
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Gets the height of the cylinder
     * @return height value
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the name of this shape
     * @return "Cylinder"
     */
    @Override
    public String getName() {
        return "Cylinder";
    }
}
