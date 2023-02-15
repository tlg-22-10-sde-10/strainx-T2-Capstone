package ui.gui.components;

import gamecontrol.GlobalVariables;

import javax.swing.*;
import java.awt.*;

public class MainMapPanel extends JPanel{
    // TEMP MAP

    public MainMapPanel(){
        add(new JLabel("MAP"), BorderLayout.SOUTH);
        setBackground(Color.BLACK);
        setSize(1024,668);
        setOpaque(true);

    }

}