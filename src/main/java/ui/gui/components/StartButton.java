package ui.gui.components;

import gamecontrol.GameDifficulty;
import gamecontrol.GlobalVariables;
import gamemodel.mapengine.MainMap;

import javax.swing.*;
import java.io.IOException;

public class StartButton extends JButton {
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
        if (GlobalVariables.inGameMap == null) {
            GlobalVariables.inGameMap = new MainMap(GameDifficulty.Easy);
            GlobalVariables.gameInitialization();
        }
        System.out.println(GlobalVariables.getPassWord());
    }
}
