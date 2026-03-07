/**
 * File: Square.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Square (2D) defined by side length.
 */
public class Square extends TwoDimensionalShape {
    private final double side;

    public Square(double side) {
        this.side = side;
        this.dimensions = 2;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public String getName() {
        return "Square";
    }
}
