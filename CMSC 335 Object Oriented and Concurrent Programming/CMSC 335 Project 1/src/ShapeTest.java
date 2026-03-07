/**
 * ShapeTest.java; 18JAN2026; Joseph A. Merrill;
 * Test class to verify all shape calculations
 */
public class ShapeTest {
    public static void main(String[] args) {
        System.out.println("========== Testing 2D Shapes ==========\n");
        
        // Test Circle
        Circle circle = new Circle(5.0);
        System.out.println("Circle with radius 5.0:");
        System.out.println("  " + circle);
        System.out.println("  Number of dimensions: " + circle.getNumberOfDimensions());
        System.out.println();
        
        // Test Rectangle
        Rectangle rectangle = new Rectangle(4.0, 9.5);
        System.out.println("Rectangle with length 4.0 and width 9.5:");
        System.out.println("  " + rectangle);
        System.out.println();
        
        // Test Square
        Square square = new Square(6.0);
        System.out.println("Square with side 6.0:");
        System.out.println("  " + square);
        System.out.println();
        
        // Test Triangle
        Triangle triangle = new Triangle(8.0, 5.0);
        System.out.println("Triangle with base 8.0 and height 5.0:");
        System.out.println("  " + triangle);
        System.out.println();
        
        System.out.println("========== Testing 3D Shapes ==========\n");
        
        // Test Sphere
        Sphere sphere = new Sphere(3.0);
        System.out.println("Sphere with radius 3.0:");
        System.out.println("  " + sphere);
        System.out.println("  Number of dimensions: " + sphere.getNumberOfDimensions());
        System.out.println();
        
        // Test Cube
        Cube cube = new Cube(4.0);
        System.out.println("Cube with side 4.0:");
        System.out.println("  " + cube);
        System.out.println();
        
        // Test Cone
        Cone cone = new Cone(3.0, 7.0);
        System.out.println("Cone with radius 3.0 and height 7.0:");
        System.out.println("  " + cone);
        System.out.println();
        
        // Test Cylinder
        Cylinder cylinder = new Cylinder(2.5, 10.0);
        System.out.println("Cylinder with radius 2.5 and height 10.0:");
        System.out.println("  " + cylinder);
        System.out.println();
        
        // Test Torus
        Torus torus = new Torus(5.0, 2.0);
        System.out.println("Torus with major radius 5.0 and minor radius 2.0:");
        System.out.println("  " + torus);
        System.out.println();
        
        System.out.println("========== Testing Error Handling ==========\n");
        
        try {
            new Circle(-5.0);
            System.out.println("ERROR: Should have thrown exception for negative radius");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error for negative radius: " + e.getMessage());
        }
        
        try {
            new Torus(2.0, 5.0);
            System.out.println("ERROR: Should have thrown exception for invalid torus");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error for invalid torus: " + e.getMessage());
        }
        
        System.out.println("\n========== All Tests Complete ==========");
    }
}