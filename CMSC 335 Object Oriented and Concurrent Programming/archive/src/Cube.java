/**
 * File: Cube.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Cube (3D) defined by side length.
 */
public class Cube extends ThreeDimensionalShape {
    private final double side;

    public Cube(double side) {
        this.side = side;
        this.dimensions = 3;
    }

    @Override
    public double getVolume() {
        return side * side * side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public String getName() {
        return "Cube";
    }
}
