// CMSC 330 Advanced Programming Languages-Project 1
// Joe Merrill 2FEB2026
// IsoscelesTriangle.java - Class that defines a solid isosceles triangle

import java.awt.*;

class IsoscelesTriangle extends SolidPolygon {

    // Constructor that initializes the vertices of the isosceles triangle given the top vertex, height, and width

    public IsoscelesTriangle(Color color, Point topVertex, int height, int width) {
        super(color, 3);
        int[] x_points = {topVertex.x, topVertex.x - width / 2, topVertex.x + width / 2};
        int[] y_points = {topVertex.y, topVertex.y + height, topVertex.y + height};
        createPolygon(x_points, y_points);
    }
}
