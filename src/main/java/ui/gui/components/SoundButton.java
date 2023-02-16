package ui.gui.components;

import gamemusic.AudioPlayer;
import javax.swing.*;
import java.awt.*;

public class SoundButton extends JButton {


    public SoundButton() {
        setText("Toggle Sound");
        setEnabled(true);
        setFocusable(false);
        addActionListener(e -> toggleSound());
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void toggleSound() {
        if(AudioPlayer.getClip() == null) {
            AudioPlayer.getInstance().playAudio();
        } else {
            AudioPlayer.getInstance().stopAudio();
            AudioPlayer.setClip(null);
        }
    }
}
