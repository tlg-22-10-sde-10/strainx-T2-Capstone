package ui.gui.components;

import gamemusic.AudioPlayer;
import gamemusic.MusicHelper;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GUISoundEffects {

    private static boolean soundOn = true;

    public static void playSound(String filePath) {
        try (InputStream input = MusicHelper.openMusic(filePath)) {
            Clip clip;
            BufferedInputStream bufferedIn = new BufferedInputStream(input);
            AudioInputStream stream = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gain.setValue(AudioPlayer.getVolume());
            clip.start();
            clip.loop(0);

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSoundOn() {
        return soundOn;
    }

    public static void setSoundOn(boolean soundOn) {
        GUISoundEffects.soundOn = soundOn;
    }
}
