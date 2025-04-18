package org.example.superhexagon;

public class GameDifficultyManager {
    private double elapsedTime;
    private double speed;

    public GameDifficultyManager() {
        elapsedTime = 0;
        speed = 1.5;
    }

    public void updateDifficulty(double deltaTime) {
        elapsedTime += deltaTime;
        speed += 0.0005;
    }

    public double getSpeed() {
        return speed;
    }
}

