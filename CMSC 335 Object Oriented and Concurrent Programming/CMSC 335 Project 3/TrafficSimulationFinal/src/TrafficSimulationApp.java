import javax.swing.SwingUtilities;

public class TrafficSimulationApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TrafficSimulationFrame frame = new TrafficSimulationFrame();
            frame.setVisible(true);
        });
    }
}
