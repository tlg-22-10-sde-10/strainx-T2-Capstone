package ui.gui.components.buttons;

import ui.gui.components.dialogs.SettingsWindow;

import javax.swing.*;

public class SettingsButton extends JButton{
    private SettingsWindow settingWindow;

    public SettingsButton() {
        super("Settings");
        settingWindow = null;
        addActionListener(e -> {
            if (settingWindow == null || !settingWindow.isDisplayable()) {
                settingWindow = new SettingsWindow((JFrame) this.getTopLevelAncestor());
            }
        });
    }
}
