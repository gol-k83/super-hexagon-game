package org.example.superhexagon;



import java.awt.*;
import java.util.List;
//
//public class CollisionDetector {
//    public static boolean checkCollision(List<Obstacle> obstacles, PlayerIndicator player, int sides) {
//        double angle = player.getAngle() % 360;
//        if (angle < 0) angle += 360;
//
//        int sector = (int) ((angle / 360.0) * sides);
//
//        for (Obstacle obstacle : obstacles) {
//            if (obstacle.getSector() == sector && obstacle.getDistance() < 60) {
//                return true;
//            }
//        }
//        return false;
//    }

//
//
//    public static boolean checkCollision(List<Obstacle> obstacles, PlayerIndicator player, int sides) {
//        double angle = player.getAngle() % 360;
//        if (angle < 0) angle += 360;
//
//        int sector = (int) ((angle / 360.0) * sides);
//
//        double playerDistance = player.getDistanceFromCenter();  // نیازه این متد توی PlayerIndicator اضافه بشه
//        double threshold = 18;  // این فاصلهٔ تقریبی ضخامت مانعه
//
//        for (Obstacle obstacle : obstacles) {
//            if (obstacle.getSector() == sector) {
//                double obsDistance = obstacle.getDistance();
//                if (Math.abs(obsDistance - playerDistance) < threshold) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public boolean checkCollision(PlayerIndicator player, int sides) {
//        double playerAngle = player.getAngle();
//        int playerX = (int) (400 + player.getDistanceFromCenter() * Math.cos(Math.toRadians(playerAngle)));
//        int playerY = (int) (400 + player.getDistanceFromCenter() * Math.sin(Math.toRadians(playerAngle)));
//
//        for (Obstacle obstacle : obstacles) {
//            double obstacleAngle = obstacle.getSector() * (360.0 / sides);
//            int obstacleX = (int) (400 + obstacle.getDistance() * Math.cos(Math.toRadians(obstacleAngle)));
//            int obstacleY = (int) (400 + obstacle.getDistance() * Math.sin(Math.toRadians(obstacleAngle)));
//
//            double dist = Math.hypot(playerX - obstacleX, playerY - obstacleY);
//            if (dist < 25) return true; // حساسیت برخورد
//        }
//        return false;



import java.util.List;
import org.example.superhexagon.Obstacle;
import org.example.superhexagon.PlayerIndicator;

public class CollisionDetector {
    public static boolean checkCollision(List<Obstacle> obstacles, PlayerIndicator player, int sides) {
        if (obstacles == null || player == null) return false;

        double angle = player.getAngle() % 360;
        if (angle < 0) angle += 360;

        int sector = (int) ((angle / 360.0) * sides);

        for (Obstacle obstacle : obstacles) {
            if (obstacle != null && obstacle.getSector() == sector && obstacle.getDistance() < 130) {
                return true;
            }
        }

        return false;
    }
}





















// Existing classes preserved below

