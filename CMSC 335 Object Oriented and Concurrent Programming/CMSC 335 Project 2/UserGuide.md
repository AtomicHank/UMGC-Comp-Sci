# User Guide - Shape Viewer Application
## CMSC 335 Project 2

**Author:** Joe Merrill
**Date:** February 1, 2026
**Version:** 1.0

---

## Table of Contents

1. Introduction
2. System Requirements
3. Installation Instructions
4. Getting Started
5. Using the Application
6. Troubleshooting
7. FAQs
8. Contact Information

---

## 1. Introduction

The Shape Viewer Application is an educational tool designed to visualize 2D and 3D geometric shapes. Users can select from nine different shapes, input dimensional parameters, and view accurate mathematical renderings with calculated areas or volumes.

### Purpose

This application demonstrates object-oriented programming concepts including:
- Inheritance hierarchies
- Polymorphism
- Event-driven programming
- Graphical user interface development

### Key Features

- Nine geometric shapes (Circle, Square, Rectangle, Triangle, Sphere, Cube, Cone, Cylinder, Torus)
- Interactive parameter input
- Real-time rendering
- Automatic area/volume calculations
- Input validation and error handling

---

## 2. System Requirements

### Minimum Requirements

- **Operating System:** Windows 10/11, macOS 10.14+, or Linux
- **Java Runtime Environment:** JDK 8 or higher
- **Memory:** 512 MB RAM
- **Disk Space:** 50 MB free space
- **Display:** 800x600 minimum resolution

### Recommended Requirements

- **Operating System:** Windows 11 or macOS 12+
- **Java Runtime Environment:** JDK 14 or higher
- **Memory:** 1 GB RAM
- **Display:** 1920x1080 resolution

---

## 3. Installation Instructions

### Step 1: Verify Java Installation

Open a terminal or command prompt and type:

```bash
java -version
```

You should see Java version 8 or higher. If not, download and install Java from:
https://www.oracle.com/java/technologies/downloads/

### Step 2: Download the Application

Download the project folder containing all source files:

```
CMSC 335 Project 2/
├── src/          (source code files)
├── bin/          (compiled class files)
└── README.md     (project documentation)
```

### Step 3: Compile the Application (if needed)

If the `bin` folder is empty or missing, compile the source code:

**On Windows:**
```cmd
cd "CMSC 335 Project 2"
mkdir bin
javac -d bin src\*.java
```

**On macOS/Linux:**
```bash
cd "CMSC 335 Project 2"
mkdir -p bin
javac -d bin src/*.java
```

### Step 4: Verify Installation

Check that `.class` files were created in the `bin` directory.

---

## 4. Getting Started

### Launching the Application

**On Windows:**
```cmd
cd "CMSC 335 Project 2"
java -cp bin ShapeGUI
```

**On macOS/Linux:**
```bash
cd "CMSC 335 Project 2"
java -cp bin ShapeGUI
```

### First Time Setup

No configuration is required. The application is ready to use immediately upon launch.

---

## 5. Using the Application

### Application Interface

The application window consists of three main areas:

1. **Control Panel** (top)
   - Shape selection dropdown
   - Parameter input fields
   - Display and Clear buttons

2. **Display Panel** (center)
   - Shows rendered shapes
   - Displays area/volume calculations
   - Shows status messages

3. **Status Bar** (within display panel)
   - Provides feedback and instructions

### Step-by-Step Usage Guide

#### Step 1: Select a Shape

1. Click the **Shape** dropdown menu
2. Choose from the available shapes:
   - **2D Shapes:** Circle, Square, Rectangle, Triangle
   - **3D Shapes:** Sphere, Cube, Cone, Cylinder, Torus

#### Step 2: Enter Parameters

After selecting a shape, input fields will appear with appropriate labels:

**Single Parameter Shapes:**
- Circle: Enter radius
- Square: Enter side length
- Sphere: Enter radius
- Cube: Enter side length

**Two Parameter Shapes:**
- Rectangle: Enter width and height
- Triangle: Enter base and height
- Cone: Enter radius and height
- Cylinder: Enter radius and height
- Torus: Enter major radius and minor radius

**Input Guidelines:**
- Use positive numbers only
- Decimal values are accepted (e.g., 125.5)
- Do not enter zero or negative values
- Do not leave fields empty

#### Step 3: Display the Shape

1. Click the **Display Shape** button
2. The shape will render in the center panel
3. Area (for 2D shapes) or Volume (for 3D shapes) will be shown

#### Step 4: View Additional Shapes

To view another shape:
- Select a different shape from the dropdown
- Enter new parameters
- Click Display Shape again

OR

- Click **Clear** button
- Start over with Step 1

---

## 6. Shape Reference Guide

### 2D Shapes

