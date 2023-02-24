package ui.gui.components.dialogs;
import ui.gui.GUIEntry;
import ui.gui.components.buttons.*;

import javax.swing.*;

public class SettingsWindow extends JDialog {

    final JFrame parentFrame;
    public SettingsWindow(JFrame frame) {
        super(frame);
        parentFrame = frame;

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        setBounds(200,200,150,260);
        setResizable(false);
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Settings");
        add(new GodModeButton("Cheats"));
        add(new SoundButton());
        add(new MusicButton());
        add(new VolumeButton(1));
        add(new VolumeButton(2));
        add(new ExitToMainMenuButton());
        add(new ExitButton());
        setVisible(true);
        addWindowListener(GUIEntry.disableJFrame(frame));
    }

    public JFrame getParentFrame() {
        return parentFrame;
    }
}
