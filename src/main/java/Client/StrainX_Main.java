package Client;

import UI.UI_main_screen;

import java.util.Scanner;

import static UI.UI_mission_accomplish.*;


public class StrainX_Main {

    public static void main(String[] args) {

        GlobalVariables.gameInitialization();
        Scanner userInput = new Scanner(System.in);

        UI_main_screen.load_main_screen();

        //String inputString = userInput.nextLine();


        while (true) {
            System.out.println("Keep going!");


            break;
        }




        UI.UI_game_over.load_game_over();


        load_mission_accomplish();

        //System.out.println("\033[1;41mThis text is highlighted!\033[0m");
    }
}
