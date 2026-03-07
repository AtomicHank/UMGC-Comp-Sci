# Lessons Learned - CMSC 335 Project 2
## Shape Viewer Application Development

**Author:** Joe Merrill
**Date:** February 1, 2026
**Project:** Shape Viewer GUI Application

---

## Executive Summary

This document reflects on the development process of the Shape Viewer Application, highlighting key learnings, challenges overcome, and best practices identified during implementation. The project successfully demonstrated object-oriented programming principles, GUI development, and software design patterns.

---

## 1. Technical Lessons Learned

### 1.1 Inheritance Hierarchies

**What I Learned:**
Creating a well-designed inheritance hierarchy requires careful planning of the relationship between abstract and concrete classes. The three-level hierarchy (Shape → TwoDimensional/ThreeDimensional → Concrete Shapes) provided clear separation of concerns and code reusability.

**Key Insight:**
Abstract classes should define common behavior while leaving specific implementations to subclasses. The `getArea()` and `getVolume()` methods were appropriately placed at the 2D and 3D levels rather than in the base Shape class.

**Application:**
This pattern can be applied to any domain with hierarchical relationships, such as vehicles, animals, or user interface components.

### 1.2 Factory Design Pattern

**What I Learned:**
The Factory pattern effectively encapsulates object creation logic and makes the code more maintainable. The `ShapeFactory` class centralizes shape creation, making it easy to add new shapes without modifying the GUI code.

**Challenge:**
Initially, I considered creating shapes directly in the GUI class, but this would have violated the Single Responsibility Principle.

**Solution:**
Implementing the Factory pattern separated creation logic from presentation logic, making the codebase more modular and testable.

### 1.3 Event-Driven Programming

**What I Learned:**
Implementing `ActionListener` and `ItemListener` interfaces taught me how event-driven programming works in Swing. Events flow from user actions to listeners to handler methods.

**Best Practice:**
Separating event handling into dedicated methods (`handleDisplayAction()`, `handleClearAction()`) improved code readability compared to putting all logic directly in the listener methods.

**Improvement:**
Future projects should consider using lambda expressions (Java 8+) for simpler event handling.

### 1.4 Input Validation

**What I Learned:**
Robust input validation is critical for user-facing applications. Using try-catch blocks to handle `NumberFormatException` and custom validation for business rules (positive numbers only) prevents crashes and provides better user experience.

**Challenge:**
Determining where to place validation logic - in the GUI layer or domain layer.

**Solution:**
Implemented validation at both levels: GUI validates format (numeric), domain validates business rules (positive values).

### 1.5 Graphics Programming

**What I Learned:**
Java 2D graphics provide powerful tools for creating visual representations. Using `Graphics2D`, gradients, and antialiasing significantly improved the appearance of rendered shapes.

**Key Techniques:**
- `RadialGradientPaint` for 3D sphere effect
- `GradientPaint` for cylinder and cone shading
- `RenderingHints.VALUE_ANTIALIAS_ON` for smooth edges
- Isometric projection formulas for cube rendering

**Challenge:**
Creating convincing 3D representations using only 2D drawing primitives.

**Solution:**
Applied shading, perspective, and isometric projection techniques to create depth illusion.

---

## 2. Design Lessons Learned

### 2.1 Separation of Concerns

**What I Learned:**
Keeping rendering logic separate from business logic (shape calculations) and GUI logic (event handling) made the code more maintainable and testable.

**Architecture:**
- **Model:** Shape classes with calculations
- **View:** ShapePanel for rendering
- **Controller:** ShapeGUI for event handling

### 2.2 Code Documentation

**What I Learned:**
Comprehensive Javadoc comments at the beginning of the project saved time later. Each method has clear documentation of parameters, return values, and purpose.

**Best Practice:**
Write documentation as you code, not after. Future-me appreciated past-me's thorough comments.

### 2.3 Naming Conventions

**What I Learned:**
Consistent, descriptive naming improves code readability. Using verbs for methods (`renderSphere()`, `handleDisplayAction()`) and nouns for classes (`ShapeFactory`, `Circle`) follows Java conventions.

---

## 3. Development Process Lessons

### 3.1 Incremental Development

**What Worked:**
Building the application in stages:
1. Shape hierarchy and calculations
2. Factory pattern
3. Basic GUI structure
4. Rendering logic
5. Input validation

This allowed testing at each stage rather than debugging everything at once.

