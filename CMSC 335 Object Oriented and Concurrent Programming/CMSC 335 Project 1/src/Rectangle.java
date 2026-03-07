/**
 *Rectangle.java; 18JAN2026; Joseph A. Merrill;
 *  Represents a rectangle with length and width
 */
public class Rectangle extends TwoDimensionalShape {
    private double length;
    private double width;
    
    public Rectangle(double length, double width) {
        super();
        if (length <= 0 || width <= 0) {
            throw new IllegalArgumentException("Length and width must be positive");
        }
        this.length = length;
        this.width = width;
    }
    
    public double getLength() {
        return length;
    }
    
    public double getWidth() {
        return width;
    }
    
    @Override
    public double getArea() {
        return length * width;
    }
    
    @Override
    public String getShapeName() {
        return "Rectangle";
    }
}
