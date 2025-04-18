
package org.example.superhexagon;

import java.awt.*;

    public class Obstacle {
        private final int sector;
        private double distance;
        public final double speed;
        public final Color color;
        public final int sides;

        public Obstacle(int sector, double initialDistance, double speed, Color color, int sides) {
            this.sector = sector;
            this.distance = initialDistance;
            this.speed = speed;
            this.color = color;
            this.sides = sides;
        }

        public void update() {
            distance -= speed;
        }

        public boolean isOutOfBounds(double minDistance) {
            return distance <= minDistance;
        }

        public int getSector() {
            return sector;
        }

        public double getDistance() {
            return distance;
        }

        public void draw(Graphics2D g2d, int centerX, int centerY, double stageRotation) {
            double angleStep = 2 * Math.PI / sides;
            double angle = sector * angleStep + Math.toRadians(stageRotation);
            double delta = Math.PI / sides;

            int outerDist = (int) distance;
            int innerDist = (int) (distance - 20);

            int[] xPoints = new int[4];
            int[] yPoints = new int[4];

            xPoints[0] = (int) (centerX + outerDist * Math.cos(angle - delta));
            yPoints[0] = (int) (centerY + outerDist * Math.sin(angle - delta));
            xPoints[1] = (int) (centerX + outerDist * Math.cos(angle + delta));
            yPoints[1] = (int) (centerY + outerDist * Math.sin(angle + delta));
            xPoints[2] = (int) (centerX + innerDist * Math.cos(angle + delta));
            yPoints[2] = (int) (centerY + innerDist * Math.sin(angle + delta));
            xPoints[3] = (int) (centerX + innerDist * Math.cos(angle - delta));
            yPoints[3] = (int) (centerY + innerDist * Math.sin(angle - delta));

            g2d.setColor(color);
            g2d.fillPolygon(xPoints, yPoints, 4);
        }
    }