### 3.2 Testing Strategy

**What I Learned:**
Testing with extreme values (very small, very large, negative) revealed edge cases I hadn't considered initially.

**Examples:**
- Very large radius values caused shapes to overflow the display
- Zero values passed numeric validation but crashed volume calculations
- Decimal inputs needed handling

**Solution:**
Added display size limits and enhanced validation rules.

### 3.3 Version Control

**What I Learned:**
Making frequent, small commits with descriptive messages would have made it easier to track changes and revert if needed.

**Recommendation:**
Use Git with meaningful commit messages for future projects.

---

## 4. Challenges and Solutions

### Challenge 1: Torus Rendering

**Problem:**
The torus (donut shape) was the most complex 3D shape to render using 2D graphics.

**Solution:**
Drew multiple ellipses (outer ring, inner hole, tube cross-sections) with strategic layering to create the donut appearance.

**Lesson:**
Complex shapes can be decomposed into simpler primitives.

### Challenge 2: Dynamic Parameter Fields

**Problem:**
Different shapes require different numbers of parameters (1 or 2), and labels need to update dynamically.

**Solution:**
Used `setVisible()` to show/hide fields and `setText()` to update labels based on selected shape.

**Lesson:**
Swing components can be dynamically configured at runtime for flexible UIs.

### Challenge 3: Maintaining Aspect Ratio

**Problem:**
Initially, shapes distorted when window was resized.

**Solution:**
Calculated center position dynamically based on panel dimensions and clamped shape sizes to reasonable limits.

**Lesson:**
Responsive UI design requires dynamic layout calculations.

---

## 5. Best Practices Identified

### 5.1 Code Organization

1. **One class per file** improves navigation
2. **Package organization** (would be beneficial for larger projects)
3. **Consistent formatting** (indentation, braces style)

### 5.2 Error Handling

1. **User-friendly error messages** instead of stack traces
2. **Graceful degradation** (invalid input doesn't crash app)
3. **Input validation at multiple layers**

### 5.3 User Experience

1. **Clear status messages** keep users informed
2. **Responsive UI** (immediate feedback on actions)
3. **Intuitive controls** (labels match shape requirements)

---

## 6. Areas for Improvement

### 6.1 What Could Be Better

1. **Unit Tests:** Should have written JUnit tests for calculation methods
2. **Configuration File:** Hard-coded values (colors, sizes) could be externalized
3. **Internationalization:** Support for multiple languages
4. **Accessibility:** Keyboard navigation could be improved

### 6.2 Advanced Features for Future Versions

1. **Animation:** Rotate 3D shapes for better visualization
2. **Multiple Shapes:** Display several shapes simultaneously
3. **Save/Load:** Persist shape configurations
4. **Export:** Save rendered shapes as images
5. **Comparison Mode:** Show multiple shapes side-by-side

---

## 7. Application to Future Projects

### 7.1 Transferable Skills

- **OOP Design:** Inheritance, polymorphism, encapsulation
- **Design Patterns:** Factory, MVC
- **GUI Development:** Swing, event handling
- **Testing:** Systematic test case development
- **Documentation:** Code comments, user guides, technical documentation

### 7.2 Professional Development

This project reinforced the importance of:
- **Planning before coding** (class diagrams, architecture)
- **Iterative development** (build, test, refine)
- **User-centered design** (intuitive interfaces)
- **Code quality** (readability, maintainability)

---

## 8. Conclusion

The Shape Viewer Application project provided valuable hands-on experience with object-oriented programming principles and GUI development. The most significant lesson was the importance of good design—spending time on architecture upfront saved considerable debugging time later.

The combination of inheritance hierarchies, design patterns, and event-driven programming created a maintainable, extensible codebase. The project successfully met all requirements while providing a solid foundation for future enhancements.

### Key Takeaways

1. **Good design matters:** Well-structured code is easier to debug and extend
2. **Users come first:** Input validation and error handling prevent frustration
3. **Documentation is investment:** Clear comments and guides save time
4. **Testing is essential:** Systematic testing reveals edge cases
5. **Incremental development works:** Build, test, refine, repeat

### Final Thoughts

This project has strengthened my understanding of Java, object-oriented design, and software engineering best practices. The lessons learned will directly apply to future coursework and professional software development.

---

**Prepared by:** Joe Merrill
**Course:** CMSC 335 Object Oriented and Concurrent Programming
**Date:** February 1, 2026
