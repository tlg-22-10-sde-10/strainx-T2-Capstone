package ui.gui.components;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.Content;
import gamemodel.mapengine.SubArea;
import ui.maps.UIEnterMainMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubareaButton extends JButton {

    public SubareaButton(SubArea subArea, SubareaPanel subareaPanel){
        adjustToolTipText(subArea);
        setAlignmentY(Component.CENTER_ALIGNMENT);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        add(new JLabel(subArea.getName()));
        setName(subArea.getName()); // give the btn a name
        addActionListener(toggleShowSubareaPanel( subareaPanel, subArea ));
    }

    private Integer adjustToolTipText(SubArea subArea){
        //<span style='color: red;'>This is red text</span>
        String info = "<html>Threat Level:???<br/>Items Inside:???</html>";

        if(subArea.getVisited()) setToolTipText(formatString(info,subArea));
        else defaultToolTipText(info);

        return 1;
    }
    private String formatString(String str, SubArea subArea){
        StringBuilder info = new StringBuilder(str);
        // TODO convert/parse ANSI code to string
        String threatLevel = UIEnterMainMap.displayThreatLvl(subArea);
        String itemsInside = String.valueOf(subArea.getContents().items.size());

        int last = str.lastIndexOf("???");
        int first = str.indexOf("???");
        info.replace(last,last+3,itemsInside);
        info.replace(first,first+3,threatLevel);
        return String.valueOf(info);
    }
    private Integer defaultToolTipText(String info){
        setToolTipText(info);
        return 1;
    }
    private ActionListener toggleShowSubareaPanel(JPanel subarea, SubArea currArea){
        // test button click event
        // toggle show/hide subarea panel
        return e -> {
            // toggle visibility
            subarea.setVisible(!subarea.isVisible());
            // expand subarea (this) panel
            if(subarea.isVisible()){
                currArea.setVisited(!currArea.getVisited());
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