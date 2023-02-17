package ui.gui.components.dialogs;

import ui.gui.components.buttons.SoundButton;
import ui.gui.components.buttons.VolumeButton;
import ui.gui.components.buttons.ExitButton;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JDialog {

    public SettingsWindow(JFrame frame) {
        super(frame);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        setBounds(200,200,150,150);
//        setSize(new Dimension(300,150));
        setResizable(false);
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Settings");
        add(new SoundButton());
        add(new VolumeButton(1));
        add(new VolumeButton(2));
        add(new ExitButton());
        setVisible(true);
    }
}
