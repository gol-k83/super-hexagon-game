package org.example.superhexagon;
import javax.swing.*;

public class GameManager {


        private final JFrame frame;

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
            frame.setContentPane(new GamePanel(this, playerName));
            frame.revalidate();
        }

        public void showSettings() {
      //      frame.setContentPane(new SettingsPanel(this));
            frame.revalidate();
        }

        public void showHistory() {
        //    frame.setContentPane(new HistoryPanel(this));
            frame.revalidate();
        }
    }





