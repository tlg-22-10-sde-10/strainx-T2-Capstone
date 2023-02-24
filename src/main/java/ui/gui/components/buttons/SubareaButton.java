package ui.gui.components.buttons;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.SubArea;
import gamemusic.AudioPlayer;
import ui.gui.ConstructHTMLString;
import ui.gui.components.GUISoundEffects;
import ui.gui.components.LoadImage;
import ui.gui.components.panels.StatusPanel;
import ui.gui.components.panels.SubareaPanel;
import ui.maps.UIEnterMainMap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SubareaButton extends JButton {

    public SubareaButton(SubArea subArea, SubareaPanel subareaPanel) {
        setBorderPainted(false);
        adjustToolTipText(subArea);
        setButtonIcon(this, subArea);
        setText(subArea.getName());
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.BOTTOM);
//=======
//        setAlignmentY(Component.CENTER_ALIGNMENT);
//        setAlignmentX(Component.CENTER_ALIGNMENT);
//        if (subArea.getVisited()) {
//            setButtonIcon(this, subArea);
//        }
//        setText("????");
//>>>>>>> dev
        setButtonThreatColor(this, subArea);
        addActionListener(toggleShowSubareaPanel(subareaPanel, subArea));
    }

    private JButton setButtonIcon(SubareaButton subareaButton, SubArea subArea) {
        try {
            String imagePath = "images/" + subArea.getName().toLowerCase() + ".png";
            if (subArea.getName() != null) {
                Image scaledInstance = LoadImage.getImage(imagePath).getScaledInstance(40,40,Image.SCALE_DEFAULT);
                subareaButton.setIcon(new ImageIcon(scaledInstance));
            }
        } catch (NullPointerException e) {
        }
        return subareaButton;
    }

    /* Sub area button color changes based on threat level */
    private JButton setButtonThreatColor(SubareaButton subareaButton, SubArea subArea) {
        if (subArea.getVisited()) {
            if (UIEnterMainMap.displayThreatLvl(subArea).contains("Low")) {
                subareaButton.setBackground(new Color(0, 255, 0,50));
            } else if (UIEnterMainMap.displayThreatLvl(subArea).contains("Safe")) {
                subareaButton.setBackground(new Color(0, 255, 255,50));
            } else if (UIEnterMainMap.displayThreatLvl(subArea).contains("Medium")) {
                subareaButton.setBackground(new Color(255, 200, 0,50));
            } else {
                subareaButton.setBackground(new Color(255, 0, 0,50));
            }
            setText(subArea.getName());
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
            if (GUISoundEffects.isSoundOn()) GUISoundEffects.playSound("sound/running.wav");
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