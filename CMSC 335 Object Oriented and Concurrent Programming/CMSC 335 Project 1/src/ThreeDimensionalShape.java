/**
 * ThreeDimensionalShape.java; 18JAN2026; Joseph A. Merrill;
 * Abstract class representing all three-dimensional shapes
 * All 3D shapes have a volume that can be calculated
 */
public abstract class ThreeDimensionalShape extends Shape {
    
    public ThreeDimensionalShape() {
        super(3);
    }
    
    public abstract double getVolume();
    
    @Override
    public String toString() {
        return String.format("The volume of the %s is %.2f", getShapeName(), getVolume());
    }
}
