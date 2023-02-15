package ui.gui.components;

import gamecontrol.GameDifficulty;
import gamecontrol.GlobalVariables;
import gamemodel.mapengine.MainMap;
import ui.gui.TitlePanel;

import javax.swing.*;
import java.io.IOException;

public class StartButton extends JButton {
    DifficultyDialog d = null;
    public StartButton() {
        this.setText("Start Game");
        this.setEnabled(true);
        this.setFocusable(false);
        this.addActionListener(e -> {
            try {
                startGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        this.setOpaque(false);
    }

    private void startGame() throws IOException {
        if(d == null) {
            d = new DifficultyDialog((JFrame) this.getTopLevelAncestor());
        } else {
            JFrame frame = (JFrame) this.getTopLevelAncestor();
            frame.getContentPane().removeAll();
            frame.add(DrawIntro.drawIntro());
            frame.setSize(1024,768);
            frame.pack();
            d = null;
        }
    }
}
