// GameOverPanel.java (نسخه نهایی)
package org.example.superhexagon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverPanel extends JPanel {
    private final GameManager gameManager;
    private final double timeElapsed;
    private final double bestScore;
    private final BufferedImage gameOverImage;

    public GameOverPanel(GameManager gameManager, double timeElapsed, double bestScore) {
        this.gameManager = gameManager;
        this.timeElapsed = timeElapsed;
        this.bestScore = bestScore;

        setLayout(null);
        setBackground(Color.BLACK);

        gameOverImage = AssetLoader.loadImage("gameover.png");

        JLabel scoreLabel = new JLabel("⏱ زمان شما: " + (int) timeElapsed + " ثانیه");
        scoreLabel.setForeground(Color.CYAN);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        scoreLabel.setBounds(270, 300, 400, 30);
        add(scoreLabel);

        JLabel bestLabel = new JLabel("🔥 بهترین رکورد: " + (int) bestScore + " ثانیه");
        bestLabel.setForeground(Color.ORANGE);
        bestLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        bestLabel.setBounds(270, 340, 400, 30);
        add(bestLabel);

        JButton retryButton = new JButton("🚀 بازی جدید");
        retryButton.setBounds(300, 400, 200, 40);
        retryButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        retryButton.setBackground(Color.DARK_GRAY);
        retryButton.setForeground(Color.PINK);
        retryButton.addActionListener(e -> gameManager.showGameSetup());
        add(retryButton);

        JButton historyButton = new JButton("📜 تاریخچه");
        historyButton.setBounds(300, 450, 200, 40);
        historyButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        historyButton.setBackground(Color.DARK_GRAY);
        historyButton.setForeground(Color.WHITE);
        historyButton.addActionListener(e -> gameManager.showHistory());
        add(historyButton);

        JButton exitButton = new JButton("❌ خروج از بازی");
        exitButton.setBounds(300, 500, 200, 40);
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        exitButton.setBackground(Color.DARK_GRAY);
        exitButton.setForeground(Color.LIGHT_GRAY);
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameOverImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int imgX = (getWidth() - gameOverImage.getWidth()) / 2;
            int imgY = 150;
            g2d.drawImage(gameOverImage, imgX, imgY, null);
            g2d.dispose();
        }
    }
}
