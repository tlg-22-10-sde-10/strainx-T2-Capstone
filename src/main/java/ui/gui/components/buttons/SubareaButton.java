package ui.gui.components.buttons;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.SubArea;
import ui.gui.ConstructHTMLString;
import ui.gui.components.LoadImage;
import ui.gui.components.panels.StatusPanel;
import ui.gui.components.panels.SubareaPanel;
import ui.maps.UIEnterMainMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SubareaButton extends JButton {

    public SubareaButton(SubArea subArea, SubareaPanel subareaPanel) {
//        super(LoadImage.getIcon("images/zombie.png"));
        adjustToolTipText(subArea);
        setAlignmentY(Component.CENTER_ALIGNMENT);
        setAlignmentX(Component.CENTER_ALIGNMENT);
//        add(new JLabel(subArea.getName()));
        setButtonIcon(this, subArea);
        setText(subArea.getName());
        setButtonThreatColor(this, subArea);
        addActionListener(toggleShowSubareaPanel(subareaPanel, subArea));
    }

    //        System.out.println(subArea.getName());
    private JButton setButtonIcon(SubareaButton subareaButton, SubArea subArea) {
        String imagePath = "images/" + subArea.getName() + ".png";
        try {
            if (subArea.getName() != null) {
                subareaButton.setIcon(LoadImage.getIcon(imagePath));
            }
        } catch (NullPointerException e) {}
        return subareaButton;
    }

    /* Sub area button color changes based on threat level */
    private JButton setButtonThreatColor(SubareaButton subareaButton, SubArea subArea) {
        if (subArea.getVisited()) {
            if (UIEnterMainMap.displayThreatLvl(subArea).contains("Low")) {
                subareaButton.setBackground(Color.GREEN);
            } else if (UIEnterMainMap.displayThreatLvl(subArea).contains("Safe")) {
                subareaButton.setBackground(Color.CYAN);
            } else if (UIEnterMainMap.displayThreatLvl(subArea).contains("Medium")) {
                subareaButton.setBackground(Color.ORANGE);
            } else {
                subareaButton.setBackground(Color.RED);
            }
            subareaButton.setOpaque(true);
            subareaButton.setBorderPainted(false);
        }
        return subareaButton;
    }

    private Integer adjustToolTipText(SubArea subArea) {

        if (subArea.getVisited()) {
            setToolTipText(ConstructHTMLString.parseThreatLevelHTMLString(subArea));
        } else {
            defaultToolTipText();
        }

        return 1;
    }

    private Integer defaultToolTipText() {
        setToolTipText(ConstructHTMLString.THREAT_LEVEL_HTML);
        return 1;
    }

    private ActionListener toggleShowSubareaPanel(JPanel subarea, SubArea currArea) {
        // toggle show/hide subarea panel
        return e -> {
            // toggle visibility
            subarea.setVisible(!subarea.isVisible());
            // expand subarea/this panel
            if (subarea.isVisible()) {
                if (!currArea.getVisited()) currArea.setVisited(!currArea.getVisited());
                JPanel tempParent = (JPanel) getParent().getParent().getParent();
                tempParent.removeAll();

                tempParent.add(new StatusPanel(GlobalVariables.mySquad));
                tempParent.add(subarea);

                tempParent.revalidate();
                tempParent.repaint();
                subarea.setPreferredSize(new Dimension(tempParent.getWidth(), tempParent.getHeight()));
            }
            subarea.revalidate();
            subarea.repaint();
        };
    }

}