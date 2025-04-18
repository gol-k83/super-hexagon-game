

package org.example.superhexagon;

import javax.swing.*;

public class GameManager {

    private final JFrame frame;
    private String currentPlayerName;

    public GameManager(JFrame frame) {
        this.frame = frame;
    }

    public void showMainMenu() {
        frame.setContentPane(new MainMenuPanel(this));
        frame.revalidate();
    }

    public void showGameSetup() {
        frame.setContentPane(new GameSetupPanel(this));
        frame.revalidate();
    }

    public void startGame(String playerName) {
        this.currentPlayerName = playerName;
        GameHistory.PlayerData data = GameHistory.loadData(playerName);
        GamePanel gamePanel = new GamePanel(this, playerName);
        frame.setContentPane(gamePanel);
        frame.revalidate();
    }

    public void showSettings() {
        frame.setContentPane(new SettingsPanel(this));
        frame.revalidate();
    }

    public void showHistory() {
        if (currentPlayerName != null) {
            frame.setContentPane(new HistoryPanel(this, currentPlayerName));
        } else {
            showMainMenu();
        }
        frame.revalidate();
    }

    public void showGameOver(double timeElapsed, double bestScore) {
        frame.setContentPane(new GameOverPanel(this, timeElapsed, bestScore));
        frame.revalidate();
    }
}
