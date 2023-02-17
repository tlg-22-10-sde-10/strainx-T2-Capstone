package ui.gui.components.buttons;

import gamemodel.mapengine.SubArea;
import ui.gui.ConstructHTMLString;
import ui.gui.components.panels.SubareaPanel;
import ui.maps.UIEnterMainMap;
import ui.gui.components.panels.SubareaPanel;

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

        if(subArea.getVisited()) setToolTipText(ConstructHTMLString.parseThreatLevelHTMLString(subArea));
        else defaultToolTipText();

        return 1;
    }
    private Integer defaultToolTipText(){
        setToolTipText(ConstructHTMLString.THREAT_LEVEL_HTML);
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