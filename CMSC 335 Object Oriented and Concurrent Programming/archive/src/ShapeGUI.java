import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * File: ShapeGUI.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Project 2 Swing GUI. User selects a shape, enters parameters, and displays it.
 */
public class ShapeGUI extends JFrame implements ActionListener, ItemListener {

    private final JComboBox<String> shapeSelector;

    private final JLabel param1Label = new JLabel("Param 1:");
    private final JLabel param2Label = new JLabel("Param 2:");

    private final JTextField param1Field = new JTextField(8);
    private final JTextField param2Field = new JTextField(8);

    private final JButton displayButton = new JButton("Display Shape");
    private final JButton clearButton = new JButton("Clear");

    private final ShapePanel displayPanel = new ShapePanel();

    public ShapeGUI() {
        setTitle("CMSC 335 Project 2 - Shape Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        shapeSelector = new JComboBox<>(new String[] {
                "Circle", "Square", "Rectangle", "Triangle",
                "Sphere", "Cube", "Cone", "Cylinder", "Torus"
        });
        shapeSelector.addItemListener(this);

        JPanel controls = buildControlPanel();

        setLayout(new BorderLayout());
        add(controls, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);

        // Initialize parameter labels/visibility
        updateParamUI((String) shapeSelector.getSelectedItem());
    }

    private JPanel buildControlPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

        p.add(new JLabel("Shape:"));
        p.add(shapeSelector);

        p.add(Box.createHorizontalStrut(10));

        p.add(param1Label);
        p.add(param1Field);

        p.add(param2Label);
        p.add(param2Field);

        p.add(Box.createHorizontalStrut(10));

        displayButton.addActionListener(this);
        clearButton.addActionListener(this);

        p.add(displayButton);
        p.add(clearButton);

        return p;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String shapeName = (String) e.getItem();
            updateParamUI(shapeName);
            displayPanel.setStatus("Ready: enter parameters for " + shapeName + " then click Display.");
        }
    }

    private void updateParamUI(String shapeName) {
        String[] labels = ShapeFactory.paramLabels(shapeName);

        if (labels.length >= 1) {
            param1Label.setText(labels[0] + ":");
            param1Field.setVisible(true);
            param1Label.setVisible(true);
        } else {
            param1Field.setVisible(false);
            param1Label.setVisible(false);
        }

        if (labels.length >= 2) {
            param2Label.setText(labels[1] + ":");
            param2Field.setVisible(true);
            param2Label.setVisible(true);
        } else {
            param2Field.setVisible(false);
            param2Label.setVisible(false);
        }

        // Clear old values when changing shape
        param1Field.setText("");
        param2Field.setText("");
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            param1Field.setText("");
            param2Field.setText("");
            displayPanel.setShape(null);
            displayPanel.setStatus("Cleared. Select a shape and enter parameters.");
            return;
        }

        if (e.getSource() == displayButton) {
            String shapeName = (String) shapeSelector.getSelectedItem();

            try {
                double[] dims = readDimsFor(shapeName);
                Shape s = ShapeFactory.create(shapeName, dims);
                displayPanel.setShape(s);
                displayPanel.setStatus("Displaying " + shapeName + ".");
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this,
                        "Please enter valid numeric values for the parameters.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this,
                        iae.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double[] readDimsFor(String shapeName) {
        String[] labels = ShapeFactory.paramLabels(shapeName);

        if (labels.length == 1) {
            double p1 = Double.parseDouble(param1Field.getText().trim());
            if (p1 <= 0) throw new IllegalArgumentException("Parameter must be > 0.");
            return new double[]{p1};
        } else if (labels.length == 2) {
            double p1 = Double.parseDouble(param1Field.getText().trim());
            double p2 = Double.parseDouble(param2Field.getText().trim());
            if (p1 <= 0 || p2 <= 0) throw new IllegalArgumentException("Parameters must be > 0.");
            return new double[]{p1, p2};
        }
        return new double[]{};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShapeGUI().setVisible(true);
        });
    }
}
