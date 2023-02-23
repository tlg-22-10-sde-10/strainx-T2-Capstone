package ui.gui.components;

import client.StrainXMain;
import gamemusic.AudioPlayer;

import javax.sound.sampled.*;
import java.io.IOException;

public class GUISoundEffects {

    private static boolean soundOn = true;

    public static void playSound(String filePath) {
        try {
            Clip clip;
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(StrainXMain.class.getClassLoader().getResourceAsStream(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(AudioPlayer.getVolume()); // Reduce volume by 10 decibels.
            clip.loop(0);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: Unsupported audio file format.");
        } catch (IOException e) {
            System.out.println("Error: Could not read audio file.");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Could not play audio clip.");
        }
    }

    public static boolean isSoundOn() {
        return soundOn;
    }

    public static void setSoundOn(boolean soundOn) {
        GUISoundEffects.soundOn = soundOn;
    }
}
