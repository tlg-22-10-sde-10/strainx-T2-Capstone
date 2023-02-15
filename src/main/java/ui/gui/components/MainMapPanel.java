package ui.gui.components;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.SubArea;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainMapPanel extends JPanel{

    // provide list of Areas, easy 3x3
    public MainMapPanel(){
        add(new JLabel("MAP"), BorderLayout.SOUTH);
        setBackground(Color.BLACK);
        setSize(1024,668);
        setOpaque(true);
        setLayout(new GridLayout(3,3));
        appendAreaPanels(this);
    }

    private Integer appendAreaPanels(JPanel mainMapPanel) {
// Traverse subareas
        for (Map.Entry<Integer, List<SubArea>> areasMap : GlobalVariables.inGameMap.gameMap.entrySet()) {
            SubareaPanel area = new SubareaPanel(areasMap.getKey(),areasMap.getValue());
            mainMapPanel.add(area);
        }

        return 1;
    }


}