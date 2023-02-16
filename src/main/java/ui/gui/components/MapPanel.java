package ui.gui.components;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.SubArea;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class MapPanel extends JPanel{

    //TODO ADD background image for the map

    public MapPanel(){
        setBackground(Color.PINK);
        setLayout(new GridLayout(GlobalVariables.inGameMap.getDimensionX(),GlobalVariables.inGameMap.getDimensionY()));
        appendAreaPanels(this);
    }

    private Integer appendAreaPanels(JPanel mainMapPanel) {
// Traverse subareas
        for (Map.Entry<Integer, List<SubArea>> areasMap : GlobalVariables.inGameMap.gameMap.entrySet()) {
            AreaPanel area = new AreaPanel(areasMap.getKey(),areasMap.getValue());
            mainMapPanel.add(area);
        }
        return 1;
    }

}