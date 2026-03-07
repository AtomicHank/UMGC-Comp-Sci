/**
 * Sphere.java; 18JAN2026; Joseph A. Merrill;
 * Represents a sphere with a given radius
 */
public class Sphere extends ThreeDimensionalShape {
    private double radius;
    
    public Sphere(double radius) {
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
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }
    
    @Override
    public String getShapeName() {
        return "Sphere";
    }
}
