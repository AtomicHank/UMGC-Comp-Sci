/**
 * Shape.java; 18JAN2026; Joseph A. Merrill;
 * Abstract base class representing a generic shape
 * All shapes have a number of dimensions (2D or 3D)
 */
public abstract class Shape {
    private int numberOfDimensions;
    
    public Shape(int numberOfDimensions) {
        this.numberOfDimensions = numberOfDimensions;
    }
    
    public int getNumberOfDimensions() {
        return numberOfDimensions;
    }
    
    public abstract String getShapeName();
}
