# Java OO Shapes Program

## Project Structure
- src/ - Contains all Java source files
- README.md - This file

## Compilation Instructions
```bash
cd src
javac *.java
```

## Running the Program
```bash
java ShapeMenu
```

## Running Tests
```bash
java ShapeTest
```

## Class Hierarchy
```
Shape (abstract)
├── TwoDimensionalShape (abstract)
│   ├── Circle
│   ├── Rectangle
│   ├── Square
│   └── Triangle
└── ThreeDimensionalShape (abstract)
    ├── Sphere
    ├── Cube
    ├── Cone
    ├── Cylinder
    └── Torus
```

## Assumptions
- Triangle: Uses base and height for area calculation
- Cone: Right circular cone
- Cylinder: Right circular cylinder
- Torus: Major radius > minor radius

## Features
- Complete object-oriented design with inheritance
- Input validation and error handling
- Interactive command-line menu
- Comprehensive test suite
