package ui.gui.components.panels;

import gamecontrol.contents.CrewMember;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatusPanel extends JPanel{


    public StatusPanel(List<CrewMember> players ){
        super(new GridLayout(2,2));

        setSubPanelDefaults(players);
    }

    private Integer setSubPanelDefaults(List<CrewMember> players){

        for (CrewMember crewMember : players) {
            JPanel p = new JPanel();

            p.setLayout(new FlowLayout());

            JLabel label = new JLabel(String.format("%s %s HP : %d/%d Attack %d",
                    crewMember.getRank(),crewMember.getName(),crewMember.getHP(),crewMember.getMaxHP(),
                    crewMember.getAttack()));
            p.add(label);

            p.setBackground(Color.GRAY);
            add(p);
        }
        return 1;
    }
}