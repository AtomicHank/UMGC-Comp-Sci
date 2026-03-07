/**
 * File: Torus.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Concrete class representing a Torus (donut shape).
 *          Demonstrates has-a relationship (has major radius and minor radius).
 */
public class Torus extends ThreeDimensionalShape {
    private final double majorRadius; // has-a: Torus has major radius (center to tube center)
    private final double minorRadius; // has-a: Torus has minor radius (tube radius)

    /**
     * Constructor for Torus
     * @param majorRadius the major radius (from center of torus to center of tube)
     * @param minorRadius the minor radius (radius of the tube)
     */
    public Torus(double majorRadius, double minorRadius) {
        this.majorRadius = majorRadius;
        this.minorRadius = minorRadius;
    }

    /**
     * Calculates the volume of the torus
     * @return volume using formula: 2 * π² * R * r²
     */
    @Override
    public double getVolume() {
        return 2.0 * Math.PI * Math.PI * majorRadius * Math.pow(minorRadius, 2);
    }

    /**
     * Gets the major radius of the torus
     * @return major radius value
     */
    public double getMajorRadius() {
        return majorRadius;
    }

    /**
     * Gets the minor radius of the torus
     * @return minor radius value
     */
    public double getMinorRadius() {
        return minorRadius;
    }

    /**
     * Returns the name of this shape
     * @return "Torus"
     */
    @Override
    public String getName() {
        return "Torus";
    }
}
