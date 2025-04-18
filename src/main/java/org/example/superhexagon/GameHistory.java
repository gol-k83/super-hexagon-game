// GameHistory.java - با استفاده از Jackson به‌جای Gson
package org.example.superhexagon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameHistory {
    private static final String DIR = "history";
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static class GameEntry {
        public String date;
        public double duration;

        public GameEntry() {} // نیاز برای Jackson

        public GameEntry(String date, double duration) {
            this.date = date;
            this.duration = duration;
        }
    }

    public static class PlayerData {
        public String player;
        public List<GameEntry> games = new ArrayList<>();

        public PlayerData() {} // نیاز برای Jackson
    }

    public static void saveGame(String playerName, double duration) {
        PlayerData data = loadData(playerName);
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        data.games.add(new GameEntry(now, duration));
        saveData(playerName, data);
    }

    public static PlayerData loadData(String playerName) {
        try {
            Files.createDirectories(Paths.get(DIR));
            String path = DIR + "/" + playerName.toLowerCase() + ".json";
            File file = new File(path);
            if (!file.exists()) {
                PlayerData data = new PlayerData();
                data.player = playerName;
                return data;
            }
            return mapper.readValue(file, PlayerData.class);
        } catch (IOException e) {
            e.printStackTrace();
            PlayerData data = new PlayerData();
            data.player = playerName;
            return data;
        }
    }

    private static void saveData(String playerName, PlayerData data) {
        try {
            File file = new File(DIR + "/" + playerName.toLowerCase() + ".json");
            mapper.writeValue(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double getBestScore(String playerName) {
        PlayerData data = loadData(playerName);
        return data.games.stream().mapToDouble(g -> g.duration).max().orElse(0);
    }

    public static List<GameEntry> getHistory(String playerName) {
        return loadData(playerName).games;
    }

    public static int getGameCount(String playerName) {
        return loadData(playerName).games.size();
    }
}
