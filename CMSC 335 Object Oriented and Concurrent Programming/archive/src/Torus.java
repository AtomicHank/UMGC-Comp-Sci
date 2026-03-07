/**
 * File: Torus.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Torus (3D) defined by major radius (R) and minor radius (r).
 * Volume = 2 * pi^2 * R * r^2
 */
public class Torus extends ThreeDimensionalShape {
    private final double majorRadius;
    private final double minorRadius;

    public Torus(double majorRadius, double minorRadius) {
        this.majorRadius = majorRadius;
        this.minorRadius = minorRadius;
        this.dimensions = 3;
    }

    @Override
    public double getVolume() {
        return 2.0 * Math.PI * Math.PI * majorRadius * minorRadius * minorRadius;
    }

    public double getMajorRadius() {
        return majorRadius;
    }

    public double getMinorRadius() {
        return minorRadius;
    }

    @Override
    public String getName() {
        return "Torus";
    }
}
