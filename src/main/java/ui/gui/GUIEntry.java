package ui.gui;

import ui.gui.components.JOptionPanes;
import ui.gui.components.panels.TitlePanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.System.exit;

public class GUIEntry {

    public static void start() {

        JFrame jFrame = new JFrame();

        jFrame.add(new TitlePanel(jFrame));

        jFrame.setResizable(false);
        jFrame.setTitle("StrainX");
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int i = JOptionPanes.confirmExit(jFrame);
                if(i == JOptionPane.YES_OPTION){
                    e.getWindow().dispose();
                    exit(0);
                }
            }
        });
    }
}