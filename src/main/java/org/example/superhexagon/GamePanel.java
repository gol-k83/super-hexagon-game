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
    private int colorTimer=0;
    // سیستم مدیریت رنگ و سختی بازی
        this.colorManager = new ColorManager();
        this.difficultyManager = new GameDifficultyManager();



    public GamePanel(GameManager gameManager, String playerName) {
        this.gameManager = gameManager;
        this.playerName = playerName;
        this.setFocusable(true);
        this.requestFocusInWindow();
        setBackground(Color.BLACK);
        SwingUtilities.invokeLater(this::requestFocusInWindow);

        // اصلاح گرفتن فوکوس
       this.grid = new HexagonGrid(400, 400, 100);
        this.grid.setSides(difficultyManager.getCurrentPolygonSides());//
        this.grid.updateSectorColors(this.colorManager.getSectorColors());

        this.obstacleManager = new ObstacleManager(grid.getSides(), colorManager.getObstacleColor());//
        player = new PlayerIndicator(160, 10, 0, 3);
        timer = new Timer(16, this); // ~60 FPS
        timer.start();

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
//==============
        obstacleManager.drawAll(g2d, 400, 400, grid.getRotation());
        player.draw(g2d, 400, 400);

        g2d.dispose();

      //  grid.draw(g2d);
        player.draw(g2d, 400, 400);

        g2d.dispose();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double deltaTime = 0.016; // تقریبا معادل با 60 FPS

        this.difficultyManager.updateDifficulty(deltaTime); // سرعت و سختی بازی با گذر زمان افزایش پیدا می‌کند
        this.grid.setSides(this.difficultyManager.getCurrentPolygonSides());
        this.grid.updateRotation(this.difficultyManager.getSpeed());
        this.player.updateRelativeToStage(this.difficultyManager.getSpeed());

        this.obstacleManager.updateAll();

        this.colorTimer++;
        if (this.colorTimer >= 240) {
            this.colorManager.changeColorsRandomly();
            this.grid.updateSectorColors(this.colorManager.getSectorColors());
            this.colorTimer = 0;
        }

        if (leftPressed) this.player.rotateLeft();
        if (rightPressed) this.player.rotateRight();

        repaint();
    }
}



