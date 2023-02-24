package ui.gui;

import gamemodel.mapengine.SubArea;
import ui.maps.UIEnterMainMap;

import java.util.*;

public class ConstructHTMLString {

    public static final String THREAT_LEVEL_HTML = "<html>Threat Level:???<br/>Items Inside:???</html>";
    private static final String THREAT_LEVEL_SPAN_TAG = "<span style='color: black;'>.</span>";
    // TEMP Global VAR
    public static Boolean HAS_PASSWORD = false;

    public static String parseThreatLevelHTMLString(SubArea subArea){
        StringBuilder info = new StringBuilder(THREAT_LEVEL_HTML);

        int last = THREAT_LEVEL_HTML.lastIndexOf("???");
        int first = THREAT_LEVEL_HTML.indexOf("???");
        info.replace(last,last+3, numberOfItemsInSubarea(subArea));
        info.replace(first,first+3, colorCodeThreatHTMLSpanTag(subArea));

        return info.toString();
    }

    public static String parseCombatTextString(String string) {
        StringBuilder returnString = new StringBuilder("<html>");
        returnString.append(string.replaceAll("\\[0m","</span>")
                .replaceAll("\\[31m","<span style='color: red;'>")
                .replaceAll("\\[32m","<span style='color: green;'>")
                .replaceAll("\\[33m","<span style='color: orange;'>")
                .replaceAll("\\[35m","<span style='color: purple;'>")
                .replaceAll("\n","<br/>")

        );
        returnString.append("</html>");
        return returnString.toString();
    }
    private static String numberOfItemsInSubarea(SubArea subArea){
        String items = "";
        if(!subArea.getContents().items.isEmpty()){
            items = String.valueOf(subArea.getContents().items.size());
        }
        return items;
    }
    private static String colorCodeThreatHTMLSpanTag(SubArea subArea){
        String colorCodeThreat = THREAT_LEVEL_SPAN_TAG;
        String threatLevel = UIEnterMainMap.displayThreatLvl(subArea);

        String txt = "";
        String color = "";

        if(!convertANSICode(threatLevel).isEmpty()){
            ArrayList<String> threatInfo = convertANSICode(threatLevel);
            txt = threatInfo.get(0);
            color = threatInfo.get(1);
        }

        colorCodeThreat = colorCodeThreat.replace("black",color);
        colorCodeThreat = colorCodeThreat.replace(".",txt);

        return colorCodeThreat;
    }
    private static ArrayList<String> convertANSICode(String ansi){
        // TODO refactor: convert/parse ANSI code to string
        ArrayList<String> data = new ArrayList<>();
        if(ansi.contains("\033[31mHigh\33[0m")){
            data.add("High");
            data.add("red");
        }
        else if(ansi.contains("\033[33mMedium\33[0m")){
            data.add("Medium");
            data.add("orange");
        }
        else if(ansi.contains("\033[32mLow\33[0m")){
            data.add("Low");
            data.add("green");
        }
        else if(ansi.contains("\033[34mSafe\33[0m")){
            data.add("Safe");
            data.add("blue");
        }
        return data;
    }


}