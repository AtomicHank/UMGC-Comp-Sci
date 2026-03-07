/**
 * Cylinder.java; 18JAN2026; Joseph A. Merrill;
 * Represents a cylinder with a radius and height.
 * Assumption: Right circular cylinder
 */
public class Cylinder extends ThreeDimensionalShape {
    private double radius;
    private double height;
    
    public Cylinder(double radius, double height) {
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
        return Math.PI * radius * radius * height;
    }
    
    @Override
    public String getShapeName() {
        return "Cylinder";
    }
}
