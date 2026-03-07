/**
 * File: Shape.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Abstract base class for all shapes in Project 2.
 */
public abstract class Shape {
    protected int dimensions;

    public int getDimensions() {
        return dimensions;
    }

    /** Friendly name for UI/debug output. */
    public abstract String getName();
}
