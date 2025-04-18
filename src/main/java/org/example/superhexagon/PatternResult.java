package org.example.superhexagon;

import java.util.List;

public class PatternResult {
    private final List<Obstacle> obstacles;
    private final boolean followup;

    public PatternResult(List<Obstacle> obstacles, boolean followup) {
        this.obstacles = obstacles;
        this.followup = followup;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public boolean hasFollowup() {
        return followup;
    }
}
