package ui.gui.components.buttons;

import ui.gui.components.GUISoundEffects;

import javax.swing.*;
import java.awt.*;

public class SoundButton extends JButton {


    public SoundButton() {
        setText("SoundFX ON");
        setEnabled(true);
        setFocusable(false);
        addActionListener(e -> toggleSound());
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void toggleSound() {
        if(!GUISoundEffects.isSoundOn()) {
            GUISoundEffects.setSoundOn(!GUISoundEffects.isSoundOn());
            setText("SoundFX ON");
            repaint();
        } else {
            GUISoundEffects.setSoundOn(!GUISoundEffects.isSoundOn());
            setText("SoundFX OFF");
            repaint();
        }
    }
}
