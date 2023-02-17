package ui.gui.components.buttons;

import ui.gui.components.dialogs.DifficultyDialog;

import javax.swing.*;
import java.io.IOException;

public class StartButton extends JButton {
    DifficultyDialog d = null;
    public StartButton() {
        setText("Start Game");
        setEnabled(true);
        setFocusable(false);
        addActionListener(e -> {
            try {
                startGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        setOpaque(false);
    }
    private void startGame() throws IOException {
        if(d == null || !d.isDisplayable()) {
            d = new DifficultyDialog((JFrame) this.getTopLevelAncestor());
        }
    }
}
