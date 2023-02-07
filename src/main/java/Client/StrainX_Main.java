package Client;

import UI.Maps.UI_main_map;
import UI.UI_TitlePage;

import java.io.IOException;
import java.util.Scanner;

import static Client.GlobalVariables.*;


public class StrainX_Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        gameInitialization();
        //Scanner userInput = new Scanner(System.in);

        UI_TitlePage.displayTitle();

        UI_main_map.displayMainMapUI();
    }
}
