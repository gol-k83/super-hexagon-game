package org.example.superhexagon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ColorManager {

    private static class Theme {
        Color color1;
        Color color2;
        Color obstacleColor;
        Color thirdColorIfOdd;
        private int currentGroupIndex = 0;

        public Theme(Color color1, Color color2, Color obstacleColor, Color thirdColorIfOdd) {
            this.color1 = color1;
            this.color2 = color2;
            this.obstacleColor = obstacleColor;
            this.thirdColorIfOdd = thirdColorIfOdd;
        }
    }

    private final List<Theme> themes = new ArrayList<>();
    private int currentThemeIndex = 0;

    private Color[] currentSectorColors;
    private static Color currentObstacleColor;

    public ColorManager() {
        // === Theme 1 ===
        themes.add(new Theme(
                new Color(198, 123, 15),
                new Color(75, 44, 4),
                new Color(200, 128, 24),
                new Color(95, 55, 7)
        ));

        // === Theme 2 ===
        themes.add(new Theme(
                new Color(0, 78, 82),
                new Color(1, 89, 99),
                new Color(16, 214, 249),
                new Color(207, 217, 218)
        ));

        // === Theme 3 ===
        themes.add(new Theme(
                new Color(82, 76, 0),
                new Color(98, 96, 0),
                new Color(242, 249, 16),
                new Color(225, 178, 56)
        ));

        // مقدار اولیه
        changeColors(6);
    }

    public void changeColors(int sides) {
        Theme theme = themes.get(currentThemeIndex);
        currentThemeIndex = (currentThemeIndex + 1) % themes.size();

        if (sides % 2 == 0) {
            // حالت زوج: فقط دو رنگ یک‌درمیون
            currentSectorColors = new Color[sides];
            for (int i = 0; i < sides; i++) {
                currentSectorColors[i] = (i % 2 == 0) ? theme.color1 : theme.color2;
            }
        } else {
            // حالت فرد: ۳ رنگ متوالی
            currentSectorColors = new Color[sides];
            for (int i = 0; i < sides; i++) {
                if (i % 3 == 0) {
                    currentSectorColors[i] = theme.color1;
                } else if (i % 3 == 1) {
                    currentSectorColors[i] = theme.color2;
                } else {
                    currentSectorColors[i] = theme.thirdColorIfOdd;
                }
            }
        }

        currentObstacleColor = theme.obstacleColor;
    }

    public Color[] getSectorColors() {
        return currentSectorColors;
    }

    public static Color getObstacleColor() {
        return currentObstacleColor;
    }
//    public void changeColorsRandomly() {
//        currentGroupIndex = (currentGroupIndex + 1) % colorGroups.length;
//        currentSectorColors = colorGroups[currentGroupIndex];
//
//        // اگه ناحیه‌ها فرد بودن، از رنگ سوم استفاده کن
//        if (currentSectorColors.length == 3) {
//            obstacleColor = currentSectorColors[2];
//        } else {
//            obstacleColor = blendColors(currentSectorColors[0], currentSectorColors[1]);
//        }
//    }

}
