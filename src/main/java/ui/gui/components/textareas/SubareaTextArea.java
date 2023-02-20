package ui.gui.components.textareas;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SubareaTextArea extends JTextArea {
    public SubareaTextArea(String text) {
        setText(text);
        setBorder(new LineBorder(Color.PINK));
        setEnabled(false);
    }
}