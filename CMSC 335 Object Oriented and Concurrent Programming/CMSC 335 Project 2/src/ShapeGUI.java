import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * File: ShapeGUI.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Main GUI application for displaying shapes.
 *          Uses Swing components with event handlers and listeners.
 *          Demonstrates ActionListener and ItemListener interfaces.
 */
public class ShapeGUI extends JFrame implements ActionListener, ItemListener {

    // GUI Components - has-a relationships
    private final JComboBox<String> shapeSelector;
    private final JLabel param1Label = new JLabel("Parameter 1:");
    private final JLabel param2Label = new JLabel("Parameter 2:");
    private final JTextField param1Field = new JTextField(10);
    private final JTextField param2Field = new JTextField(10);
    private final JButton displayButton = new JButton("Display Shape");
    private final JButton clearButton = new JButton("Clear");
    private final ShapePanel shapePanel = new ShapePanel();

    /**
     * Constructor - initializes the GUI
     */
    public ShapeGUI() {
        setTitle("CMSC 335 Project 2 - Shape Viewer");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize shape selector
        shapeSelector = new JComboBox<>(new String[] {
            "Circle", "Square", "Rectangle", "Triangle",
            "Sphere", "Cube", "Cone", "Cylinder", "Torus"
        });
        shapeSelector.addItemListener(this); // Register item listener

        // Build and add components
        JPanel controlPanel = buildControlPanel();
        setLayout(new BorderLayout(10, 10));
        add(controlPanel, BorderLayout.NORTH);
        add(shapePanel, BorderLayout.CENTER);

        // Initialize UI for first shape
        updateParameterFields((String) shapeSelector.getSelectedItem());
    }

    /**
     * Builds the control panel with input fields and buttons
     * @return JPanel containing all controls
     */
    private JPanel buildControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Add shape selector
        panel.add(new JLabel("Shape:"));
        panel.add(shapeSelector);
        panel.add(Box.createHorizontalStrut(15));

        // Add parameter inputs
        panel.add(param1Label);
        panel.add(param1Field);
        panel.add(param2Label);
        panel.add(param2Field);
        panel.add(Box.createHorizontalStrut(15));

        // Add buttons and register action listeners
        displayButton.addActionListener(this);
        clearButton.addActionListener(this);
        panel.add(displayButton);
        panel.add(clearButton);

        return panel;
    }

    /**
     * ItemListener implementation - handles shape selection changes
     * @param e the ItemEvent triggered by selection change
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectedShape = (String) e.getItem();
            updateParameterFields(selectedShape);
            shapePanel.setStatus("Enter parameters for " + selectedShape + " and click Display.");
        }
    }

    /**
     * Updates parameter field labels and visibility based on selected shape
     * @param shapeName the name of the selected shape
     */
    private void updateParameterFields(String shapeName) {
        String[] labels = ShapeFactory.getParameterLabels(shapeName);

        // Configure first parameter
        if (labels.length >= 1) {
            param1Label.setText(labels[0] + ":");
            param1Field.setVisible(true);
            param1Label.setVisible(true);
        } else {
            param1Field.setVisible(false);
            param1Label.setVisible(false);
        }

        // Configure second parameter
        if (labels.length >= 2) {
            param2Label.setText(labels[1] + ":");
            param2Field.setVisible(true);
            param2Label.setVisible(true);
        } else {
            param2Field.setVisible(false);
            param2Label.setVisible(false);
        }

        // Clear previous values
        param1Field.setText("");
        param2Field.setText("");
        revalidate();
        repaint();
    }

    /**
     * ActionListener implementation - handles button clicks
     * @param e the ActionEvent triggered by button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            handleClearAction();
        } else if (e.getSource() == displayButton) {
            handleDisplayAction();
        }
    }

    /**
     * Handles the Clear button action
     */
    private void handleClearAction() {
        param1Field.setText("");
        param2Field.setText("");
        shapePanel.setShape(null);
        shapePanel.setStatus("Cleared. Select a shape and enter parameters.");
    }

    /**
     * Handles the Display button action
     */
    private void handleDisplayAction() {
        String shapeName = (String) shapeSelector.getSelectedItem();

        try {
            // Read and validate parameters
            double[] params = readParameters(shapeName);

            // Create shape using factory
            Shape shape = ShapeFactory.createShape(shapeName, params);

            // Display shape
            shapePanel.setShape(shape);
            shapePanel.setStatus("Displaying " + shapeName);

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid numeric values for all parameters.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException iae) {
            JOptionPane.showMessageDialog(this,
                iae.getMessage(),
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Reads and validates parameters from input fields
     * @param shapeName the name of the shape
     * @return array of validated parameter values
     * @throws NumberFormatException if input cannot be parsed as double
     * @throws IllegalArgumentException if parameters are invalid
     */
    private double[] readParameters(String shapeName) {
        String[] labels = ShapeFactory.getParameterLabels(shapeName);

        if (labels.length == 1) {
            double param1 = Double.parseDouble(param1Field.getText().trim());
            if (param1 <= 0) {
                throw new IllegalArgumentException("Parameter must be greater than 0.");
            }
            return new double[]{param1};

        } else if (labels.length == 2) {
            double param1 = Double.parseDouble(param1Field.getText().trim());
            double param2 = Double.parseDouble(param2Field.getText().trim());
            if (param1 <= 0 || param2 <= 0) {
                throw new IllegalArgumentException("All parameters must be greater than 0.");
            }
            return new double[]{param1, param2};
        }

        return new double[]{};
    }

    /**
     * Main method - entry point of the application
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            ShapeGUI gui = new ShapeGUI();
            gui.setVisible(true);
        });
    }
}
