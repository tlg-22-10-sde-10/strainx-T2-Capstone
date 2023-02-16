package gamemusic;
import junit.framework.TestCase;

public class AudioPlayerTest extends TestCase {

    public void testChangeVolume() {
        AudioPlayer.setVolume(-100.0f);
        assertEquals(-80.0f,AudioPlayer.getVolume(),0.0f);
        AudioPlayer.setVolume(20.0f);
        assertEquals(6.0206f,AudioPlayer.getVolume(),0.01f);
    }
}