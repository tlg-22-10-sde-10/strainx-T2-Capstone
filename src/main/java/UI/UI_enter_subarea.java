package UI;

import java.util.Scanner;

import static Client.GlobalVariables.*;

public class UI_enter_subarea {

    public static void display_subarea() {
        display_subarea_name();
        display_subarea_description();
        display_subarea_items();
        display_subarea_enemy();

        System.out.println("press any key to continue...");

        Scanner s = new Scanner(System.in);
        String an = s.nextLine();
    }

    private static void display_subarea_name() {
        System.out.println("You have entered into " + current_subArea.getName());
    }

    private static void display_subarea_description() {
        System.out.println(current_subArea.getDescription());
    }

    private static void display_subarea_items() {
        int itemsNumber = current_subArea.getContents().items.size();
        if (itemsNumber > 0) {
            System.out.println("You see " + itemsNumber + " item there.");
        } else{
            System.out.println("There isn't anything interested you in this place.");
        }
    }

    private static void display_subarea_enemy() {
        int enemyPack = current_subArea.getContents().enemies.size();
        if (enemyPack > 0) {
            System.out.println("You see " + enemyPack + " zombies walking around.");
        } else {
            System.out.println("This place looks safe, at least for now.");
        }
    }
}
