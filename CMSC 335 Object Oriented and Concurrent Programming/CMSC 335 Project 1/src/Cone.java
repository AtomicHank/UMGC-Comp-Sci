/**
 * Cone.java; 18JAN2026; Joseph A. Merrill; 
 * Represents a cone with a radius and height
 * Assumption: Right circular cone
 */
public class Cone extends ThreeDimensionalShape {
    private double radius;
    private double height;
    
    public Cone(double radius, double height) {
        super();
        if (radius <= 0 || height <= 0) {
            throw new IllegalArgumentException("Radius and height must be positive");
        }
        this.radius = radius;
        this.height = height;
    }
    
    public double getRadius() {
        return radius;
    }
    
    public double getHeight() {
        return height;
    }
    
    @Override
    public double getVolume() {
        return (1.0 / 3.0) * Math.PI * radius * radius * height;
    }
    
    @Override
    public String getShapeName() {
        return "Cone";
    }
}
