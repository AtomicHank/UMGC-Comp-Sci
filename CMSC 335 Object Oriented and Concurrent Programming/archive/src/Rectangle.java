/**
 * File: Rectangle.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Rectangle (2D) defined by width and height.
 */
public class Rectangle extends TwoDimensionalShape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
        this.dimensions = 2;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String getName() {
        return "Rectangle";
    }
}
