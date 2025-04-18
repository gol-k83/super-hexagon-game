package org.example.superhexagon;

public class PhaseController {
    private double elapsed = 0;
    private int currentSides = 6;

    public void update(double deltaTime) {
        elapsed += deltaTime;
        if (elapsed < 10) {
            currentSides = 6;
        } else if (elapsed < 17) {
            currentSides = 5;
        } else {
            currentSides = 4;
        }
    }

    public int getCurrentSides() {
        return currentSides;
    }
}