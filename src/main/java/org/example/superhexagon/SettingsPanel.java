// SettingsPanel.java
package org.example.superhexagon;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private final GameManager gameManager;
    private static boolean isMusicEnabled = true;
    private static boolean saveHistory = true;
    private static Clip themeClip;

    public SettingsPanel(GameManager gameManager) {
        this.gameManager = gameManager;
        setLayout(null);
        setBackground(Color.BLACK);

        JLabel title = new JLabel("⚙️ تنظیمات بازی", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.CYAN);
        title.setBounds(200, 50, 400, 40);
        add(title);

        JCheckBox musicCheckbox = new JCheckBox("🎵 پخش آهنگ بازی", isMusicEnabled);
        musicCheckbox.setBounds(300, 150, 250, 30);
        musicCheckbox.setForeground(Color.WHITE);
        musicCheckbox.setBackground(Color.BLACK);
        musicCheckbox.addActionListener(e -> toggleMusic(musicCheckbox.isSelected()));
        add(musicCheckbox);

        JCheckBox historyCheckbox = new JCheckBox("💾 ذخیره تاریخچه بازی‌ها", saveHistory);
        historyCheckbox.setBounds(300, 200, 250, 30);
        historyCheckbox.setForeground(Color.WHITE);
        historyCheckbox.setBackground(Color.BLACK);
        historyCheckbox.addActionListener(e -> saveHistory = historyCheckbox.isSelected());
        add(historyCheckbox);

        JButton backButton = new JButton("🔙 بازگشت به منو");
        backButton.setBounds(320, 300, 200, 40);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.LIGHT_GRAY);
        backButton.addActionListener(e -> gameManager.showMainMenu());
        add(backButton);
    }

    private void toggleMusic(boolean enabled) {
        isMusicEnabled = enabled;
        if (enabled) {
            if (themeClip == null) {
                themeClip = AssetLoader.loadSound("src/main/java/resources/assets/MusicSuperHexagon.wav");
            }
            if (themeClip != null && !themeClip.isRunning()) {
                themeClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } else {
            if (themeClip != null && themeClip.isRunning()) {
                themeClip.stop();
            }
        }
    }

    public static boolean isMusicOn() {
        return isMusicEnabled;
    }

    public static boolean shouldSaveHistory() {
        return saveHistory;
    }

    public static void startMusicIfNeeded() {
        if (isMusicEnabled) {
            if (themeClip == null) {
                themeClip = AssetLoader.loadSound("src/main/java/resources/assets/MusicSuperHexagon.wav");


            }
            if (themeClip != null) {
                themeClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    public static void stopMusic() {
        if (themeClip != null) {
            themeClip.stop();
        }
    }
}

