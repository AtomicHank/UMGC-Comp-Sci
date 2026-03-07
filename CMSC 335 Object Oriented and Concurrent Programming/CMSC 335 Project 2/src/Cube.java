/**
 * File: Cube.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Concrete class representing a Cube.
 *          Demonstrates has-a relationship (has side length).
 */
public class Cube extends ThreeDimensionalShape {
    private final double side; // has-a relationship: Cube has side

    /**
     * Constructor for Cube
     * @param side the length of one side of the cube
     */
    public Cube(double side) {
        this.side = side;
    }

    /**
     * Calculates the volume of the cube
     * @return volume using formula: side³
     */
    @Override
    public double getVolume() {
        return Math.pow(side, 3);
    }

    /**
     * Gets the side length of the cube
     * @return side value
     */
    public double getSide() {
        return side;
    }

    /**
     * Returns the name of this shape
     * @return "Cube"
     */
    @Override
    public String getName() {
        return "Cube";
    }
}
