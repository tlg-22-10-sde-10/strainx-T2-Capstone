package ui.gui.components.buttons;

import gamecontrol.GlobalVariables;
import ui.gui.components.dialogs.SettingsWindow;
import ui.gui.components.panels.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class ExitToMainMenuButton extends JButton {

    public ExitToMainMenuButton() {
        setText("Exit to Main");
        setEnabled(true);
        setFocusable(false);
        addActionListener(e -> showConfirm());
        setOpaque(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void showConfirm() {
        int a = JOptionPane.showConfirmDialog(this.getTopLevelAncestor(),"Are you sure you want to exit to main menu?");

        if(a==JOptionPane.YES_OPTION) {
            SettingsWindow window = (SettingsWindow) this.getTopLevelAncestor();
            JFrame parentFrame = window.getParentFrame();
            window.dispose();

            GlobalVariables.resetGlobalVariables();
            parentFrame.getContentPane().removeAll();
            parentFrame.repaint();
            parentFrame.add(new TitlePanel(parentFrame));
            parentFrame.repaint();
            parentFrame.pack();
        }
    }
}
