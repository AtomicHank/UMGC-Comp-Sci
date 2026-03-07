/**
 * File: Square.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Concrete class representing a Square.
 *          Demonstrates has-a relationship (has side length).
 */
public class Square extends TwoDimensionalShape {
    private final double side; // has-a relationship: Square has a side

    /**
     * Constructor for Square
     * @param side the length of one side of the square
     */
    public Square(double side) {
        this.side = side;
    }

    /**
     * Calculates the area of the square
     * @return area using formula: side²
     */
    @Override
    public double getArea() {
        return side * side;
    }

    /**
     * Gets the side length of the square
     * @return side value
     */
    public double getSide() {
        return side;
    }

    /**
     * Returns the name of this shape
     * @return "Square"
     */
    @Override
    public String getName() {
        return "Square";
    }
}
