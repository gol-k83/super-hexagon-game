package org.example.superhexagon;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class HexagonGrid {
    private double rotation = 0;
    private final int centerX;
    private final int centerY;
    private int sides = 6;
    private Color[] sectorColors;
    private double pulseOffset = 0;
    private int pulseStage = 0;
    private boolean growing = true;

    public HexagonGrid(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        initializeColors();
    }

    private void initializeColors() {
        sectorColors = new Color[sides];
        Color color1 = Color.ORANGE;
        Color color2 = Color.DARK_GRAY;
        Color color3 = new Color(90, 90, 90); // رنگ سوم برای حالت ۵ ناحیه

        for (int i = 0; i < sides; i++) {
            if (sides == 5) {
                sectorColors[i] = switch (i % 3) {
                    case 0 -> color1;
                    case 1 -> color2;
                    default -> color3;
                };
            } else {
                sectorColors[i] = (i % 2 == 0) ? color1 : color2;
            }
        }
    }

    public void setSides(int sides) {
        if (sides >= 3 && sides <= 6) {
            this.sides = sides;
            initializeColors();
        }
    }

    public void updateRotation(double speed) {
        rotation += speed;
        if (rotation >= 360) rotation -= 360;
        if (rotation <= -360) rotation += 360;
    }

    public void updatePulse() {
        if (growing) {
            pulseOffset += 0.3;
            if (pulseOffset >= 6) {
                growing = false;
                pulseStage++;
            }
        } else {
            pulseOffset -= 0.3;
            if (pulseOffset <= 0) {
                growing = true;
                pulseStage++;
            }
        }
    }

    public void updateSectorColors(Color[] colors) {
        if (colors != null && colors.length == sides) {
            this.sectorColors = colors;
        }
    }

    public void draw(Graphics2D g2d) {
        AffineTransform old = g2d.getTransform();
        g2d.translate(centerX, centerY);
        g2d.rotate(Math.toRadians(rotation));

        double angleStep = 360.0 / sides;
        for (int i = 0; i < sides; i++) {
            g2d.setColor(sectorColors[i]);
            drawSector(g2d, i * angleStep);
        }

        drawCenterPolygon(g2d);
        g2d.setTransform(old);
    }

    private void drawSector(Graphics2D g2d, double angleDegrees) {
        double angleRad = Math.toRadians(angleDegrees);
        double delta = Math.PI / sides;
        int length = 900;

        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        xPoints[0] = 0;
        yPoints[0] = 0;
        xPoints[1] = (int) (length * Math.cos(angleRad - delta));
        yPoints[1] = (int) (length * Math.sin(angleRad - delta));
        xPoints[2] = (int) (length * Math.cos(angleRad + delta));
        yPoints[2] = (int) (length * Math.sin(angleRad + delta));

        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    private void drawCenterPolygon(Graphics2D g2d) {
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];
        double angleStep = 2 * Math.PI / sides;
        int innerRadius = 75 + (int) pulseOffset;

        for (int i = 0; i < sides; i++) {
            double angle = i * angleStep + angleStep / 2;
            xPoints[i] = (int) (innerRadius * Math.cos(angle));
            yPoints[i] = (int) (innerRadius * Math.sin(angle));
        }

        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints, yPoints, sides);

        // مرز ضخیم برای جلوه
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawPolygon(xPoints, yPoints, sides);
    }

    public double getRotation() {
        return rotation;
    }

    public int getSides() {
        return sides;
    }

    public Color[] getSectorColors() {
        return sectorColors;
    }
}







