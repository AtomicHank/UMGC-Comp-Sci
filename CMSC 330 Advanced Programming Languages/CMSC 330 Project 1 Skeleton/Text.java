// CMSC 330 Advanced Programming Languages-Project 1
// Joe Merrill 2FEB2026
// Text.java - Class that defines a text image to be drawn on the scene

import java.awt.*;

class Text extends Image {

    private String text;
    private Point location;

    // Constructor that is supplied the color, location, and string to be displayed

    public Text(Color color, Point location, String text) {
        super(color);
        this.location = location;
        this.text = text;
    }

    // Draws the text string at the specified location

    @Override
    void draw(Graphics graphics) {
        colorDrawing(graphics);
        graphics.drawString(text, location.x, location.y);
    }
}
