package client;

import ui.UIEnterGame;
import ui.endgame.UIDisplayGameStatus;
import ui.endgame.UIIntroBlurb;
import ui.maps.UIEnterMainMap;
import ui.endgame.UITitlePage;

import java.io.IOException;

public class StrainXMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        UIEnterGame.displayEnterGame();

        //UIEnterMainMap.displayMainMapUI();
    }
}
