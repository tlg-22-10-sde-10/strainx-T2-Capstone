package Client;

import CombatEngine.engage_enemy;
import UI.TitlePage;
import UI.UI_enter_subarea;

import java.io.IOException;
import java.util.Scanner;

import static Client.GlobalVariables.*;


public class StrainX_Main {

    public static void main(String[] args) throws IOException {

        gameInitialization();
        Scanner userInput = new Scanner(System.in);

        //UI_main_screen.load_main_screen();

        var map = gameMap.Game_Maps;

        //combat_environment.squad_initialization(mySquad, enemySquad);

        test();

        TitlePage.displayTitle();

//########demo part
        while (true) {
            System.out.println("------------------");
            System.out.println("Keep going!");

            int position = gameMap.getPosition();

            System.out.println("You are now in Area" + position);
            System.out.println("There are several places you can explore:\n");

            for (int i = 0; i< map.get(position).size(); i++) {
                System.out.println((i+1)+". "+ map.get(position).get(i).getName());
            }

            System.out.println("\nselect what you trying to do:");
            System.out.println("press 0 to exit game.\npress number to explore area\npress 6789 to go NSWE\n");
            var choice = userInput.nextInt();

            if (choice == 0) {
                break;
            }

            switch (choice){
                case 6:
                    gameMap.go_North();
                    break;
                case 7:
                    gameMap.go_South();
                    break;
                case 8:
                    gameMap.go_West();
                    break;
                case 9:
                    gameMap.go_East();
                    break;
            }

            if (choice >0 && choice < map.get(position).size()+1) {
                current_subArea = map.get(position).get(choice-1);

                UI_enter_subarea.display_subarea();

                enemySquad = current_subArea.getContents().enemies;

                if (enemySquad.size() > 0) {
                    System.out.println("Engaging a group of enemies");
                } else {
                    System.out.println(map.get(position).get(choice-1).getName() + " is clear");
                }

                engage_enemy.gameEnginePrototype();

                if(mySquad.get(0).getHP() <= 0) {
                    break;
                }
            }
        }

        //UI.UI_game_over.load_game_over();

        //load_mission_accomplish();

        //System.out.println("\033[1;41mThis text is highlighted!\033[0m");


        //engage.gameEnginePrototype();


    }

    static void test() {

    }
}
