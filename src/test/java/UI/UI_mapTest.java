package UI;

import Client.GlobalVariables;
import org.junit.Test;

import java.io.IOException;

import static UI.UI_map.*;

public class UI_mapTest {

    @Test
    public void displayMainMapUITest() throws IOException {
        GlobalVariables.gameInitialization();

        displayMainMapUI();
    }
}