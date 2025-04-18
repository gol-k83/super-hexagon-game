// ObstaclePatternLibrary.java
package org.example.superhexagon;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ObstaclePatternLibrary {
    private static final Random random = new Random();
    private static int lastPattern = -1;

    public PatternResult generatePattern(int sides, Color color, double speed, int patternType) {
        lastPattern = patternType;

        List<Obstacle> obstacles = switch (patternType) {
            case 0 -> singleObstacle(sides, color, speed);
            case 1 -> incompletePolygon(sides, color, speed);
            case 2 -> repeatingIncomplete(sides, 2, color, speed);
            case 3 -> repeatingIncomplete(sides, 3, color, speed);
            case 4 -> alternatingPattern(sides, true, 3, color, speed);
            case 5 -> alternatingPattern(sides, false, 3, color, speed);
            case 6 -> alternatingPattern(sides, false, 4, color, speed);
            case 7 -> multiPhasePattern1(sides, color, speed);
            case 8 -> multiPhasePattern2(sides, color, speed);
            default -> singleObstacle(sides, color, speed);
        };

        boolean followup = (patternType == 7 || patternType == 8);
        return new PatternResult(obstacles, followup);
    }


    public List<Obstacle> generateDelayedFollowup(int sides, Color color, double speed) {
        if (lastPattern == 7) {
            return incompletePolygon(sides, color, speed); // ادامه مستقیم
        } else if (lastPattern == 8) {
            return alternatingPattern(sides, true, 2, color, speed);
        }
        return Collections.emptyList();
    }

    private static List<Obstacle> singleObstacle(int sides, Color color, double speed) {
        return List.of(new Obstacle(random.nextInt(sides), 300, speed, color, sides));
    }

    private static List<Obstacle> incompletePolygon(int sides, Color color, double speed) {
        List<Obstacle> list = new ArrayList<>();
        int missing = random.nextInt(sides);
        for (int i = 0; i < sides; i++) {
            if (i != missing) list.add(new Obstacle(i, 300, speed, color, sides));
        }
        return list;
    }

    private static List<Obstacle> repeatingIncomplete(int sides, int count, Color color, double speed) {
        List<Obstacle> result = new ArrayList<>();
        int missing = random.nextInt(sides);
        for (int c = 0; c < count; c++) {
            for (int i = 0; i < sides; i++) {
                if (i != missing)
                    result.add(new Obstacle(i, 300 + c * 40, speed, color, sides));
            }
        }
        return result;
    }

    private List<Obstacle> alternatingPattern(int sides, boolean odd, int count, Color color, double speed) {
        List<Obstacle> result = new ArrayList<>();
        for (int c = 0; c < count; c++) {
            for (int i = 0; i < sides; i++) {
                if ((i % 2 == 0) != odd) continue;
                result.add(new Obstacle(i, 300 + c * 30, speed, color, sides));
            }
        }
        return result;
    }

    private List<Obstacle> multiPhasePattern1(int sides, Color color, double speed) {
        return incompletePolygon(sides, color, speed);
    }

    private List<Obstacle> multiPhasePattern2(int sides, Color color, double speed) {
        return alternatingPattern(sides, false, 2, color, speed);
    }
}
