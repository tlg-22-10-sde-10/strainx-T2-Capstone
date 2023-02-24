package ui.gui.components.panels;

import gamemodel.mapengine.SubArea;
import ui.gui.ButtonCoordinates;
import ui.gui.components.buttons.SubareaButton;
import ui.maps.UIEnterMainMap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AreaPanel extends JPanel {

    public AreaPanel(List<SubArea> subareasList){
        createSubareaPanels(this,subareasList);
    }

    private Integer createSubareaPanels(JPanel area,List<SubArea> subAreaList){
        UIEnterMainMap.threatLvlMapInitialize();
        area.setLayout(null);

        for (SubArea subArea : subAreaList) {

            SubareaPanel subareaPanel = new SubareaPanel(subArea);
            subareaPanel.setBorder(new LineBorder(Color.BLUE));
            area.add(subareaPanel);

            JButton areaButton = new JButton();
            areaButton.setEnabled(false);

            JButton toggleSubarea = new SubareaButton(subArea,subareaPanel);
            toggleSubarea.setOpaque(true);
            Point buttonCoordinates = getButtonCoordinates(subArea);

            toggleSubarea.setBounds((int)(buttonCoordinates.getX()),(int)(buttonCoordinates.getY()),ButtonCoordinates.w,ButtonCoordinates.h);
            subareaPanel.add(toggleSubarea);

            area.add(toggleSubarea);
            area.add(subareaPanel);
        }
        return 1;
    }

    private Point getButtonCoordinates(SubArea subArea){
        HashMap<SubArea, Point> randomAreaLocations = ButtonCoordinates.buttonLocations;
        return randomAreaLocations.get(subArea);
    }
}