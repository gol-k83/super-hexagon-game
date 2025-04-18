package org.example.superhexagon;

import java.awt.*;

public class PlayerIndicator {
    private double angle; // زاویه فعلی نسبت به مرکز (درجه)
    private final int distanceFromCenter; // فاصله از مرکز
    private final int size; // اندازه مثلث
    private final double playerSpeed; // سرعت چرخش (درجه به ازای هر فشار کلید)
    private Color color;
    private int sides = 6; // تعداد اضلاع چندضلعی مرکزی (در صورت نیاز برای برخورد)

    public PlayerIndicator(int distanceFromCenter, int size, double initialAngle, double playerSpeed, Color color) {
        this.distanceFromCenter = distanceFromCenter;
        this.size = size;
        this.angle = initialAngle;
        this.playerSpeed = playerSpeed;
        this.color = color;
    }

    public void rotateLeft() {
        angle -= playerSpeed;
    }

    public void rotateRight() {
        angle += playerSpeed;
    }

    public void updateRelativeToStage(double stageRotation) {
        angle += stageRotation;
    }

    public void draw(Graphics2D g2d, int centerX, int centerY) {
        double angleRad = Math.toRadians(angle);
        int x = (int) (centerX + distanceFromCenter * Math.cos(angleRad));
        int y = (int) (centerY + distanceFromCenter * Math.sin(angleRad));

        Polygon triangle = new Polygon();
        for (int i = 0; i < 3; i++) {
            double theta = angleRad + Math.toRadians(i * 120);
            int px = (int) (x + size * Math.cos(theta));
            int py = (int) (y + size * Math.sin(theta));
            triangle.addPoint(px, py);
        }

        g2d.setColor(color);
        g2d.fillPolygon(triangle);
    }

    public double getAngle() {
        return angle % 360;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public void setDistanceFromCenter(int newDistance) {
        // اضافه برای انعطاف بیشتر
        // اگر خواستی بازیکن رو به مرکز نزدیک‌تر کنی
    }



    public double getDistanceFromCenter() {
        return distanceFromCenter;
    }


}
