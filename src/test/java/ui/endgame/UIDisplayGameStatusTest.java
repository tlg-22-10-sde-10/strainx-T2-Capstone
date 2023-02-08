package ui.endgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class UIDisplayGameStatusTest {

  @Test
  public void displayLosing() {
    UIDisplayGameStatus.displayInfo(UILosingPage.display());
  }

  @Test
  public void displayWinning() {
    UIDisplayGameStatus.displayInfo(UIWinningPage.displayWinning());
  }

  @Test
  public void displayTitle() {
    UIDisplayGameStatus.displayInfo(UITitlePage.displayTitle());
  }
}