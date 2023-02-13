package gamecontrol;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

public class GlobalVariablesTest {

  @Test
  public void getPassWord() throws IOException {
    GlobalVariables.gameInitialization();

    System.out.println(GlobalVariables.getPassWord());

    assertEquals(GlobalVariables.getPassWord().length(), 4);
  }
}