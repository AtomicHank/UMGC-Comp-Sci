/**
 * File: ShapeFactory.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Centralizes creation of shapes from UI inputs.
 */
public class ShapeFactory {

    /**
     * Creates a Shape from the selected shape name and numeric dimensions.
     * dims is a double array containing the user-provided values in order.
     */
    public static Shape create(String shapeName, double[] dims) {
        switch (shapeName) {
            case "Circle":
                return new Circle(dims[0]); // radius
            case "Square":
                return new Square(dims[0]); // side
            case "Rectangle":
                return new Rectangle(dims[0], dims[1]); // width, height
            case "Triangle":
                return new Triangle(dims[0], dims[1]); // base, height
            case "Sphere":
                return new Sphere(dims[0]); // radius
            case "Cube":
                return new Cube(dims[0]); // side
            case "Cone":
                return new Cone(dims[0], dims[1]); // radius, height
            case "Cylinder":
                return new Cylinder(dims[0], dims[1]); // radius, height
            case "Torus":
                return new Torus(dims[0], dims[1]); // major R, minor r
            default:
                throw new IllegalArgumentException("Unknown shape: " + shapeName);
        }
    }

    /** Returns required parameter labels for each shape. */
    public static String[] paramLabels(String shapeName) {
        switch (shapeName) {
            case "Circle": return new String[]{"Radius"};
            case "Square": return new String[]{"Side"};
            case "Rectangle": return new String[]{"Width", "Height"};
            case "Triangle": return new String[]{"Base", "Height"};
            case "Sphere": return new String[]{"Radius"};
            case "Cube": return new String[]{"Side"};
            case "Cone": return new String[]{"Radius", "Height"};
            case "Cylinder": return new String[]{"Radius", "Height"};
            case "Torus": return new String[]{"Major Radius (R)", "Minor Radius (r)"};
            default: return new String[]{};
        }
    }
}
