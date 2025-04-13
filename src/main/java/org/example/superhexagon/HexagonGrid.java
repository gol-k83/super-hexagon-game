package org.example.superhexagon;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class HexagonGrid {
    private double rotation = 0;
    private final int centerX, centerY, radius;
    private int sides = 6; // تعداد اضلاع (۴، ۵ یا ۶)
    private Color[] sectorColors;

    public HexagonGrid(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        initializeColors();
    }

    private void initializeColors() {
        sectorColors = new Color[sides];
        for (int i = 0; i < sides; i++) {
            // رنگ‌های یک‌درمیون: نارنجی و خاکستری تیره
            sectorColors[i] = (i % 2 == 0) ? Color.ORANGE : Color.DARK_GRAY;
        }
    }

    public void updateRotation(double speed) {
        rotation += speed;
        if (rotation >= 360) rotation -= 360;
    }

    public void setSides(int sides) {
        if (sides >= 3 && sides <= 6) {
            this.sides = sides;
            initializeColors();
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

        int length = 900; // برای پوشش کل صفحه

        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        xPoints[0] = 0;
        yPoints[0] = 0;

        xPoints[1] = (int)(length * Math.cos(angleRad - delta));
        yPoints[1] = (int)(length * Math.sin(angleRad - delta));

        xPoints[2] = (int)(length * Math.cos(angleRad + delta));
        yPoints[2] = (int)(length * Math.sin(angleRad + delta));

        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    private void drawCenterPolygon(Graphics2D g2d) {
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];

        double angleStep = 2 * Math.PI / sides;
        int innerRadius =75; // بزرگ‌تر برای دیده شدن

        for (int i = 0; i < sides; i++) {

            double angle = i * angleStep+ angleStep/2;
            xPoints[i] = (int) (innerRadius * Math.cos(angle));
            yPoints[i] = (int) (innerRadius * Math.sin(angle));
        }

        g2d.setColor(Color.BLACK); // رنگ واضح برای تست
        g2d.fillPolygon(xPoints, yPoints, sides);
    }

    public double getRotation (){
        return rotation;
    }

    public void updateSectorColors(Color[] colors){
        if (colors != null && colors.length == sides) {
            this.sectorColors = colors;
        }
     
    }

    public int getSides() {
        return sides;
    }




}








