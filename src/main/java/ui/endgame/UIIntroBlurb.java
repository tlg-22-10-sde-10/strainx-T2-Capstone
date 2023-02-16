package ui.endgame;

import java.util.Scanner;

public class UIIntroBlurb {



  public static void displayIntro() {

    Scanner scan = new Scanner(System.in);
    String paragraph1 = "\nYou are part of a small strike team covertly inserted onto a military installation where \n"
            + "a failed experiment has taken place. The experiment caused a zombie outbreak and it is thought the \n"
            + "only way to contain and reverse the spread is to obtain a blood sample from \"Patient Zero.\" \n";
    String paragraph2 = "\nYour objective is to locate Patient Zero on the base and obtain the sample by any means necessary. \n"
            + "Naturally, insertion was totally botched and your team's equipment was lost. As such, you'll have to pick \n"
            + "your fights carefully as you search the base for gear and your target, Patient Zero.\n";
    String paragraph3 = "\nPatient Zero now leads a horde of zombies, his once human form twisted and corrupted by the \n"
            + "failed experiment. You must face this formidable opponent and put an end to the zombie outbreak \n"
            + "before it spreads beyond the confines of the installation. Can you overcome the odds and emerge \n"
            + "victorious, or will you become just another mindless undead member of Patient Zero's horde?\n";
    String paragraph4 = "\nYour mission begins from Area 1, the base is arranged in a grid of numbered Areas containing enter-able subareas (buildings).\n"
            + "You recall your commander telling you the famed \"Schrader Lab\" might be a good place to check for Patient Zero...\n\n"
            + "If at any point you need help remembering game controls, type help. Good luck!";

    System.out.print(paragraph1);
    System.out.print("\nPress enter to continue>>");
    scan.nextLine();
    System.out.print(paragraph2);
    System.out.print("\nPress enter to continue>>");
    scan.nextLine();
    System.out.print(paragraph3);
    System.out.print("\nPress enter to continue>>");
    scan.nextLine();
    System.out.print(paragraph4);
    System.out.print("\nPress enter to continue>>");
    scan.nextLine();
  }
}
