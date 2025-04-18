// ObstacleManager.java - نسخه نهایی و بهینه‌شده
package org.example.superhexagon;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ObstacleManager {
    private final List<Obstacle> obstacles;
    private final ObstaclePatternLibrary patternLibrary;
    private int spawnTimer;
    private final int baseInterval = 100; // در 60 FPS معادل ~1 ثانیه
    private boolean waitingForNextPattern = false;
    private int delayedPatternTimer = 0;

    public ObstacleManager() {
        this.obstacles = new ArrayList<>();
        this.patternLibrary = new ObstaclePatternLibrary();
        this.spawnTimer = 0;
    }

    public void updateAll(int sides, Color color, double speed, double elapsedTime) {
        // افزایش سرعت با زمان (اختیاری)
        double dynamicSpeed = speed + (elapsedTime * 0.004); // به مرور سریع‌تر میشه

        // مدیریت ظاهر شدن الگو با تأخیر برای بعضی حالت‌ها
        if (waitingForNextPattern) {
            delayedPatternTimer--;
            if (delayedPatternTimer <= 0) {
                obstacles.addAll(patternLibrary.generateDelayedFollowup(sides, color, dynamicSpeed));
                waitingForNextPattern = false;
            }
        } else {
            spawnTimer--;
            if (spawnTimer <= 0) {
                PatternResult result = patternLibrary.generatePattern(sides, color, dynamicSpeed,new Random().nextInt(11));
                obstacles.addAll(result.getObstacles());

                // اگر پترن نیاز به دنباله داشت، تنظیم کن
                if (result.hasFollowup()) {
                    waitingForNextPattern = true;
                    delayedPatternTimer = baseInterval; // یعنی بعد ~1 ثانیه
                }

                spawnTimer = baseInterval;
            }
        }

        // آپدیت و حذف موانع خارج‌شده
        Iterator<Obstacle> it = obstacles.iterator();
        while (it.hasNext()) {
            Obstacle obs = it.next();
            obs.update();
            if (obs.isOutOfBounds(60)) it.remove();
        }
    }

    public void drawAll(Graphics2D g2d, int cx, int cy, double rotation) {
        for (Obstacle obs : obstacles) {
            obs.draw(g2d, cx, cy, rotation);
        }
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public boolean checkCollision(PlayerIndicator player, int sides) {
        double angle = player.getAngle() % 360;
        if (angle < 0) angle += 360;
        int sector = (int) ((angle / 360.0) * sides);

        for (Obstacle obs : obstacles) {
            if (obs.getSector() == sector && obs.getDistance() < 130) {
                return true;
            }
        }
        return false;
    }
}

