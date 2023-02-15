package ui.gui.components;

import gamecontrol.GameDifficulty;
import gamecontrol.GlobalVariables;
import gamemodel.mapengine.MainMap;

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
        }
        System.out.println(GlobalVariables.getPassWord());
        System.out.println(GlobalVariables.InventoryMap);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            setDifficulty(s);
            JDialog d = (JDialog) this.getTopLevelAncestor();
            d.dispose();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
