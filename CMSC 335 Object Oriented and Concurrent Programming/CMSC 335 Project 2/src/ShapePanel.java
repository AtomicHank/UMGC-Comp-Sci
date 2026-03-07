import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * File: ShapePanel.java
 * Author: Joe Merrill
 * Date: February 1, 2026
 * Purpose: Custom JPanel for rendering 2D and 3D shapes.
 *          2D shapes are drawn programmatically.
 *          3D shapes are rendered with isometric projections and gradients.
 */
public class ShapePanel extends JPanel {
    private Shape currentShape;  // has-a relationship: ShapePanel has a Shape
    private String statusText = "Select a shape and enter parameters to begin.";

    /**
     * Sets the shape to be displayed
     * @param shape the Shape object to display
     */
    public void setShape(Shape shape) {
        this.currentShape = shape;
        repaint();
    }

    /**
     * Sets the status text displayed at the top of the panel
     * @param text the status message
     */
    public void setStatus(String text) {
        this.statusText = text;
        repaint();
    }

    /**
     * Paints the component with the current shape
     * @param g Graphics object for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Enable antialiasing for smooth rendering
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Display status text
        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g2.drawString(statusText, 10, 20);

        if (currentShape == null) {
            return;
        }

        int w = getWidth();
        int h = getHeight();
        int cx = w / 2;
        int cy = h / 2 + 20;

        // Render based on shape type
        if (currentShape instanceof TwoDimensionalShape) {
            render2DShape(g2, currentShape, cx, cy);
        } else if (currentShape instanceof ThreeDimensionalShape) {
            render3DShape(g2, currentShape, cx, cy);
        }
    }

    /**
     * Renders a 2D shape
     * @param g2 Graphics2D object
     * @param shape the 2D shape to render
     * @param cx center x coordinate
     * @param cy center y coordinate
     */
    private void render2DShape(Graphics2D g2, Shape shape, int cx, int cy) {
        g2.setColor(new Color(20, 80, 200));
        g2.setStroke(new BasicStroke(3));

        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            int r = (int) Math.min(600, circle.getRadius());
            g2.drawOval(cx - r, cy - r, r * 2, r * 2);
            g2.drawString("Area = " + String.format("%.2f", circle.getArea()), 10, 40);

        } else if (shape instanceof Square) {
            Square square = (Square) shape;
            int side = (int) Math.min(800, square.getSide());
            g2.drawRect(cx - side / 2, cy - side / 2, side, side);
            g2.drawString("Area = " + String.format("%.2f", square.getArea()), 10, 40);

        } else if (shape instanceof Rectangle) {
            Rectangle rect = (Rectangle) shape;
            int rw = (int) Math.min(1000, rect.getWidth());
            int rh = (int) Math.min(650, rect.getHeight());
            g2.drawRect(cx - rw / 2, cy - rh / 2, rw, rh);
            g2.drawString("Area = " + String.format("%.2f", rect.getArea()), 10, 40);

        } else if (shape instanceof Triangle) {
            Triangle tri = (Triangle) shape;
            int base = (int) Math.min(1000, tri.getBase());
            int th = (int) Math.min(650, tri.getHeight());

            int[] xPoints = {cx - base / 2, cx + base / 2, cx};
            int[] yPoints = {cy + th / 2, cy + th / 2, cy - th / 2};
            g2.drawPolygon(xPoints, yPoints, 3);
            g2.drawString("Area = " + String.format("%.2f", tri.getArea()), 10, 40);
        }
    }

    /**
     * Renders a 3D shape with isometric projection and shading
     * @param g2 Graphics2D object
     * @param shape the 3D shape to render
     * @param cx center x coordinate
     * @param cy center y coordinate
     */
    private void render3DShape(Graphics2D g2, Shape shape, int cx, int cy) {
        if (shape instanceof Sphere) {
            renderSphere(g2, (Sphere) shape, cx, cy);
        } else if (shape instanceof Cube) {
            renderCube(g2, (Cube) shape, cx, cy);
        } else if (shape instanceof Cylinder) {
            renderCylinder(g2, (Cylinder) shape, cx, cy);
        } else if (shape instanceof Cone) {
            renderCone(g2, (Cone) shape, cx, cy);
        } else if (shape instanceof Torus) {
            renderTorus(g2, (Torus) shape, cx, cy);
        }

        // Display volume
        if (shape instanceof ThreeDimensionalShape) {
            double volume = ((ThreeDimensionalShape) shape).getVolume();
            g2.setColor(Color.BLACK);
            g2.drawString("Volume = " + String.format("%.2f", volume), 10, 40);
        }
    }

    /**
     * Renders a sphere with radial gradient for 3D effect
     */
    private void renderSphere(Graphics2D g2, Sphere sphere, int cx, int cy) {
        int r = (int) Math.min(500, sphere.getRadius());

        RadialGradientPaint gradient = new RadialGradientPaint(
            cx - r/3, cy - r/3, r * 1.5f,
            new float[]{0.0f, 0.7f, 1.0f},
            new Color[]{new Color(120, 180, 255), new Color(50, 100, 220), new Color(20, 50, 150)}
        );

        g2.setPaint(gradient);
        g2.fillOval(cx - r, cy - r, r * 2, r * 2);
        g2.setColor(new Color(20, 50, 150));
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(cx - r, cy - r, r * 2, r * 2);
    }

    /**
     * Renders a cube with isometric projection
     */
    private void renderCube(Graphics2D g2, Cube cube, int cx, int cy) {
        int side = (int) Math.min(450, cube.getSide());
        double angle = Math.PI / 6;
        int dx = (int)(side * Math.cos(angle));
        int dy = (int)(side * Math.sin(angle));

        // Front face
        int[] xFront = {cx - side/2, cx + side/2, cx + side/2, cx - side/2};
        int[] yFront = {cy - side/2, cy - side/2, cy + side/2, cy + side/2};
        g2.setColor(new Color(80, 140, 230));
        g2.fillPolygon(xFront, yFront, 4);

        // Top face
        int[] xTop = {cx - side/2, cx + side/2, cx + side/2 + dx, cx - side/2 + dx};
        int[] yTop = {cy - side/2, cy - side/2, cy - side/2 - dy, cy - side/2 - dy};
        g2.setColor(new Color(130, 180, 255));
        g2.fillPolygon(xTop, yTop, 4);

        // Right face
        int[] xRight = {cx + side/2, cx + side/2 + dx, cx + side/2 + dx, cx + side/2};
        int[] yRight = {cy - side/2, cy - side/2 - dy, cy + side/2 - dy, cy + side/2};
        g2.setColor(new Color(50, 100, 200));
        g2.fillPolygon(xRight, yRight, 4);

        // Draw edges
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawPolygon(xFront, yFront, 4);
        g2.drawPolygon(xTop, yTop, 4);
        g2.drawPolygon(xRight, yRight, 4);
    }

    /**
     * Renders a cylinder with 3D effect
     */
    private void renderCylinder(Graphics2D g2, Cylinder cyl, int cx, int cy) {
        int r = (int) Math.min(350, cyl.getRadius());
        int h = (int) Math.min(500, cyl.getHeight());
        int offset = r / 3;

        // Top ellipse
        g2.setColor(new Color(50, 100, 200));
        g2.fillOval(cx - r + offset, cy - h/2 - offset, r * 2, r);

        // Body with gradient
        GradientPaint gradient = new GradientPaint(
            cx - r, cy, new Color(40, 90, 210),
            cx + r, cy, new Color(110, 160, 255)
        );
        g2.setPaint(gradient);
        g2.fillRect(cx - r + offset, cy - h/2 - offset + r/2, r * 2, h);

        // Bottom ellipse
        g2.setColor(new Color(80, 140, 230));
        g2.fillOval(cx - r + offset, cy + h/2 - offset, r * 2, r);

        // Edges
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(cx - r + offset, cy - h/2 - offset, r * 2, r);
        g2.drawOval(cx - r + offset, cy + h/2 - offset, r * 2, r);
        g2.drawLine(cx - r + offset, cy - h/2 - offset + r/2, cx - r + offset, cy + h/2 - offset + r/2);
        g2.drawLine(cx + r + offset, cy - h/2 - offset + r/2, cx + r + offset, cy + h/2 - offset + r/2);
    }

    /**
     * Renders a cone with 3D effect
     */
    private void renderCone(Graphics2D g2, Cone cone, int cx, int cy) {
        int r = (int) Math.min(350, cone.getRadius());
        int h = (int) Math.min(500, cone.getHeight());
        int offset = r / 3;

        // Base ellipse
        g2.setColor(new Color(80, 140, 230));
        g2.fillOval(cx - r + offset, cy + h/2 - offset, r * 2, r);

        // Cone body with gradient
        int[] xCone = {cx + offset, cx - r + offset, cx + r + offset};
        int[] yCone = {cy - h/2 - offset, cy + h/2 - offset + r/2, cy + h/2 - offset + r/2};

        GradientPaint gradient = new GradientPaint(
            cx - r, cy, new Color(40, 90, 210),
            cx + r, cy, new Color(110, 160, 255)
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

    /**
     * Renders a torus (donut shape) with 3D effect
     */
    private void renderTorus(Graphics2D g2, Torus torus, int cx, int cy) {
        int majorR = (int) Math.min(400, torus.getMajorRadius());
        int minorR = (int) Math.min(130, torus.getMinorRadius());

        // Scale factor for vertical compression (isometric view)
        double verticalScale = 0.5;

        // Draw back half of torus (darker)
        g2.setColor(new Color(40, 80, 180));
        for (int angle = 90; angle <= 270; angle += 15) {
            double rad = Math.toRadians(angle);
            int x = cx + (int)(majorR * Math.cos(rad));
            int y = cy + (int)(majorR * Math.sin(rad) * verticalScale);
            g2.fillOval(x - minorR/2, y - (int)(minorR/2 * verticalScale),
                       minorR, (int)(minorR * verticalScale));
        }

        // Draw main body with gradient for depth
        GradientPaint bodyGradient = new GradientPaint(
            cx - majorR, cy, new Color(60, 110, 220),
            cx + majorR, cy, new Color(100, 150, 255)
        );
        g2.setPaint(bodyGradient);

        // Outer ellipse
        int outerWidth = (majorR + minorR) * 2;
        int outerHeight = (int)((majorR + minorR) * 2 * verticalScale);
        g2.fillOval(cx - majorR - minorR, cy - (int)((majorR + minorR) * verticalScale),
                    outerWidth, outerHeight);

        // Inner hole (cut out center)
        g2.setColor(getBackground());
        int innerWidth = (majorR - minorR) * 2;
        int innerHeight = (int)((majorR - minorR) * 2 * verticalScale);
        if (innerWidth > 0 && innerHeight > 0) {
            g2.fillOval(cx - majorR + minorR, cy - (int)((majorR - minorR) * verticalScale),
                        innerWidth, innerHeight);
        }

        // Draw front half of torus (brighter) for 3D effect
        g2.setColor(new Color(120, 170, 255));
        for (int angle = -90; angle <= 90; angle += 15) {
            double rad = Math.toRadians(angle);
            int x = cx + (int)(majorR * Math.cos(rad));
            int y = cy + (int)(majorR * Math.sin(rad) * verticalScale);
            g2.fillOval(x - minorR/2, y - (int)(minorR/2 * verticalScale),
                       minorR, (int)(minorR * verticalScale));
        }

        // Draw outlines for definition
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));

        // Outer outline
        g2.drawOval(cx - majorR - minorR, cy - (int)((majorR + minorR) * verticalScale),
                    outerWidth, outerHeight);

        // Inner outline
        if (innerWidth > 0 && innerHeight > 0) {
            g2.drawOval(cx - majorR + minorR, cy - (int)((majorR - minorR) * verticalScale),
                        innerWidth, innerHeight);
        }
    }
}
