/**
 * File: Sphere.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Concrete class representing a Sphere.
 *          Demonstrates has-a relationship (has radius).
 */
public class Sphere extends ThreeDimensionalShape {
    private final double radius; // has-a relationship: Sphere has radius

    /**
     * Constructor for Sphere
     * @param radius the radius of the sphere
     */
    public Sphere(double radius) {
        this.radius = radius;
    }

    /**
     * Calculates the volume of the sphere
     * @return volume using formula: (4/3) * π * r³
     */
    @Override
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Gets the radius of the sphere
     * @return radius value
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the name of this shape
     * @return "Sphere"
     */
    @Override
    public String getName() {
        return "Sphere";
    }
}
