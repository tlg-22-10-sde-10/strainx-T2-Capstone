package ui.gui.components.panels;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.SubArea;
import ui.gui.ConstructHTMLString;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class MapPanel extends JPanel{

    public MapPanel(){
        setLayout(getDimensions());
        appendAreaPanels(this);
    }

    private GridLayout getDimensions(){
        int x = GlobalVariables.inGameMap.getDimensionX();
        int y = GlobalVariables.inGameMap.getDimensionY();
        return new GridLayout(x,y);
    }
    private Integer appendAreaPanels(JPanel mainMapPanel) {
        for (Map.Entry<Integer, List<SubArea>> areasMap : GlobalVariables.inGameMap.gameMap.entrySet()) {
            AreaPanel area = new AreaPanel(areasMap.getKey(),areasMap.getValue());
            mainMapPanel.add(area);
        }
        return 1;
    }

}