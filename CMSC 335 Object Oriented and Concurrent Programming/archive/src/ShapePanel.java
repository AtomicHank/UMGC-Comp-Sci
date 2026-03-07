import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * File: ShapePanel.java
 * Author: Joe
 * Date: 2026-02-01
 * Purpose: Displays drawn 2D and 3D shapes.
 */
public class ShapePanel extends JPanel {
    private Shape currentShape;
    private String statusText = "Select a shape, enter parameters, then click Display.";

    public void setShape(Shape shape) {
        this.currentShape = shape;
        repaint();
    }

    public void setStatus(String text) {
        this.statusText = text;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Background guide text
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(statusText, 10, 20);

        if (currentShape == null) {
            return;
        }

        int w = getWidth();
        int h = getHeight();

        if (currentShape instanceof TwoDimensionalShape) {
            g2.setColor(new Color(20, 80, 200));
            draw2D(g2, currentShape, w, h);
        } else {
            draw3D(g2, currentShape, w, h);
        }
    }

    private void draw2D(Graphics2D g2, Shape shape, int w, int h) {
        int cx = w / 2;
        int cy = h / 2 + 20;

        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            int r = (int) Math.max(10, Math.min(400, c.getRadius()));
            g2.setStroke(new BasicStroke(3));
            g2.drawOval(cx - r, cy - r, r * 2, r * 2);
            g2.drawString("Area = " + String.format("%.2f", c.getArea()), 10, 40);
        } else if (shape instanceof Square) {
            Square s = (Square) shape;
            int side = (int) Math.max(10, Math.min(600, s.getSide()));
            g2.setStroke(new BasicStroke(3));
            g2.drawRect(cx - side / 2, cy - side / 2, side, side);
            g2.drawString("Area = " + String.format("%.2f", s.getArea()), 10, 40);
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            int rw = (int) Math.max(10, Math.min(700, r.getWidth()));
            int rh = (int) Math.max(10, Math.min(500, r.getHeight()));
            g2.setStroke(new BasicStroke(3));
            g2.drawRect(cx - rw / 2, cy - rh / 2, rw, rh);
            g2.drawString("Area = " + String.format("%.2f", r.getArea()), 10, 40);
        } else if (shape instanceof Triangle) {
            Triangle t = (Triangle) shape;
            int base = (int) Math.max(20, Math.min(700, t.getBase()));
            int th = (int) Math.max(20, Math.min(500, t.getHeight()));
            int x1 = cx - base / 2;
            int y1 = cy + th / 2;
            int x2 = cx + base / 2;
            int y2 = cy + th / 2;
            int x3 = cx;
            int y3 = cy - th / 2;

            g2.setStroke(new BasicStroke(3));
            g2.drawPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
            g2.drawString("Area = " + String.format("%.2f", t.getArea()), 10, 40);
        }
    }

    private void draw3D(Graphics2D g2, Shape shape, int w, int h) {
        // Enable antialiasing for smoother 3D rendering
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = w / 2;
        int cy = h / 2 + 20;

        if (shape instanceof Sphere) {
            drawSphere(g2, (Sphere) shape, cx, cy);
        } else if (shape instanceof Cube) {
            drawCube(g2, (Cube) shape, cx, cy);
        } else if (shape instanceof Cylinder) {
            drawCylinder(g2, (Cylinder) shape, cx, cy);
        } else if (shape instanceof Cone) {
            drawCone(g2, (Cone) shape, cx, cy);
        } else if (shape instanceof Torus) {
            drawTorus(g2, (Torus) shape, cx, cy);
        }

        if (shape instanceof ThreeDimensionalShape) {
            double v = ((ThreeDimensionalShape) shape).getVolume();
            g2.setColor(Color.BLACK);
            g2.drawString("Volume = " + String.format("%.2f", v), 10, 40);
        }
    }

    private void drawSphere(Graphics2D g2, Sphere s, int cx, int cy) {
        int r = (int) Math.max(20, Math.min(300, s.getRadius()));

        // Create gradient for 3D effect
        RadialGradientPaint gradient = new RadialGradientPaint(
            cx - r/3, cy - r/3, r * 1.4f,
            new float[]{0.0f, 0.7f, 1.0f},
            new Color[]{new Color(100, 150, 255), new Color(40, 80, 200), new Color(20, 40, 120)}
        );

        g2.setPaint(gradient);
        g2.fillOval(cx - r, cy - r, r * 2, r * 2);

        g2.setColor(new Color(20, 40, 120));
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(cx - r, cy - r, r * 2, r * 2);
    }

    private void drawCube(Graphics2D g2, Cube cube, int cx, int cy) {
        int side = (int) Math.max(20, Math.min(250, cube.getSide()));

        // Isometric projection angles
        double angle = Math.PI / 6; // 30 degrees
        int dx = (int)(side * Math.cos(angle));
        int dy = (int)(side * Math.sin(angle));

        // Front face
        int[] xFront = {cx - side/2, cx + side/2, cx + side/2, cx - side/2};
        int[] yFront = {cy - side/2, cy - side/2, cy + side/2, cy + side/2};

        // Top face (isometric)
        int[] xTop = {cx - side/2, cx + side/2, cx + side/2 + dx, cx - side/2 + dx};
        int[] yTop = {cy - side/2, cy - side/2, cy - side/2 - dy, cy - side/2 - dy};

        // Right face (isometric)
        int[] xRight = {cx + side/2, cx + side/2 + dx, cx + side/2 + dx, cx + side/2};
        int[] yRight = {cy - side/2, cy - side/2 - dy, cy + side/2 - dy, cy + side/2};

        // Draw faces with different shades for depth
        g2.setColor(new Color(80, 120, 220));
        g2.fillPolygon(xFront, yFront, 4);

        g2.setColor(new Color(120, 160, 255));
        g2.fillPolygon(xTop, yTop, 4);

        g2.setColor(new Color(50, 90, 180));
        g2.fillPolygon(xRight, yRight, 4);

        // Draw edges
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawPolygon(xFront, yFront, 4);
        g2.drawPolygon(xTop, yTop, 4);
        g2.drawPolygon(xRight, yRight, 4);
    }

    private void drawCylinder(Graphics2D g2, Cylinder cyl, int cx, int cy) {
        int r = (int) Math.max(15, Math.min(200, cyl.getRadius()));
        int h = (int) Math.max(30, Math.min(300, cyl.getHeight()));

        // Isometric offset for 3D effect
        int offset = r / 3;

        // Back ellipse (top)
        g2.setColor(new Color(50, 90, 180));
        g2.fillOval(cx - r + offset, cy - h/2 - offset, r * 2, r);

        // Cylinder body
        GradientPaint gradient = new GradientPaint(
            cx - r, cy, new Color(40, 80, 200),
            cx + r, cy, new Color(100, 150, 255)
        );
        g2.setPaint(gradient);
        g2.fillRect(cx - r + offset, cy - h/2 - offset + r/2, r * 2, h);

        // Front ellipse (bottom)
        g2.setColor(new Color(80, 120, 220));
        g2.fillOval(cx - r + offset, cy + h/2 - offset, r * 2, r);

        // Edges
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(cx - r + offset, cy - h/2 - offset, r * 2, r);
        g2.drawOval(cx - r + offset, cy + h/2 - offset, r * 2, r);
        g2.drawLine(cx - r + offset, cy - h/2 - offset + r/2, cx - r + offset, cy + h/2 - offset + r/2);
        g2.drawLine(cx + r + offset, cy - h/2 - offset + r/2, cx + r + offset, cy + h/2 - offset + r/2);
    }

    private void drawCone(Graphics2D g2, Cone cone, int cx, int cy) {
        int r = (int) Math.max(15, Math.min(200, cone.getRadius()));
        int h = (int) Math.max(30, Math.min(300, cone.getHeight()));

        int offset = r / 3;

        // Base ellipse
        g2.setColor(new Color(80, 120, 220));
        g2.fillOval(cx - r + offset, cy + h/2 - offset, r * 2, r);

        // Cone sides with gradient
        int[] xCone = {cx + offset, cx - r + offset, cx + r + offset};
        int[] yCone = {cy - h/2 - offset, cy + h/2 - offset + r/2, cy + h/2 - offset + r/2};

        GradientPaint gradient = new GradientPaint(
            cx - r, cy, new Color(40, 80, 200),
            cx + r, cy, new Color(100, 150, 255)
        );
        g2.setPaint(gradient);
        g2.fillPolygon(xCone, yCone, 3);

        // Edges
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(cx - r + offset, cy + h/2 - offset, r * 2, r);
        g2.drawLine(cx + offset, cy - h/2 - offset, cx - r + offset, cy + h/2 - offset + r/2);
        g2.drawLine(cx + offset, cy - h/2 - offset, cx + r + offset, cy + h/2 - offset + r/2);
    }

    private void drawTorus(Graphics2D g2, Torus torus, int cx, int cy) {
        int majorR = (int) Math.max(40, Math.min(250, torus.getMajorRadius()));
        int minorR = (int) Math.max(10, Math.min(80, torus.getMinorRadius()));

        // Draw multiple ellipses to simulate torus
        g2.setStroke(new BasicStroke(2));

        // Outer ring (back)
        g2.setColor(new Color(50, 90, 180));
        g2.fillOval(cx - majorR - minorR/2, cy - (majorR + minorR)/3,
                    (majorR + minorR) * 2, (majorR + minorR) * 2 / 3);

        // Inner hole
        g2.setColor(getBackground());
        g2.fillOval(cx - majorR + minorR/2, cy - (majorR - minorR)/3,
                    (majorR - minorR) * 2, (majorR - minorR) * 2 / 3);

        // Front gradient effect
        GradientPaint gradient = new GradientPaint(
            cx - majorR, cy, new Color(100, 150, 255),
            cx + majorR, cy, new Color(40, 80, 200)
        );
        g2.setPaint(gradient);

        // Draw tube cross-sections for 3D effect
        for (int angle = -90; angle <= 90; angle += 30) {
            double rad = Math.toRadians(angle);
            int x = cx + (int)(majorR * Math.cos(rad));
            int y = cy + (int)(majorR * Math.sin(rad) / 2);
            g2.fillOval(x - minorR/2, y - minorR/2, minorR, minorR);
        }

        // Outline
        g2.setColor(Color.BLACK);
        g2.drawOval(cx - majorR - minorR/2, cy - (majorR + minorR)/3,
                    (majorR + minorR) * 2, (majorR + minorR) * 2 / 3);
        g2.drawOval(cx - majorR + minorR/2, cy - (majorR - minorR)/3,
                    (majorR - minorR) * 2, (majorR - minorR) * 2 / 3);
    }
}
