package ui.gui.components.buttons;

import gamecontrol.GameDifficulty;
import gamecontrol.GlobalVariables;
import gamemodel.mapengine.MainMap;
import ui.gui.CheckHealth;
import ui.gui.components.dialogs.DifficultyDialog;
import ui.gui.components.panels.DrawIntro;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class DifficultyButton extends JButton implements ActionListener {

    private final GameDifficulty s;
    public DifficultyButton(GameDifficulty s) {
        this.s = s;
        this.setText(s.toString());
        this.setEnabled(true);
        this.setFocusable(false);
        this.addActionListener(this);
        this.setOpaque(false);
    }

    public void setDifficulty(GameDifficulty s) throws IOException {
        if (GlobalVariables.inGameMap == null) {
            GlobalVariables.inGameMap = new MainMap(s);
            GlobalVariables.gameInitialization();
            DifficultyDialog d = (DifficultyDialog) this.getTopLevelAncestor();
            CheckHealth.healthTimer(d.getFrame());
        }
        System.out.println(GlobalVariables.getPassWord());
        System.out.println(GlobalVariables.InventoryMap);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            setDifficulty(s);
            DifficultyDialog d = (DifficultyDialog) this.getTopLevelAncestor();
            d.dispose();
            JFrame frame = d.getFrame();
            frame.getContentPane().removeAll();
            frame.add(DrawIntro.drawIntro());
            frame.setSize(1024,768);
            frame.pack();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
