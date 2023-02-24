package ui.gui.components.buttons;

import ui.gui.components.dialogs.DifficultyDialog;

import javax.swing.*;

public class StartButton extends JButton {
    DifficultyDialog d = null;
    public StartButton() {
        setText("Start Game");
        setEnabled(true);
        setFocusable(false);
        addActionListener(e -> {
            startGame();
        });
        setOpaque(false);
    }
    private void startGame() {
        if(d == null || !d.isDisplayable()) {
            d = new DifficultyDialog((JFrame) this.getTopLevelAncestor());
        }
    }
}
