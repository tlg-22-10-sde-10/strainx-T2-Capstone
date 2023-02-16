package ui.gui.components;

import gamemodel.mapengine.SubArea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubareaPanel extends JPanel {
    private SubArea subArea;

    public SubareaPanel(SubArea subArea){
        setBorder(new LineBorder(Color.RED));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(0,0));
        setVisible(false);
        goToMap(this);
        this.subArea = subArea;
    }

    private Integer goToMap(JPanel subareaPanel){
        JButton btn = new JButton("Go Back");
        btn.addActionListener( handleReturnToMap(subareaPanel) );
        subareaPanel.add(btn);
        return 1;
    }

    private ActionListener handleReturnToMap(JPanel subareaPanel){
        return e -> {
            JFrame ancestor = (JFrame) subareaPanel.getTopLevelAncestor();
            ancestor.getContentPane().removeAll();
            ancestor.add(new WrapperPanel());
            ancestor.repaint();
            ancestor.pack();
        };
    }

    public SubArea getSubArea() { return subArea; }
    public void setSubArea(SubArea subArea) { this.subArea = subArea; }
}