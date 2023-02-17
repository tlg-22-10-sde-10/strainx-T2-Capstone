package ui.gui.components.buttons;

import gamemodel.mapengine.SubArea;
import ui.gui.components.panels.SubareaPanel;
import ui.maps.UIEnterMainMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SubareaButton extends JButton {

    public SubareaButton(SubArea subArea, SubareaPanel subareaPanel){
        adjustToolTipText(subArea);
        setAlignmentY(Component.CENTER_ALIGNMENT);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        add(new JLabel(subArea.getName()));
        setName(subArea.getName());
        addActionListener(toggleShowSubareaPanel( subareaPanel, subArea ));
    }

    private Integer adjustToolTipText(SubArea subArea){
        String info = "<html>Threat Level:???<br/>Items Inside:???</html>";

        if(subArea.getVisited()) setToolTipText(parseHTMLString(info,subArea));
        else defaultToolTipText(info);

        return 1;
    }
    public String parseHTMLString(String str, SubArea subArea){
        StringBuilder info = new StringBuilder(str);

        int last = str.lastIndexOf("???");
        int first = str.indexOf("???");
        info.replace(last,last+3, numberOfItemsInSubarea(subArea));
        info.replace(first,first+3, colorCodeThreatHTMLSpanTag(subArea));

        return info.toString();
    }
    private String numberOfItemsInSubarea(SubArea subArea){
        String items = "";
        if(!subArea.getContents().items.isEmpty()){
            items = String.valueOf(subArea.getContents().items.size());
        }
        return items;
    }
    private String colorCodeThreatHTMLSpanTag(SubArea subArea){
        String colorCodeThreat = "<span style='color: black;'>.</span>";
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
    private ArrayList<String> convertANSICode(String ansi){
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
    private Integer defaultToolTipText(String info){
        setToolTipText(info);
        return 1;
    }
    private ActionListener toggleShowSubareaPanel(JPanel subarea, SubArea currArea){
        // toggle show/hide subarea panel
        return e -> {
            // toggle visibility
            subarea.setVisible(!subarea.isVisible());
            // expand subarea/this panel
            if(subarea.isVisible()){
                if( !currArea.getVisited() ) currArea.setVisited(!currArea.getVisited());
                JPanel tempParent = (JPanel) getParent().getParent().getParent();
                tempParent.remove(1);
                tempParent.add(subarea);
                tempParent.revalidate();
                tempParent.repaint();
                subarea.setPreferredSize(new Dimension(tempParent.getWidth(),tempParent.getHeight()));
            }
            subarea.revalidate();
            subarea.repaint();
        };
    }

}