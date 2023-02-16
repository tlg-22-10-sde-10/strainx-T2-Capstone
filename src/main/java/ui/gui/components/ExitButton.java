package ui.gui.components;

import javax.swing.*;

import java.awt.*;

import static java.lang.System.exit;

public class ExitButton extends JButton {
    public ExitButton() {
        setText("Exit Game");
        setEnabled(true);
        setFocusable(false);
        addActionListener(e -> showConfirm());
        setOpaque(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void showConfirm() {
        int a = JOptionPane.showConfirmDialog(this.getTopLevelAncestor(),"Are you sure you want to exit?");

        if(a==JOptionPane.YES_OPTION) {
            exit(0);
        }
    }
}
