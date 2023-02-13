package ui;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

public class UIEnterGameTest {

  @Test
  public void displayStaff() throws InterruptedException {
    UIEnterGame.displayStaff();
  }

  @Test
  public void displayGameControl() {
    UIEnterGame.displayGameControl();
  }

  @Test
  public void displayEnterGame() throws InterruptedException, IOException {
    UIEnterGame.displayEnterGame();
  }
}