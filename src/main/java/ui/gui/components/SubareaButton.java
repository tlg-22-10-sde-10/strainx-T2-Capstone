package ui.gui.components;

import gamemodel.mapengine.SubArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SubareaButton extends JButton {

    public SubareaButton(SubArea subArea, SubareaPanel subareaPanel){
        setAlignmentY(Component.CENTER_ALIGNMENT);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        add(new JLabel(subArea.getName()));
        setName(subArea.getName()); // give the btn a name
        addActionListener(toggleSubareaPanel( subareaPanel ));
    }

    private ActionListener toggleSubareaPanel(JPanel subarea){
        // test button click event
        // toggle show/hide subarea panel
        return e -> {
            subarea.setVisible(!subarea.isVisible());
            if(subarea.isVisible()){
                JPanel tempParent = (JPanel) getParent().getParent().getParent();
                tempParent.remove(1);
                tempParent.add(subarea);
                tempParent.revalidate();
                tempParent.repaint();
                subarea.setPreferredSize(new Dimension(tempParent.getWidth(),tempParent.getHeight()));
            }
            subarea.revalidate();
            subarea.repaint();
        };
    }

}