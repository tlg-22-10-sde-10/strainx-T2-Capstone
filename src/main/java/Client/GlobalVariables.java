package Client;

import java.util.Map;

public class GlobalVariables {

    public static void gameExitMessage() {
        System.out.println("Thank you for playing StrainX!");
    }

    public static void playerHelpCall() {
        System.out.println("Here are some tips:");
        System.out.println("You can interact with items, by typing get [item] or use [item].");
        System.out.println("You can move from your location to another location by typing go [direction].");
        System.out.println("Examples: go north or use shotgun");
    }

    private int exit_code = 0;

    public int getExitCode() {
        return exit_code;
    }

    public void setExitCode(int exit_code) {
        this.exit_code = exit_code;
    }

    public static void gameInitialization() {


    }
}
