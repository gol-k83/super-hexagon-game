
package org.example.superhexagon;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryPanel extends JPanel {
    private final GameManager gameManager;
    private final String playerName;

    public HistoryPanel(GameManager gameManager, String playerName) {
        this.gameManager = gameManager;
        this.playerName = playerName;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel title = new JLabel("📜 تاریخچه بازی‌ها - " + playerName, SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.CYAN);
        add(title, BorderLayout.NORTH);

        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setBackground(Color.BLACK);
        historyArea.setForeground(Color.LIGHT_GRAY);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        List<GameHistory.GameEntry> games = GameHistory.getHistory(playerName);
        int count = games.size();
        double best = GameHistory.getBestScore( playerName);

        StringBuilder content = new StringBuilder();
        content.append("🔸 تعداد بازی‌ها: ").append(count).append("\n");
        content.append("🏆 رکورد (BEST): ").append((int) best).append(" ثانیه\n\n");
        content.append("🕒 تاریخچه:\n");
        for (GameHistory.GameEntry game : games) {
            content.append(" - ").append(game.date).append(" | ")
                    .append((int) game.duration).append(" ثانیه\n");
        }
        historyArea.setText(content.toString());

        JScrollPane scrollPane = new JScrollPane(historyArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("🔙 بازگشت به منو");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> gameManager.showMainMenu());
        add(backButton, BorderLayout.SOUTH);
    }
}
