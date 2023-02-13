package gamemusic;

import java.io.InputStream;

public class MusicHelper {

  public static InputStream openMusic(String name) {
    return MusicHelper.class.getClassLoader().getResourceAsStream(name);
  }
}
