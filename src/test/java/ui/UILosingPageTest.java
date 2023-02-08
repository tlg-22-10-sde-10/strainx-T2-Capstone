package ui;

import org.junit.Test;
import ui.endgame.UILosingPage;
import ui.endgame.UIWinningPage;

public class UILosingPageTest {

  @Test
  public void display() {
    System.out.println(UILosingPage.display());

    System.out.println(UILosingPage.display().length());
    System.out.println(UIWinningPage.displayWinning().length());
  }
}