#### Circle
- **Parameters:** Radius (r)
- **Formula:** Area = πr²
- **Example:** Radius = 100 → Area = 31,415.93

#### Square
- **Parameters:** Side (s)
- **Formula:** Area = s²
- **Example:** Side = 150 → Area = 22,500.00

#### Rectangle
- **Parameters:** Width (w), Height (h)
- **Formula:** Area = w × h
- **Example:** Width = 200, Height = 100 → Area = 20,000.00

#### Triangle
- **Parameters:** Base (b), Height (h)
- **Formula:** Area = (b × h) / 2
- **Example:** Base = 180, Height = 120 → Area = 10,800.00

### 3D Shapes

#### Sphere
- **Parameters:** Radius (r)
- **Formula:** Volume = (4/3)πr³
- **Example:** Radius = 100 → Volume = 4,188,790.20

#### Cube
- **Parameters:** Side (s)
- **Formula:** Volume = s³
- **Example:** Side = 100 → Volume = 1,000,000.00

#### Cone
- **Parameters:** Radius (r), Height (h)
- **Formula:** Volume = (1/3)πr²h
- **Example:** Radius = 80, Height = 150 → Volume = 1,005,309.65

#### Cylinder
- **Parameters:** Radius (r), Height (h)
- **Formula:** Volume = πr²h
- **Example:** Radius = 70, Height = 200 → Volume = 3,078,760.80

#### Torus
- **Parameters:** Major Radius (R), Minor Radius (r)
- **Formula:** Volume = 2π²Rr²
- **Example:** Major Radius = 100, Minor Radius = 30 → Volume = 177,653.59

---

## 7. Troubleshooting

### Problem: Application Won't Start

**Solution:**
1. Verify Java is installed: `java -version`
2. Check you're in the correct directory
3. Verify bin folder contains .class files
4. Try recompiling the source code

### Problem: "Class not found" Error

**Solution:**
- Ensure you include `-cp bin` in the java command
- Verify all .class files exist in the bin directory

### Problem: Number Format Error

**Solution:**
- Enter only numeric values
- Use period (.) for decimals, not comma
- Do not include units (cm, inches, etc.)

### Problem: Shape Doesn't Display

**Solution:**
- Click the "Display Shape" button
- Verify all required parameters are entered
- Check for error dialogs
- Try smaller parameter values

### Problem: Error Message "Parameters must be greater than 0"

**Solution:**
- Enter only positive numbers
- Zero and negative values are not allowed
- Check for accidental minus sign

---

## 8. Frequently Asked Questions

**Q: What is the maximum size for shapes?**
A: Shapes are automatically scaled to fit within the display panel. Very large values will be capped at maximum display sizes.

**Q: Can I display multiple shapes at once?**
A: No, the current version displays one shape at a time. Use the Clear button to remove the current shape before displaying a new one.

**Q: Why do 3D shapes look flat?**
A: 3D shapes are rendered using 2D graphics with isometric projection and shading to create the illusion of depth.

**Q: Can I save my shapes?**
A: The current version does not support saving. This feature may be added in future versions.

**Q: What units are used for measurements?**
A: The application uses abstract units. You can interpret them as pixels, inches, centimeters, or any unit system you prefer.

**Q: Are the area and volume calculations accurate?**
A: Yes, calculations use Java's Math library with double precision floating-point arithmetic.

---

## 9. Tips and Best Practices

1. **Start with moderate values** (50-200) to see shapes clearly
2. **Try different sizes** to understand how parameters affect the shape
3. **Use the Clear button** between different shapes for clarity
4. **Experiment with decimals** for precise measurements
5. **Compare 2D and 3D versions** of similar shapes (Circle vs Sphere, Square vs Cube)

---

## 10. Contact Information

**Project:** CMSC 335 Project 2
**Course:** Object Oriented and Concurrent Programming
**Author:** Joe Merrill
**Date:** February 1, 2026

For questions or issues related to this application, please contact your course instructor.

---

## Appendix A: Keyboard Shortcuts

- **Tab:** Navigate between input fields
- **Enter:** Submit form (equivalent to clicking Display Shape)
- **Alt+F4 (Windows) / Cmd+Q (Mac):** Close application

## Appendix B: Parameter Ranges

Recommended parameter ranges for optimal display:

| Shape | Parameter 1 | Parameter 2 |
|-------|-------------|-------------|
| Circle | 10-350 | - |
| Square | 10-500 | - |
| Rectangle | 10-600 | 10-400 |
| Triangle | 10-600 | 10-400 |
| Sphere | 10-280 | - |
| Cube | 10-220 | - |
| Cylinder | 10-180 | 10-280 |
| Cone | 10-180 | 10-280 |
| Torus | 40-220 | 10-70 |

---

*Thank you for using the Shape Viewer Application!*
