package ui.gui.components.buttons;

import gamecontrol.GlobalVariables;
import gamemusic.AudioPlayer;

import javax.swing.*;
import java.awt.*;

public class MusicButton extends JButton {
    public MusicButton() {
        setText("Toggle Music");
        setEnabled(true);
        setFocusable(false);
        addActionListener(e -> toggleMusic());
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void toggleMusic() {
        if(!AudioPlayer.isSoundOn()) {
            if(GlobalVariables.inGameMap == null) {
                AudioPlayer.getInstance().playAudio("sound/titlemusic.wav");
            } else {
                AudioPlayer.getInstance().playAudio();
            }
            AudioPlayer.setSoundOn(!AudioPlayer.isSoundOn());
        } else {
            AudioPlayer.getInstance().stopAudio();
            AudioPlayer.setSoundOn(!AudioPlayer.isSoundOn());
        }
    }
}
