package ui.gui.components.panels;

import gamecontrol.contents.CrewMember;
import ui.gui.components.InventoryDialog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class StatusPanel extends JPanel{

    private JPanel playerContainer;
    private JPanel buttonContainer;

    public StatusPanel(List<CrewMember> players ){
        setLayout(new BorderLayout());
        add(addContainerPlayerSubPanels(players), BorderLayout.WEST);
        add(addContainerOfButtons(this), BorderLayout.EAST);
        // TODO: BUG -> NOT RESIZING/WIPES MAIN MAP COMPONENT
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                super.componentResized(e);
//                playerContainer.setPreferredSize(new Dimension((int) (getParent().getWidth()*.9),getParent().getHeight()));
//                buttonContainer.setPreferredSize(new Dimension((int) (getParent().getWidth()*.1),getParent().getHeight()));
//            }
//        });
    }

    private JPanel addContainerPlayerSubPanels(List<CrewMember> players ){
        JPanel playerContainer = new JPanel();
        playerContainer.setLayout(new GridLayout(2, 2));
        JPanel container = addCrew(players, playerContainer);
        setPlayerContainer(container);
        return container;
    }
    private JPanel addContainerOfButtons(StatusPanel statusPanel){
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(3, 1));
        container.add(inventoryButton(statusPanel));
        container.add(new JButton("Test"));
        container.add(new JButton("Test"));

        setButtonContainer(container);
        return container;
    }
    private JButton inventoryButton(StatusPanel statusPanel) {
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
        return invButton;
    }
    private JPanel addCrew(List<CrewMember> players, JPanel container){
        int n = 0;
        for (CrewMember crewMember : players) {
            JPanel p = new JPanel();
            p.setLayout(new FlowLayout());
            JLabel label = new JLabel(String.format("%d. %s %s HP : %d/%d Attack %d", n+=1,
                    crewMember.getRank(),crewMember.getName(),crewMember.getHP(),crewMember.getMaxHP(),
                    crewMember.getAttack()+crewMember.getWeapon().getWeapon_base_dmg()));
            p.add(label);
            container.add(p);
        }
        return container;
    }

    public void setPlayerContainer(JPanel playerContainer) {this.playerContainer = playerContainer;}
    public void setButtonContainer(JPanel buttonContainer) {this.buttonContainer = buttonContainer;}
}