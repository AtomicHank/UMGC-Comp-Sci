CMSC 335 Project 2 - Shape Viewer
================================

Overview
--------
This project implements a Java Swing GUI that allows a user to select a shape, input dimensional
parameters, and display that shape. 2D shapes are drawn; 3D shapes are displayed using PNG images.

Files
-----
src/   : Java source files (each class is separate .java file)
images/: PNG images for 3D shapes (sphere.png, cube.png, cone.png, cylinder.png, torus.png)
docs/  : Documentation (this README, test plan template, UML diagram)

How to compile
--------------
From the project root folder:

    javac -d bin src/*.java

How to run
----------
From the project root folder:

    java -cp bin ShapeGUI

Notes:
------
- The app expects the "images" folder to be in the working directory (same level as src/ and bin/).

Assumptions & Limitations
-------------------------
- 3D shapes use representative images (as suggested in project instructions).
- Inputs are positive real numbers.
- 2D drawing uses pixels as units for display only; math uses the entered values directly.
