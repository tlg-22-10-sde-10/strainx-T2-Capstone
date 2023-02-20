package ui.gui.components.labels;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SubareaTitleLabel extends JLabel {
    public SubareaTitleLabel(String subareaName){
        setText(subareaName);
        setFont(getFont().deriveFont(Font.BOLD,30));
        setBorder(new LineBorder(Color.PINK));
        setForeground(Color.BLUE);
    }
}