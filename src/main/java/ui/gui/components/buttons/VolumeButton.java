package ui.gui.components.buttons;

import gamemusic.AudioPlayer;

import javax.swing.*;
import java.awt.*;

public class VolumeButton extends JButton {
    public VolumeButton(int modifier) {
        setText(modifier == 1 ? "Volume Up" : "Volume Down");
        setEnabled(true);
        setFocusable(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        addActionListener(e -> AudioPlayer.changeVolume(modifier));
    }
}
