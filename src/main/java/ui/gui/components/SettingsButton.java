package ui.gui.components;

import ui.gui.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class SettingsButton extends JButton{
    private SettingsWindow settingWindow;

    public SettingsButton() {
        super("Settings");
        settingWindow = null;
        addActionListener(e -> {
            if (settingWindow == null || !settingWindow.isDisplayable()) {
                System.out.println(this.getTopLevelAncestor());
                settingWindow = new SettingsWindow((JFrame) this.getTopLevelAncestor());
            }
        });
    }
}
