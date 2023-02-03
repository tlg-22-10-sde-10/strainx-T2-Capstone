package Client;

import CombatEngine.engage_enemy;

import java.util.Scanner;

import static Client.GlobalVariables.*;


public class StrainX_Main {

    public static void main(String[] args) {

        gameInitialization();
        Scanner userInput = new Scanner(System.in);

        //UI_main_screen.load_main_screen();

        var map = gameMap.Game_Maps;


        //combat_environment.squad_initialization(mySquad, enemySquad);

        test();

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
            if (choice == 6) {
                gameMap.go_North();
            }
            if (choice == 7) {
                gameMap.go_South();
            }
            if (choice == 8) {
                gameMap.go_West();
            }
            if (choice == 9) {
                gameMap.go_East();
            }

            if (choice >0 && choice < map.get(position).size()+1) {
                enemySquad = map.get(position).get(choice-1).getContents().enemies;

                if (enemySquad.size() > 0) {
                    System.out.println("You find a group of enemies");
                } else {
                    System.out.println("You have cleared " + map.get(position).get(choice-1).getName());
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
