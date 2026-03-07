import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * File: Week4SimpleGUI.java
 * Author: Joe Merrill 1Feb2026
 * Purpose: Simple Swing GUI demo for CMSC 335 Week 4.
 */
public class Week4SimpleGUI {

    private int counter = 0;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final JLabel clockLabel = new JLabel("Clock: --");
    private final JTextField nameField = new JTextField(12);
    private final JLabel helloLabel = new JLabel("Hello: (enter your name)");
    private final JLabel counterLabel = new JLabel("Counter: 0");

    private final JButton helloButton = new JButton("Say Hello");
    private final JButton incButton = new JButton("Increment");
    private final JButton resetButton = new JButton("Reset");

    // Swing Timer fires ActionEvents on the Event Dispatch Thread (EDT)
    private final Timer clockTimer = new Timer(1000, e -> updateClock());

    private void buildAndShowUI() {
        JFrame frame = new JFrame("Week 4 Simple GUI - Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));

        // Row 1: clock (updates every second)
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(clockLabel);
        root.add(row1);

        // Row 2: name input + hello button
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Name:"));
        row2.add(nameField);
        row2.add(helloButton);
        root.add(row2);

        // Row 3: hello output
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.add(helloLabel);
        root.add(row3);

        // Row 4: counter controls
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row4.add(counterLabel);
        row4.add(incButton);
        row4.add(resetButton);
        root.add(row4);

        // Event handlers (listeners)
        helloButton.addActionListener(this::onSayHello);
        incButton.addActionListener(e -> setCounter(counter + 1));
        resetButton.addActionListener(e -> setCounter(0));

        // Start clock timer
        clockTimer.start();
        updateClock();

        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void onSayHello(ActionEvent e) {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please type a name first.",
                    "Missing Input", JOptionPane.WARNING_MESSAGE);
            nameField.requestFocusInWindow();
            return;
        }
        helloLabel.setText("Hello: " + name + "!");
    }

    private void setCounter(int newValue) {
        counter = Math.max(0, newValue);
        counterLabel.setText("Counter: " + counter);
    }

    private void updateClock() {
        clockLabel.setText("Clock: " + LocalDateTime.now().format(fmt));
    }

    public static void main(String[] args) {
        // Always create Swing GUIs on the EDT
        SwingUtilities.invokeLater(() -> new Week4SimpleGUI().buildAndShowUI());
    }
}