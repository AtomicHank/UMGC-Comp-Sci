// CMSC 330 Advanced Programming Languages-Project 1
// Joe Merrill 2FEB2026
// Parallelogram.java - Class that defines a solid parallelogram

import java.awt.*;

class Parallelogram extends SolidPolygon {

    // Constructor that initializes the vertices of the parallelogram given the upper left and lower right
    // vertices and an x offset between the upper and lower left vertices

    public Parallelogram(Color color, Point upperLeft, Point lowerRight, int offset) {
        super(color, 4);
        int[] x_points = {upperLeft.x, lowerRight.x - offset, lowerRight.x, upperLeft.x + offset};
        int[] y_points = {upperLeft.y, upperLeft.y, lowerRight.y, lowerRight.y};
        createPolygon(x_points, y_points);
    }
}
