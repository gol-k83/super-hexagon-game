package org.example.superhexagon;
import javax.swing.*;
import java.awt.*;

public class GameSetupPanel extends JPanel {
    private final GameManager gameManager;

    public GameSetupPanel(GameManager gameManager) {
        this.gameManager = gameManager;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel nameLabel = new JLabel("نام بازیکن:");
        gbc.gridy = 0;
        add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        gbc.gridy = 1;
        add(nameField, gbc);

        JButton startButton = new JButton("شروع بازی");
        gbc.gridy = 2;
        add(startButton, gbc);

        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) name = "بازیکن ناشناس";
            gameManager.startGame(name);
        });
    }
}
