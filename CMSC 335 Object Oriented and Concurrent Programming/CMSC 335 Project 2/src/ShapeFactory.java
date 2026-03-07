/**
 * File: ShapeFactory.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Factory class for creating Shape objects based on user selection.
 *          Demonstrates Factory design pattern and encapsulates object creation logic.
 */
public class ShapeFactory {

    /**
     * Creates a Shape object based on the shape name and parameters
     * @param shapeName the name of the shape to create
     * @param params array of parameters (dimensions) for the shape
     * @return a new Shape object
     * @throws IllegalArgumentException if shape name is unknown or params are invalid
     */
    public static Shape createShape(String shapeName, double[] params) {
        if (params == null || shapeName == null) {
            throw new IllegalArgumentException("Shape name and parameters cannot be null");
        }

        switch (shapeName) {
            // 2D Shapes
            case "Circle":
                if (params.length < 1) throw new IllegalArgumentException("Circle requires 1 parameter: radius");
                return new Circle(params[0]);

            case "Square":
                if (params.length < 1) throw new IllegalArgumentException("Square requires 1 parameter: side");
                return new Square(params[0]);

            case "Rectangle":
                if (params.length < 2) throw new IllegalArgumentException("Rectangle requires 2 parameters: width, height");
                return new Rectangle(params[0], params[1]);

            case "Triangle":
                if (params.length < 2) throw new IllegalArgumentException("Triangle requires 2 parameters: base, height");
                return new Triangle(params[0], params[1]);

            // 3D Shapes
            case "Sphere":
                if (params.length < 1) throw new IllegalArgumentException("Sphere requires 1 parameter: radius");
                return new Sphere(params[0]);

            case "Cube":
                if (params.length < 1) throw new IllegalArgumentException("Cube requires 1 parameter: side");
                return new Cube(params[0]);

            case "Cone":
                if (params.length < 2) throw new IllegalArgumentException("Cone requires 2 parameters: radius, height");
                return new Cone(params[0], params[1]);

            case "Cylinder":
                if (params.length < 2) throw new IllegalArgumentException("Cylinder requires 2 parameters: radius, height");
                return new Cylinder(params[0], params[1]);

            case "Torus":
                if (params.length < 2) throw new IllegalArgumentException("Torus requires 2 parameters: major radius, minor radius");
                return new Torus(params[0], params[1]);

            default:
                throw new IllegalArgumentException("Unknown shape: " + shapeName);
        }
    }

    /**
     * Returns the parameter labels for a given shape
     * @param shapeName the name of the shape
     * @return array of String labels for the parameters
     */
    public static String[] getParameterLabels(String shapeName) {
        switch (shapeName) {
            case "Circle":
            case "Sphere":
                return new String[]{"Radius"};
            case "Square":
            case "Cube":
                return new String[]{"Side"};
            case "Rectangle":
                return new String[]{"Width", "Height"};
            case "Triangle":
                return new String[]{"Base", "Height"};
            case "Cone":
            case "Cylinder":
                return new String[]{"Radius", "Height"};
            case "Torus":
                return new String[]{"Major Radius", "Minor Radius"};
            default:
                return new String[]{};
        }
    }
}
