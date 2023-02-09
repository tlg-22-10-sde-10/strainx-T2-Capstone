package client;

import gamecontrol.GlobalVariables;
import java.util.*;

public class TextParsing {

    public static void ParseCommand(List<String> wordlist) {
        String verb;
        String noun;

        Verbs[] commandVerbList = Verbs.values();
        Nouns[] commandNounList = Nouns.values();
        Directions[] commandDirectionList = Directions.values();

        List<Directions> directionsAsList = Arrays.asList(commandDirectionList);
        List<Verbs> verbsAsList = Arrays.asList(commandVerbList);
        List<Nouns> nounsAsList = Arrays.asList(commandNounList);

        verb = wordlist.get(0);
        noun = wordlist.get(1);
        if(wordlist.size() > 2) {
            System.out.println("Please enter movement + directions");
        }
        // special case of if verb is 'go', it must be paired with a valid direction
        // handle that here
        else if (verb.equalsIgnoreCase("go")) {
            try {
                Directions validDirectionEnum = Directions.valueOf(noun.toUpperCase());
                if (directionsAsList.contains(validDirectionEnum)) {
                    // update player location
                    //TO-DO:

                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input, please choose from: North, East, South, or West");
            }
        } else {
            try {
                Verbs validVerbEnums = Verbs.valueOf(verb.toUpperCase());
                Nouns validNounEnums = Nouns.valueOf(noun.toUpperCase());
                if(verbsAsList.contains(validVerbEnums)) {
                    if(nounsAsList.contains(validNounEnums)) {
                        // interact here

                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid verb or noun. Error message - " + e);
            }
        }
    }


    public static void RunCommand(String userInput) {
        List<String> wordlist;
        if(userInput.equalsIgnoreCase("quit")) {
            // ExitMessage()
            //GlobalVariables.gameExitMessage();
            System.exit(0);
        }
        if(userInput.equalsIgnoreCase("help")) {
            // display help message
            GlobalVariables.playerHelpCall();
        }
        if(userInput.equalsIgnoreCase("")) {
            System.out.println("You must enter a valid action!");
        }
//         else {
//             wordlist = WordList(userInput);
//             if (wordlist.size() > 1) {
//                 wordlist.forEach((aStr) -> System.out.println(aStr));
//                 ParseCommand(wordlist);
//             }
//             else {
//                 System.out.println("Try entering commands in a [verb] [direction/noun] format such as" +
//                         " [go] [north] or [use] [shotgun]");
//             }
        }
    }
