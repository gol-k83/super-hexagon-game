package org.example.superhexagon;
import javax.swing.*;
import java.awt.*;

public class GameSetupPanel extends JPanel {
    private final GameManager gameManager;

    public GameSetupPanel(GameManager gameManager) {
        this.gameManager = gameManager;
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2;


        JLabel titleLabel = new JLabel("🌀 Super Hexagon 🌀");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.CYAN);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(titleLabel, gbc);




        JLabel nameLabel = new JLabel("نام بازیکن:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridy = 1;
        add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        gbc.gridy = 2;
        add(nameField, gbc);

        JButton startButton = new JButton("🚀 شروع بازی");
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.ORANGE);
        startButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridy = 3;
        add(startButton, gbc);

        startButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) name = "بازیکن ناشناس";
            gameManager.startGame(name);
        });
    }
}
