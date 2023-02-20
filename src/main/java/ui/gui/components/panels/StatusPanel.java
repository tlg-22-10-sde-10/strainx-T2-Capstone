package ui.gui.components.panels;

import gamecontrol.contents.CrewMember;
import ui.gui.components.InventoryDialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StatusPanel extends JPanel{


    public StatusPanel(List<CrewMember> players ){
//        super(new GridLayout(2,2));
        super(new FlowLayout());
        add(setSubPanelDefaults(players));
//        displayInventoryButton(this);

        add(test());
    }

    private JPanel test(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.setBorder(new LineBorder(Color.RED));
        panel.add(new JButton("Test"));
        panel.add(new JButton("Test"));
        panel.add(new JButton("Test"));
//        panel.setPreferredSize(new Dimension((int)(getParent().getWidth()*.9), getParent().getHeight()));
        return panel;
    }

    private void displayInventoryButton(StatusPanel statusPanel) {
        JButton invButton = new JButton();
        invButton.setText("Inventory");
        invButton.setVisible(true);
        add(invButton);
        invButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryDialog((JFrame) statusPanel.getTopLevelAncestor());
            }
        });
    }

    private JPanel setSubPanelDefaults(List<CrewMember> players){
        JPanel playerContainer = new JPanel(new GridLayout(2, 2));
//        playerContainer.setPreferredSize(new Dimension((int)(getParent().getWidth()*.9), getParent().getHeight()));
        for (CrewMember crewMember : players) {

            JPanel p = new JPanel();

            p.setLayout(new FlowLayout());

            JLabel label = new JLabel(String.format("%s %s HP : %d/%d Attack %d",
                    crewMember.getRank(),crewMember.getName(),crewMember.getHP(),crewMember.getMaxHP(),
                    crewMember.getAttack()));
            p.add(label);

            p.setBackground(Color.GRAY);
            playerContainer.add(p);
        }
        return playerContainer;
    }
}