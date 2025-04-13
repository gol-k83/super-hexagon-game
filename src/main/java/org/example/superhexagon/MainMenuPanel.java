package org.example.superhexagon;



import javax.swing.*;
import java.awt.*;

    public class MainMenuPanel extends JPanel {

        public MainMenuPanel(GameManager gameManager) {
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;

            JButton startButton = new JButton("شروع بازی جدید");
            startButton.addActionListener(e -> gameManager.showGameSetup());
            add(startButton, gbc);






            JButton historyButton = new JButton("تاریخچه بازی‌ها");
            historyButton.addActionListener(e -> gameManager.showHistory());

            JButton settingsButton = new JButton("تنظیمات");
            settingsButton.addActionListener(e -> gameManager.showSettings());

            JButton exitButton = new JButton("خروج از بازی");
            exitButton.addActionListener(e -> System.exit(0));


            add(historyButton, gbc);
            add(settingsButton, gbc);
            add(exitButton, gbc);
        }
    }






