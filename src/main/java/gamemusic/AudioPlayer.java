package gamemusic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

  private static AudioPlayer audioPlayer = null;
  private static Clip clip;
  private static final Float DEFAULT_VOLUME = -35.0f;
  private static final Float VOLUME_ADJUST_VALUE = 10.0f;
  private static Float volume = DEFAULT_VOLUME;

  public static AudioPlayer getInstance() {
    if (audioPlayer == null) {
      audioPlayer = new AudioPlayer();
    }
    return audioPlayer;
  }

  public void playAudio() {

    try (InputStream input = MusicHelper.openMusic("Horror-Game-Intro.wav")) {
      if (clip != null) {
        clip.stop();
        clip.flush();
      }
      BufferedInputStream bufferedIn = new BufferedInputStream(input);
      AudioInputStream stream = AudioSystem.getAudioInputStream(bufferedIn);
      AudioFormat format = stream.getFormat();
      Info info = new Info(Clip.class, format);
      clip = (Clip) AudioSystem.getLine(info);
      clip.open(stream);
      FloatControl gain = (FloatControl) clip.getControl(Type.MASTER_GAIN);
      gain.setValue(volume);
      clip.setFramePosition(0);
      clip.start();
      clip.loop(Clip.LOOP_CONTINUOUSLY);

    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }

  public void stopAudio() {
    clip.stop();
    clip.flush();
    clip.close();
  }

  public static void changeVolume(int input) {
//    setVolume((input == 1) ? (volume+=VOLUME_ADJUST_VALUE) : (volume-=VOLUME_ADJUST_VALUE));
    FloatControl gain = (FloatControl) clip.getControl(Type.MASTER_GAIN);
    if (input == 1) {
      setVolume(volume += VOLUME_ADJUST_VALUE);
    } else if (input == 2) {
      setVolume(volume -= VOLUME_ADJUST_VALUE);
    }
    gain.setValue(volume);
  }

  public static Float getVolume() {
    return volume;
  }

  public static void setVolume(Float volume) {
    if(volume < -80.0f) {
      AudioPlayer.volume = -80.0f;
    } else if (volume > 6.0206f) {
      AudioPlayer.volume = 6.0206f;
    } else {
      AudioPlayer.volume = volume;
    }
  }
}
