/**
 * File: Cone.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Concrete class representing a Cone.
 *          Demonstrates has-a relationship (has radius and height).
 */
public class Cone extends ThreeDimensionalShape {
    private final double radius; // has-a relationship: Cone has radius
    private final double height; // has-a relationship: Cone has height

    /**
     * Constructor for Cone
     * @param radius the radius of the cone's base
     * @param height the height of the cone
     */
    public Cone(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    /**
     * Calculates the volume of the cone
     * @return volume using formula: (1/3) * π * r² * h
     */
    @Override
    public double getVolume() {
        return (1.0 / 3.0) * Math.PI * Math.pow(radius, 2) * height;
    }

    /**
     * Gets the radius of the cone
     * @return radius value
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Gets the height of the cone
     * @return height value
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the name of this shape
     * @return "Cone"
     */
    @Override
    public String getName() {
        return "Cone";
    }
}
