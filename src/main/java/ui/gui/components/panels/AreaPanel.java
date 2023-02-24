package ui.gui.components.panels;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.SubArea;
import ui.gui.ButtonCoordinates;
import ui.gui.ConstructHTMLString;
import ui.gui.components.buttons.SubareaButton;
import ui.maps.UIEnterMainMap;
import ui.gui.components.buttons.SubareaButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.*;
import java.util.List;

import static gamecontrol.GlobalVariables.inGameMap;

public class AreaPanel extends JPanel {

    public static String neveChanging;

    public AreaPanel(Integer keyNumber, List<SubArea> subareasList){
        setBorder(new LineBorder(Color.BLUE.darker()));
//        setBackground(Color.GRAY);
//        setOpaque(true);
        createSubareaPanels(this,subareasList, keyNumber);
//        setLayout(new BoxLayout( this,BoxLayout.Y_AXIS));
    }

    private Integer createSubareaPanels(JPanel area,List<SubArea> subAreaList,Integer areaNumber){
        UIEnterMainMap.threatLvlMapInitialize();

        area.add(new JLabel(String.format("Area %d",areaNumber)));
        area.setLayout(null);
        for (SubArea subArea : subAreaList) {

            SubareaPanel subareaPanel = new SubareaPanel(subArea);
            subareaPanel.setBorder(new LineBorder(Color.BLUE));
            area.add(subareaPanel);

            JButton toggleSubarea = new SubareaButton(subArea,subareaPanel);
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