package ui.gui.components;

import gamemodel.mapengine.SubArea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SubareaPanel extends JPanel {
    private SubArea subArea;

    public SubareaPanel(SubArea subArea){
        this.subArea = subArea;
        setBorder(new LineBorder(Color.RED));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(0,0));
        setVisible(false);
        goToMap(this);
        goToCombat(this);
    }

    private Integer goToMap(JPanel subareaPanel){
        JButton btn = new JButton("Go Back");
        btn.addActionListener( handleReturnToMap(subareaPanel) );
        subareaPanel.add(btn);
        return 1;
    }

    // Temporary Methods until SubArea is fully designed
    private Integer goToCombat(JPanel subareaPanel) {
        JButton btn = new JButton("Enter Combat");
        btn.addActionListener(handleGoToCombat(subareaPanel));
        if(subArea.getContents().enemies.isEmpty()) {
            btn.setEnabled(false);
        }
        subareaPanel.add(btn);
        return 1;
    }

    private ActionListener handleGoToCombat(JPanel subareaPanel) {
        return e -> {
            JFrame ancestor = (JFrame) subareaPanel.getTopLevelAncestor();
            ancestor.getContentPane().removeAll();
            ancestor.add(new CombatPanel(this));
            ancestor.repaint();
            ancestor.pack();
        };
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