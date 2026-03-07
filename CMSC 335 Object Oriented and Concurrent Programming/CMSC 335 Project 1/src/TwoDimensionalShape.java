/**
 * TwoDimensionalShape.java; 18JAN2026; Joseph A. Merrill;
 * Abstract class representing all two-dimensional shapes
 * All 2D shapes have an area that can be calculated
 */
public abstract class TwoDimensionalShape extends Shape {
    
    public TwoDimensionalShape() {
        super(2);
    }
    
    public abstract double getArea();
    
    @Override
    public String toString() {
        return String.format("The area of the %s is %.2f", getShapeName(), getArea());
    }
}
