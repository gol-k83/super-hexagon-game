// AssetLoader.java
package org.example.superhexagon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.*;

public class AssetLoader {

    public static BufferedImage loadImage(String path) {
        try (InputStream in = AssetLoader.class.getClassLoader().getResourceAsStream("assets/" + path)) {
            if (in == null) throw new IOException("فایل تصویر پیدا نشد: " + path);
            return ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Clip loadSound(String path) {
        try {
            URL url = AssetLoader.class.getClassLoader().getResource("assets/" + path);
            if (url == null) throw new IOException("فایل صوتی پیدا نشد: " + path);

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }
}
