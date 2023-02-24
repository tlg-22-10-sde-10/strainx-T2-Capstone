package ui.gui.components.buttons;

import ui.gui.ButtonCoordinates;
import ui.gui.components.panels.WrapperPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContinueButton extends JButton implements ActionListener {
    public ContinueButton() {
        this.setText("Continue..");
        this.setBackground(Color.GREEN);
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Clear the contents of the Frame and then draw the main map UI (Lorenzo's classes) to the screen
        ButtonCoordinates.buttonDimension();
        ButtonCoordinates.buttonLocations = ButtonCoordinates.getRandomAreaLocations();
        JFrame tempFrame = (JFrame) getTopLevelAncestor();
        tempFrame.getContentPane().removeAll();
        tempFrame.add(new WrapperPanel());
        tempFrame.repaint();
        tempFrame.pack();

        // Placeholder
        //exit(0);
    }
}
