package client;

import ui.endgame.UIDisplayGameStatus;
import ui.maps.UIMainMap;
import ui.endgame.UITitlePage;

import java.io.IOException;

import static client.GlobalVariables.*;


public class StrainXMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        gameInitialization();
        //Scanner userInput = new Scanner(System.in);

        UIDisplayGameStatus.displayInfo(UITitlePage.displayTitle());

        UIMainMap.displayMainMapUI();
    }
}
