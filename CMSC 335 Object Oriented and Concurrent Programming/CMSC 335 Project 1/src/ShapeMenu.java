import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ShapeMenu.java; 18JAN2026; Joseph A. Merrill;
 * Command-line menu system for constructing and calculating properties of shapes
 */
public class ShapeMenu {
    private Scanner scanner;
    
    public ShapeMenu() {
        scanner = new Scanner(System.in);
    }
    
    public void run() {
        System.out.println("********* Welcome to the Java OO Shapes Program **********");
        
        boolean continueRunning = true;
        
        while (continueRunning) {
            displayMenu();
            
            int choice = getValidMenuChoice();
            
            if (choice == 10) {
                continueRunning = false;
                displayExitMessage();
            } else {
                processShapeConstruction(choice);
                continueRunning = askToContinue();
            }
        }
        
        scanner.close();
    }
    
    private void displayMenu() {
        System.out.println("\nSelect from the menu below:");
        System.out.println("1. Construct a Circle");
        System.out.println("2. Construct a Rectangle");
        System.out.println("3. Construct a Square");
        System.out.println("4. Construct a Triangle");
        System.out.println("5. Construct a Sphere");
        System.out.println("6. Construct a Cube");
        System.out.println("7. Construct a Cone");
        System.out.println("8. Construct a Cylinder");
        System.out.println("9. Construct a Torus");
        System.out.println("10. Exit the program");
    }
    
    private int getValidMenuChoice() {
        int choice = -1;
        boolean valid = false;
        
        while (!valid) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= 10) {
                    valid = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
            }
        }
        
        return choice;
    }
    
    private void processShapeConstruction(int choice) {
        try {
            Shape shape = null;
            
            switch (choice) {
                case 1:
                    shape = constructCircle();
                    break;
                case 2:
                    shape = constructRectangle();
                    break;
                case 3:
                    shape = constructSquare();
                    break;
                case 4:
                    shape = constructTriangle();
                    break;
                case 5:
                    shape = constructSphere();
                    break;
                case 6:
                    shape = constructCube();
                    break;
                case 7:
                    shape = constructCone();
                    break;
                case 8:
                    shape = constructCylinder();
                    break;
                case 9:
                    shape = constructTorus();
                    break;
            }
            
            if (shape != null) {
                System.out.println(shape);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private Circle constructCircle() {
        System.out.println("You have selected a Circle");
        double radius = getPositiveDouble("What is the radius?");
        return new Circle(radius);
    }
    
    private Rectangle constructRectangle() {
        System.out.println("You have selected a Rectangle");
        double length = getPositiveDouble("What is the length?");
        double width = getPositiveDouble("What is the width?");
        return new Rectangle(length, width);
    }
    
    private Square constructSquare() {
        System.out.println("You have selected a Square");
        double side = getPositiveDouble("What is the side length?");
        return new Square(side);
    }
    
    private Triangle constructTriangle() {
        System.out.println("You have selected a Triangle");
        double base = getPositiveDouble("What is the base?");
        double height = getPositiveDouble("What is the height?");
        return new Triangle(base, height);
    }
    
    private Sphere constructSphere() {
        System.out.println("You have selected a Sphere");
        double radius = getPositiveDouble("What is the radius?");
        return new Sphere(radius);
    }
    
    private Cube constructCube() {
        System.out.println("You have selected a Cube");
        double side = getPositiveDouble("What is the side length?");
        return new Cube(side);
    }
    
    private Cone constructCone() {
        System.out.println("You have selected a Cone");
        double radius = getPositiveDouble("What is the radius?");
        double height = getPositiveDouble("What is the height?");
        return new Cone(radius, height);
    }
    
    private Cylinder constructCylinder() {
        System.out.println("You have selected a Cylinder");
        double radius = getPositiveDouble("What is the radius?");
        double height = getPositiveDouble("What is the height?");
        return new Cylinder(radius, height);
    }
    
    private Torus constructTorus() {
        System.out.println("You have selected a Torus");
        double majorRadius = getPositiveDouble("What is the major radius (distance from center to tube center)?");
        double minorRadius = getPositiveDouble("What is the minor radius (tube radius)?");
        return new Torus(majorRadius, minorRadius);
    }
    
    private double getPositiveDouble(String prompt) {
        double value = -1;
        boolean valid = false;
        
        while (!valid) {
            System.out.println(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine().trim());
                if (value > 0) {
                    valid = true;
                } else {
                    System.out.println("Value must be positive. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        
        return value;
    }
    
    private boolean askToContinue() {
        System.out.println("Would you like to continue? (Y or N)");
        
        while (true) {
            String response = scanner.nextLine().trim().toUpperCase();
            
            if (response.equals("Y") || response.equals("YES")) {
                return true;
            } else if (response.equals("N") || response.equals("NO")) {
                displayExitMessage();
                return false;
            } else {
                System.out.println("Sorry I do not understand. Enter Y or N");
            }
        }
    }
    
    private void displayExitMessage() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' HH:mm:ss");
        
        System.out.println("\nThanks for using the program. Today is " + now.format(formatter));
    }
    
    public static void main(String[] args) {
        ShapeMenu menu = new ShapeMenu();
        menu.run();
    }
}
