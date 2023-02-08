package client;

import ui.maps.UIMainMap;
import ui.UITitlePage;

import java.io.IOException;

import static client.GlobalVariables.*;


public class StrainXMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        gameInitialization();
        //Scanner userInput = new Scanner(System.in);

        UITitlePage.displayTitle();

        UIMainMap.displayMainMapUI();
    }
}
