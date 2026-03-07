/**
 * Triangle.java; 18JAN2026; Joseph A. Merrill;
 * Represents a triangle with a base and height
 * Assumption: Uses base and height for area calculation (works for all triangles)
 */
public class Triangle extends TwoDimensionalShape {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        super();
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height must be positive");
        }
        this.base = base;
        this.height = height;
    }
    
    public double getBase() {
        return base;
    }
    
    public double getHeight() {
        return height;
    }
    
    @Override
    public double getArea() {
        return 0.5 * base * height;
    }
    
    @Override
    public String getShapeName() {
        return "Triangle";
    }
}
