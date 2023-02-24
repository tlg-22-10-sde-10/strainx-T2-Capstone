package ui.gui;

import ui.gui.components.JOptionPanes;
import ui.gui.components.panels.TitlePanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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

    public static WindowListener disableJFrame(JFrame frame) {
        return new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                frame.setEnabled(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                frame.setEnabled(true);
            }
        };
    }
}