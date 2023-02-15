package ui.gui;

import gamecontrol.GameDifficulty;
import gamecontrol.GameDifficultySelection;
import gamecontrol.GlobalVariables;
import gamemodel.mapengine.MainMap;
import ui.gui.components.MainMapPanel;
import ui.gui.components.StatsPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUIEntry {
    public static void start() throws IOException {

        GlobalVariables.inGameMap = new MainMap(GameDifficulty.Easy);
        GlobalVariables.gameInitialization();

        // TEST StatsPanel
        JFrame jFrame = new JFrame();
        jFrame.add(new StatsPanel(GlobalVariables.mySquad));
        jFrame.add(new MainMapPanel());
        jFrame.setResizable(false);
        jFrame.setTitle("StrainX");
        jFrame.setPreferredSize(new Dimension(1024,768));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}