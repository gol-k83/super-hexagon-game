package org.example.superhexagon;


import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Super Hexagon - Swing Edition");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);
            frame.setLocationRelativeTo(null);

            GameManager gameManager = new GameManager(frame);
           gameManager.showMainMenu();
          //  gameManager.startGame("بازیکن تست");
            frame.setVisible(true);
        });
    }
}

