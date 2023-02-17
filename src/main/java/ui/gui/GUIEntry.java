package ui.gui;

import ui.gui.components.panels.TitlePanel;

import javax.swing.*;

public class GUIEntry {

    public static void start() {

        // TEST StatsPanel
        JFrame jFrame = new JFrame();

//        jFrame.add(new WrapperPanel());
        jFrame.add(new TitlePanel());

        jFrame.setResizable(false);
        jFrame.setTitle("StrainX");
        //jFrame.setPreferredSize(new Dimension(1024,768));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }
}