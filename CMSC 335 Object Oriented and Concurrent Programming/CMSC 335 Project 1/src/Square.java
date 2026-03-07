/**
 * Square.java; 18JAN2026; Joseph A. Merrill;
 * Represents a square with equal sides
 * A square is a special case of a rectangle
 */
public class Square extends TwoDimensionalShape {
    private double side;
    
    public Square(double side) {
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
    public double getArea() {
        return side * side;
    }
    
    @Override
    public String getShapeName() {
        return "Square";
    }
}
