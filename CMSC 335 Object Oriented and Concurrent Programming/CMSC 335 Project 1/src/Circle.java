/**
 * Circle.java; 18JAN2026; Joseph A. Merrill; 
 * Represents a circle with a given radius
 */
public class Circle extends TwoDimensionalShape {
    private double radius;
    
    public Circle(double radius) {
        super();
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }
    
    public double getRadius() {
        return radius;
    }
    
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public String getShapeName() {
        return "Circle";
    }
}
