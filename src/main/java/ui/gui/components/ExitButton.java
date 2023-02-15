package ui.gui.components;

import javax.swing.*;

import static java.lang.System.exit;

public class ExitButton extends JButton {
    public ExitButton() {
        this.setText("Exit Game");
        this.setEnabled(true);
        this.setFocusable(false);
        this.addActionListener(e -> exit(0));
        this.setOpaque(false);
    }
}
