package client;

import gamemodel.mapengine.MainMap;
import ui.endgame.UIDisplayGameStatus;
import ui.endgame.UIIntroBlurb;
import ui.maps.UIMainMap;
import ui.endgame.UITitlePage;

import java.io.IOException;

import static gamecontrol.GlobalVariables.*;


public class StrainXMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        MainMap gameMap = new MainMap(3,3);

        inGameMap = gameMap;

        gameInitialization();
        //Scanner userInput = new Scanner(System.in);

        UIDisplayGameStatus.displayInfo(UITitlePage.displayTitle());

        UIIntroBlurb.displayIntro();

        UIMainMap.displayMainMapUI();
    }
}
