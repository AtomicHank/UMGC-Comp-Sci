/**
 * File: Sphere.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Sphere (3D) defined by radius.
 */
public class Sphere extends ThreeDimensionalShape {
    private final double radius;

    public Sphere(double radius) {
        this.radius = radius;
        this.dimensions = 3;
    }

    @Override
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String getName() {
        return "Sphere";
    }
}
