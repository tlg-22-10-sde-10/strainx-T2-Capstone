package ui.gui.components.panels;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.SubArea;
import ui.gui.components.LoadImage;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class MapPanel extends JPanel{

    public MapPanel(){
        setLayout(getDimensions());
        appendAreaPanels(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image backgroundImage = LoadImage.getIcon("images/overhead01.png").getImage();
        g.drawImage(backgroundImage, 0, 0, null);
    }

    private GridLayout getDimensions(){
        int x = GlobalVariables.inGameMap.getDimensionX();
        int y = GlobalVariables.inGameMap.getDimensionY();
        return new GridLayout(x,y);
    }
    private Integer appendAreaPanels(JPanel mainMapPanel) {
        for (Map.Entry<Integer, List<SubArea>> areasMap : GlobalVariables.inGameMap.gameMap.entrySet()) {
            AreaPanel area = new AreaPanel(areasMap.getValue());
                area.setBackground(new Color(0,0, 0, 30));
            mainMapPanel.add(area);
        }
        return 1;
    }

}