// CMSC 330 Advanced Programming Languages-Project 1
// Joe Merrill 2FEB2026
// SolidPolygon.java - Class that defines all solid (filled) polygons

import java.awt.*;

class SolidPolygon extends Polygon_ {

    // Constructor that calls super constructor

    public SolidPolygon(Color color, int vertexCount) {
        super(color, vertexCount);
    }

    // Draws solid (filled) polygon

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        graphics.fillPolygon(polygon);
    }
}
