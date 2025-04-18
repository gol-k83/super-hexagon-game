package org.example.superhexagon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObstaclePatternGenerator {
    private final Color color;
    private final int sides;
    private final double speed;
    private final Random random = new Random();
    private final ObstaclePatternLibrary patternLibrary = new ObstaclePatternLibrary();

    public ObstaclePatternGenerator(int sides, Color color, double speed) {
        this.color = color;
        this.sides = sides;
        this.speed = speed;
    }

    public List<Obstacle> generateRandomPattern() {
        PatternResult result = patternLibrary.generatePattern(sides, color, speed,random.nextInt(11));
        return result.getObstacles();
    }

    public List<Obstacle> generateDelayedFollowup() {
        return patternLibrary.generateDelayedFollowup(sides, color, speed);
    }
}
