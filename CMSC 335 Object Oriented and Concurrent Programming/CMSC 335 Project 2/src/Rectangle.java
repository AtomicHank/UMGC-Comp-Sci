/**
 * File: Rectangle.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Concrete class representing a Rectangle.
 *          Demonstrates has-a relationship (has width and height).
 */
public class Rectangle extends TwoDimensionalShape {
    private final double width;  // has-a relationship: Rectangle has width
    private final double height; // has-a relationship: Rectangle has height

    /**
     * Constructor for Rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates the area of the rectangle
     * @return area using formula: width * height
     */
    @Override
    public double getArea() {
        return width * height;
    }

    /**
     * Gets the width of the rectangle
     * @return width value
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the height of the rectangle
     * @return height value
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the name of this shape
     * @return "Rectangle"
     */
    @Override
    public String getName() {
        return "Rectangle";
    }
}
