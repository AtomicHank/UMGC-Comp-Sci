/**
 * File: Cylinder.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Cylinder (3D) defined by radius and height.
 */
public class Cylinder extends ThreeDimensionalShape {
    private final double radius;
    private final double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
        this.dimensions = 3;
    }

    @Override
    public double getVolume() {
        return Math.PI * radius * radius * height;
    }

    public double getRadius() {
        return radius;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String getName() {
        return "Cylinder";
    }
}
