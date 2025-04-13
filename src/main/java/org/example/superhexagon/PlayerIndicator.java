package org.example.superhexagon;



import java.awt.*;

public class PlayerIndicator {
    private double angle; // زاویه فعلی نشانگر نسبت به مرکز
    private final int distanceFromCenter; // فاصله ثابت از مرکز
    private final int size; // اندازه مثلث بازیکن
    private final double playerSpeed; // سرعت چرخش

    public PlayerIndicator(int distanceFromCenter, int size, double initialAngle, double playerSpeed) {
        this.distanceFromCenter = distanceFromCenter;
        this.size = size;
        this.angle = initialAngle;
        this.playerSpeed = playerSpeed;
    }

    public void rotateLeft() {
        angle -= playerSpeed;
    }

    public void rotateRight() {
        angle += playerSpeed;
    }

    // برای هماهنگ ماندن با چرخش زمین (نسبی)
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

        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(triangle);
    }

    // Getter برای زاویه در صورت نیاز برای بررسی برخورد
    public double getAngle() {
        return angle;
    }
}
