import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class TrafficSimulationFrame extends JFrame {
    private final JLabel clockLabel = new JLabel("--:--:--");
    private final JLabel elapsedLabel = new JLabel("0 s");

    private final DefaultTableModel intersectionTableModel = new DefaultTableModel(
        new Object[] {"Intersection", "X (m)", "Light"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final DefaultTableModel carTableModel = new DefaultTableModel(
        new Object[] {"Car", "X (m)", "Y (m)", "Speed (km/h)", "State"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final SimulationModel model = new SimulationModel();
    private final SimulationControl control = new SimulationControl();

    private final List<Thread> lightThreads = new ArrayList<>();
    private Thread clockThread;
    private Thread carThread;

    public TrafficSimulationFrame() {
        super("CMSC 335 - Traffic Simulation");

        setLayout(new BorderLayout(8, 8));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 620);
        setLocationRelativeTo(null);

        JPanel header = buildHeaderPanel();
        JSplitPane center = buildCenterPanel();
        JPanel controls = buildControls();

        add(header, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);

        refreshView();
    }

    private JPanel buildHeaderPanel() {
        JPanel header = new JPanel(new GridLayout(1, 2, 8, 8));
        header.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));
        header.add(panelWithTitle("Current Time (1s)", clockLabel));
        header.add(panelWithTitle("Elapsed", elapsedLabel));
        return header;
    }

    private JSplitPane buildCenterPanel() {
        JTable intersectionTable = new JTable(intersectionTableModel);
        JTable carTable = new JTable(carTableModel);

        JScrollPane intersectionPane = new JScrollPane(intersectionTable);
        intersectionPane.setBorder(BorderFactory.createTitledBorder("Traffic Lights: Real-Time Intersections"));

        JScrollPane carPane = new JScrollPane(carTable);
        carPane.setBorder(BorderFactory.createTitledBorder("Cars: X, Y, Speed"));

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, intersectionPane, carPane);
        split.setResizeWeight(0.35);
        return split;
    }

    private JPanel buildControls() {
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton continueButton = new JButton("Continue");
        JButton stopButton = new JButton("Stop");
        JButton addCarButton = new JButton("Add Car");
        JButton addIntersectionButton = new JButton("Add Intersection");

        startButton.addActionListener(e -> startSimulation());
        pauseButton.addActionListener(e -> control.pause());
        continueButton.addActionListener(e -> control.resume());
        stopButton.addActionListener(e -> stopSimulation());
        addCarButton.addActionListener(e -> {
            model.addCar();
            refreshView();
        });
        addIntersectionButton.addActionListener(e -> {
            int intersectionId = model.addIntersection();
            if (control.isRunning()) {
                startLightThread(intersectionId);
            }
            refreshView();
        });

        controls.add(startButton);
        controls.add(pauseButton);
        controls.add(continueButton);
        controls.add(stopButton);
        controls.add(addCarButton);
        controls.add(addIntersectionButton);

        return controls;
    }

    private JPanel panelWithTitle(String title, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
        panel.add(valueLabel, BorderLayout.CENTER);
        return panel;
    }

    private void startSimulation() {
        if (!control.start()) {
            return;
        }

        model.resetForNewRun();
        refreshView();

        Runnable uiRefresh = () -> SwingUtilities.invokeLater(this::refreshView);

        clockThread = new Thread(new ClockWorker(model, control, uiRefresh), "clock-worker");
        carThread = new Thread(new CarWorker(model, control, uiRefresh), "car-worker");

        clockThread.start();
        carThread.start();

        for (Integer intersectionId : model.getIntersectionIds()) {
            startLightThread(intersectionId);
        }
    }

    private void startLightThread(int intersectionId) {
        Runnable uiRefresh = () -> SwingUtilities.invokeLater(this::refreshView);
        Thread thread = new Thread(
            new TrafficLightWorker(intersectionId, model, control, uiRefresh),
            "light-worker-" + intersectionId
        );
        lightThreads.add(thread);
        thread.start();
    }

    private void stopSimulation() {
        control.stop();
        interruptIfRunning(clockThread);
        interruptIfRunning(carThread);
        for (Thread lightThread : lightThreads) {
            interruptIfRunning(lightThread);
        }
        lightThreads.clear();
    }

    private void interruptIfRunning(Thread thread) {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
    }

    private void refreshView() {
        SimulationModel.Snapshot snapshot = model.snapshot();

        clockLabel.setText(snapshot.currentClock);
        elapsedLabel.setText(snapshot.elapsedSeconds + " s");

        intersectionTableModel.setRowCount(0);
        for (SimulationModel.IntersectionState intersection : snapshot.intersections) {
            intersectionTableModel.addRow(new Object[] {
                "Intersection " + intersection.getId(),
                String.format("%.1f", intersection.getXPositionMeters()),
                intersection.getLightColor().name()
            });
        }

        carTableModel.setRowCount(0);
        for (SimulationModel.CarState car : snapshot.cars) {
            String status = car.isWaitingAtRed()
                ? "Stopped at red (I" + car.getWaitingIntersectionId() + ")"
                : "Moving";

            carTableModel.addRow(new Object[] {
                "Car " + car.getId(),
                String.format("%.1f", car.getXPositionMeters()),
                String.format("%.1f", car.getYPositionMeters()),
                String.format("%.1f", car.getSpeedKmh()),
                status
            });
        }
    }
}
