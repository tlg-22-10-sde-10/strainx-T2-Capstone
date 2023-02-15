package ui.gui.components;

import gamemusic.AudioPlayer;
import javax.swing.*;

public class SoundButton extends JButton {

    private boolean soundOn;
    public SoundButton() {
        this.setText("<html><pre>Toggle<br> Sound</pre></html>");
        this.setEnabled(true);
        this.setFocusable(false);
        this.addActionListener(e -> toggleSound());
    }

    private void toggleSound() {
        if(!soundOn) {
            AudioPlayer.getInstance().playAudio();
            soundOn = true;
        } else {
            AudioPlayer.getInstance().stopAudio();
            soundOn = false;
        }
    }
}
