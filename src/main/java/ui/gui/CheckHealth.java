package ui.gui;

import gamecontrol.GameDifficulty;
import gamecontrol.GlobalVariables;
import gamemodel.mapengine.MainMap;
import ui.gui.components.panels.TitlePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.lang.System.exit;

public class CheckHealth {
    public static void healthTimer(JFrame frame) {

        Timer timer = new Timer(500,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(GlobalVariables.mySquad.get(0).getHP() <= 0) {
                    int i = JOptionPane.showConfirmDialog(frame,
                            "You died! Do you want to start again?",
                            "You Lose!",
                            JOptionPane.YES_NO_OPTION);
                    if(i == JOptionPane.YES_OPTION) {
                        GlobalVariables.mySquad.get(0).setHP(100);
                        frame.getContentPane().removeAll();
                        frame.add(new TitlePanel());
                        frame.repaint();
                        frame.pack();
                    } else {
                        exit(0);
                    }
                }
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }
}
