
// GamePanel.java
package org.example.superhexagon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel implements ActionListener {
    private final GameManager gameManager;
    private final String playerName;

    private final Timer timer;
    private final HexagonGrid grid;
    private final PlayerIndicator player;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private final ColorManager colorManager;
    private final GameDifficultyManager difficultyManager;
    private final PhaseController phaseController;

    private final ObstacleManager obstacleManager;
    private final GameHistory history;

    private int colorTimer = 0;
    private double timeElapsed =0;



    public GamePanel(GameManager gameManager, String playerName) {
        this.gameManager = gameManager;
        this.playerName = playerName;

        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.BLACK);
        SwingUtilities.invokeLater(this::requestFocusInWindow);

        this.colorManager = new ColorManager();
        this.difficultyManager = new GameDifficultyManager();
        this.phaseController = new PhaseController();
        this.history = new GameHistory();

        this.grid = new HexagonGrid(400, 400, 100);
        this.obstacleManager = new ObstacleManager();
        this.player = new PlayerIndicator(120, 10,0, 5, colorManager.getObstacleColor());

        this.timer = new Timer(16, this);
        this.timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        grid.draw(g2d);
        obstacleManager.drawAll(g2d, 400, 400, grid.getRotation());
        player.draw(g2d, 400, 400);
        drawInfo(g2d);

        g2d.dispose();
    }

    private void drawInfo(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 18));
        g2d.drawString("TIME: " + (int) timeElapsed, 20, 30);
        Double best = history.getBestScore(playerName);
        if (best != null) {
            g2d.drawString("BEST: " + best.intValue(), 650, 30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double deltaTime = 0.016;
        timeElapsed += deltaTime;

        phaseController.update(deltaTime);
        int currentSides = phaseController.getCurrentSides();

        difficultyManager.updateDifficulty(deltaTime);
        grid.setSides(currentSides);
        grid.updateRotation(difficultyManager.getSpeed());
        grid.updatePulse();

        player.setSides(currentSides);

        colorTimer++;
        if (colorTimer >= 180) {
            grid.updateSectorColors(colorManager.getSectorColors());
            player.setColor(colorManager.getObstacleColor());
            colorTimer = 0;
        }

        obstacleManager.updateAll(currentSides, colorManager.getObstacleColor(), difficultyManager.getSpeed(), timeElapsed);


        player.updateRelativeToStage(difficultyManager.getSpeed());

        if (leftPressed) player.rotateLeft();
        if (rightPressed) player.rotateRight();

        boolean hit = obstacleManager.checkCollision(player, currentSides); // ✅ اگر لازم بود این متد بسازیم
        if (hit) {
           history.saveGame(playerName,timeElapsed);
            timer.stop();
           gameManager.showGameOver(timeElapsed, history.getBestScore(playerName));
        }

        repaint();
    }

}