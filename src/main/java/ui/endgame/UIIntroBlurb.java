package ui.endgame;

import java.util.Scanner;
import ui.maps.UIEnterSubarea;

public class UIIntroBlurb {

  public static void displayIntro() throws InterruptedException {

    Scanner scan = new Scanner(System.in);

    String paragraph1 =
        "\nYou are part of a small strike team covertly inserted onto a military installation where \n"
            + "a failed experiment has taken place. The experiment caused a zombie outbreak and it is thought the \n"
            + "only way to contain and reverse the spread is to obtain a blood sample from \"Patient Zero.\" \n\n";

    String paragraph2 =
        "\nYour objective is to locate Patient Zero on the base and obtain the sample by any means necessary. \n"
            + "Naturally, insertion was totally botched and your team's equipment was lost, so you'll have to scour \n"
            + "the base for supplies in order to proceed.\n\n";

    String paragraph3 =
        "\nPatient Zero now leads a horde of zombies, his once human form twisted and corrupted by the \n"
            + "failed experiment. You must face this formidable opponent and put an end to the zombie outbreak \n"
            + "before it spreads beyond the confines of the installation. Can you overcome the odds and emerge \n"
            + "victorious, or will you become just another mindless undead member of Patient Zero's horde?\n";

    System.out.print(paragraph1);
    System.out.print("\nPress enter to continue>>");
    String input = scan.nextLine();
    System.out.print(paragraph2);
    System.out.print("\nPress enter to continue>>");
    input = scan.nextLine();
    System.out.print(paragraph3);
    System.out.print("\nPress enter to continue>>");
    input = scan.nextLine();
  }
}
