package ui.gui.components;

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
            d = null;
        }
    }
}
