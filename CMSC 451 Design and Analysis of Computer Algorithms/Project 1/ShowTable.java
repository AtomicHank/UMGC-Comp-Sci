import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ShowTable {
    private static final int RUNS_PER_SIZE = 40;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShowTable::showReport);
    }

    private static void showReport() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();
        String[] columns = {"Size", "Avg Count", "Count CV %", "Avg Time", "Time CV %"};

        try {
            List<String> lines = Files.readAllLines(file.toPath());
            String[][] rows = new String[lines.size()][columns.length];
            for (int row = 0; row < lines.size(); row++) {
                rows[row] = parseRow(lines.get(row));
            }

            JTable table = createReportTable(rows, columns);
            JFrame frame = new JFrame(file.getName());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(new JScrollPane(table));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (IOException exception) {
            throw new RuntimeException("Unable to read " + file.getName(), exception);
        }
    }

    private static JTable createReportTable(String[][] rows, String[] columns) {
        DefaultTableModel model = new DefaultTableModel(rows, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFillsViewportHeight(true);

        DefaultTableCellRenderer rightAlignedRenderer = new DefaultTableCellRenderer();
        rightAlignedRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

        for (int column = 0; column < table.getColumnModel().getColumnCount(); column++) {
            table.getColumnModel().getColumn(column).setCellRenderer(rightAlignedRenderer);
        }

        return table;
    }

    private static String[] parseRow(String line) {
        String[] parts = line.trim().split("\\s+");
        int size = Integer.parseInt(parts[0]);
        List<Double> counts = new ArrayList<Double>();
        List<Double> times = new ArrayList<Double>();

        for (int index = 1; index + 1 < parts.length; index += 2) {
            counts.add(Double.parseDouble(parts[index]));
            times.add(Double.parseDouble(parts[index + 1]));
        }

        if (counts.size() != RUNS_PER_SIZE || times.size() != RUNS_PER_SIZE) {
            throw new IllegalArgumentException("Expected 40 runs for size " + size);
        }

        double averageCount = average(counts);
        double countCv = coefficientOfVariation(counts, averageCount);
        double averageTime = average(times);
        double timeCv = coefficientOfVariation(times, averageTime);

        return new String[] {
            Integer.toString(size),
            formatNumber(averageCount),
            formatPercent(countCv),
            formatNumber(averageTime),
            formatPercent(timeCv)
        };
    }

    private static double average(List<Double> values) {
        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    private static double coefficientOfVariation(List<Double> values, double mean) {
        if (mean == 0.0) {
            return 0.0;
        }
        double sumSquares = 0.0;
        for (double value : values) {
            double difference = value - mean;
            sumSquares += difference * difference;
        }
        double standardDeviation = Math.sqrt(sumSquares / values.size());
        return (standardDeviation / mean) * 100.0;
    }

    private static String formatNumber(double value) {
        return String.format(Locale.US, "%,.2f", value);
    }

    private static String formatPercent(double value) {
        return String.format(Locale.US, "%.2f%%", value);
    }
}
