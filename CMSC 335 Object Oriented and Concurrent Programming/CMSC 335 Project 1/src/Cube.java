/**
 * Cube.java; 18JAN2026; Joseph A. Merrill; 
 * Represents a cube with equal sides
 */
public class Cube extends ThreeDimensionalShape {
    private double side;
    
    public Cube(double side) {
        super();
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be positive");
        }
        this.side = side;
    }
    
    public double getSide() {
        return side;
    }
    
    @Override
    public double getVolume() {
        return Math.pow(side, 3);
    }
    
    @Override
    public String getShapeName() {
        return "Cube";
    }
}
