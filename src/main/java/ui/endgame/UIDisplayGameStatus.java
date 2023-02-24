package ui.endgame;

import java.util.Random;

public class UIDisplayGameStatus {

  private static final int x_axis = 96;

  public static String displayInfo(String title) {
    Random rg = new Random();

    String colorPlaceHolderEnd = "\33[0m";

    String[] colors = new String[]{"\033[29m", "\033[30m", "\033[31m", "\033[32m",
        "\033[33m", "\033[34m", "\033[35m", "\033[36m", "\033[37m"};

    String colorPlaceHolderStart = colors[rg.nextInt(colors.length - 2) + 2];



    title = colorPlaceHolderStart + title + colorPlaceHolderEnd;
    System.out.println(title);
    return title;
  }
}
