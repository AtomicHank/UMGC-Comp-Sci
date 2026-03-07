/**
 * Torus.java; 18JAN2026; Joseph A. Merrill;
 * Represents a torus (donut shape) with major and minor radius
 * Assumption: Major radius is distance from center of torus to center of tube
 * Minor radius is the radius of the tube itself
 */
public class Torus extends ThreeDimensionalShape {
    private double majorRadius;  // Distance from center to tube center
    private double minorRadius;  // Radius of the tube
    
    public Torus(double majorRadius, double minorRadius) {
        super();
        if (majorRadius <= 0 || minorRadius <= 0) {
            throw new IllegalArgumentException("Radii must be positive");
        }
        if (minorRadius >= majorRadius) {
            throw new IllegalArgumentException("Minor radius must be less than major radius");
        }
        this.majorRadius = majorRadius;
        this.minorRadius = minorRadius;
    }
    
    public double getMajorRadius() {
        return majorRadius;
    }
    
    public double getMinorRadius() {
        return minorRadius;
    }
    
    @Override
    public double getVolume() {
        return 2 * Math.PI * Math.PI * majorRadius * minorRadius * minorRadius;
    }
    
    @Override
    public String getShapeName() {
        return "Torus";
    }
}
