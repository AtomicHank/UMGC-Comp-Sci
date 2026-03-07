# CMSC 335 Project 2 - Shape Viewer Application

**Author:** Joe Merrill
**Date:** February 1, 2026
**Course:** CMSC 335 Object Oriented and Concurrent Programming

## Project Description

This project implements a Java Swing GUI application for displaying 2D and 3D geometric shapes. Users can select a shape, enter dimensional parameters, and view the shape rendered in the application window. The project demonstrates object-oriented programming concepts including inheritance hierarchies, polymorphism, factory design patterns, and event-driven GUI programming.

## Features

- **Shape Hierarchy:** Implements inheritance with abstract base classes (Shape, TwoDimensionalShape, ThreeDimensionalShape) and nine concrete shape classes
- **GUI Interface:** Swing-based GUI with event handlers and listeners
- **Dynamic Rendering:** 2D shapes drawn programmatically, 3D shapes rendered with isometric projections and gradients
- **Calculation Display:** Shows area for 2D shapes and volume for 3D shapes
- **Input Validation:** Validates user input and provides error messages for invalid data

## Supported Shapes

### 2D Shapes
- Circle (requires: radius)
- Square (requires: side)
- Rectangle (requires: width, height)
- Triangle (requires: base, height)

### 3D Shapes
- Sphere (requires: radius)
- Cube (requires: side)
- Cone (requires: radius, height)
- Cylinder (requires: radius, height)
- Torus (requires: major radius, minor radius)

## Project Structure

```
CMSC 335 Project 2/
├── src/
│   ├── Shape.java                    # Abstract base class
│   ├── TwoDimensionalShape.java      # Abstract 2D shape class
│   ├── ThreeDimensionalShape.java    # Abstract 3D shape class
│   ├── Circle.java                   # Concrete 2D shape
│   ├── Square.java                   # Concrete 2D shape
│   ├── Rectangle.java                # Concrete 2D shape
│   ├── Triangle.java                 # Concrete 2D shape
│   ├── Sphere.java                   # Concrete 3D shape
│   ├── Cube.java                     # Concrete 3D shape
│   ├── Cone.java                     # Concrete 3D shape
│   ├── Cylinder.java                 # Concrete 3D shape
│   ├── Torus.java                    # Concrete 3D shape
│   ├── ShapeFactory.java             # Factory for creating shapes
│   ├── ShapePanel.java               # Custom panel for rendering shapes
│   └── ShapeGUI.java                 # Main GUI application
├── bin/                              # Compiled class files
└── README.md                         # This file
```

## How to Compile

From the project root directory:

```bash
javac -d bin src/*.java
```

## How to Run

From the project root directory:

```bash
java -cp bin ShapeGUI
```

## How to Use

1. **Launch the application** using the command above
2. **Select a shape** from the dropdown menu
3. **Enter parameters** in the text fields that appear
   - Parameter labels update based on the selected shape
   - All values must be positive numbers
4. **Click "Display Shape"** to render the shape
   - 2D shapes are drawn as outlines with area displayed
   - 3D shapes are rendered with shading and volume displayed
5. **Click "Clear"** to reset the display and input fields

## Class Relationships

### Inheritance (is-a relationships)
- Circle **is a** TwoDimensionalShape **is a** Shape
- Square **is a** TwoDimensionalShape **is a** Shape
- Rectangle **is a** TwoDimensionalShape **is a** Shape
- Triangle **is a** TwoDimensionalShape **is a** Shape
- Sphere **is a** ThreeDimensionalShape **is a** Shape
- Cube **is a** ThreeDimensionalShape **is a** Shape
- Cone **is a** ThreeDimensionalShape **is a** Shape
- Cylinder **is a** ThreeDimensionalShape **is a** Shape
- Torus **is a** ThreeDimensionalShape **is a** Shape

### Composition (has-a relationships)
- Circle **has a** radius
- Square **has a** side
- Rectangle **has a** width and height
- Triangle **has a** base and height
- Sphere **has a** radius
- Cube **has a** side
- Cone **has a** radius and height
- Cylinder **has a** radius and height
- Torus **has a** major radius and minor radius
- ShapeGUI **has a** ShapePanel
- ShapePanel **has a** Shape

## Technical Implementation

- **Language:** Java (JDK 8 or higher required)
- **GUI Framework:** Swing
- **Design Patterns:** Factory pattern, MVC pattern
- **Event Handling:** ActionListener, ItemListener
- **Graphics:** Java 2D API with Graphics2D, gradients, and antialiasing

## Testing

The application has been tested with multiple test cases for each shape type, including:
- Minimum valid values
- Maximum display values
- Mid-range values
- Invalid inputs (negative numbers, non-numeric text, empty fields)

See Test Plan document for detailed test cases and results.

## Known Limitations

- Input values are capped at maximum display sizes to ensure shapes fit within the window
- 3D shapes are representations using isometric projection, not true 3D rendering
- No support for saving/loading shape configurations

## Future Enhancements

- Add ability to save shape configurations
- Support for multiple shapes displayed simultaneously
- Animation of rotating 3D shapes
- Export rendered shapes as images

## License

This is an academic project for CMSC 335. All rights reserved.
