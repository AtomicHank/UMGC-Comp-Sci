// CMSC 330 Advanced Programming Languages-Project 1
// Joe Merrill 2FEB2026
// RegularPolygon.java - Class that defines a solid regular polygon

import java.awt.*;

class RegularPolygon extends SolidPolygon {

    // Constructor that initializes the vertices of the regular polygon given the center point,
    // number of sides, and radius

    public RegularPolygon(Color color, Point center, int sides, int radius) {
        super(color, sides);
        int[] x_points = new int[sides];
        int[] y_points = new int[sides];
        for (int i = 0; i < sides; i++) {
            x_points[i] = (int) (center.x + radius * Math.cos(2 * Math.PI * i / sides));
            y_points[i] = (int) (center.y + radius * Math.sin(2 * Math.PI * i / sides));
        }
        createPolygon(x_points, y_points);
    }
}
