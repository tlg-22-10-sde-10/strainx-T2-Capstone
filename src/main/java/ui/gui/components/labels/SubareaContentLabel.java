package ui.gui.components.labels;

import ui.gui.ConstructHTMLString;
import ui.gui.components.panels.SubareaPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SubareaContentLabel extends JLabel {
    public SubareaContentLabel(SubareaPanel parentPanel) {
        setText(ConstructHTMLString.parseThreatLevelHTMLString(parentPanel.getSubArea()));
        setBorder(new LineBorder(Color.PINK));
    }
}