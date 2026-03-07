/**
 * File: Triangle.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Triangle (2D) defined by base and height.
 * Assumption: Right/any triangle area formula uses (base * height)/2.
 */
public class Triangle extends TwoDimensionalShape {
    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
        this.dimensions = 2;
    }

    @Override
    public double getArea() {
        return (base * height) / 2.0;
    }

    public double getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String getName() {
        return "Triangle";
    }
}
