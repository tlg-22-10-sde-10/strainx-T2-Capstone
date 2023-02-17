package ui.gui.components.panels;

import gamecontrol.contents.CrewMember;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StatusPanel extends JPanel{

    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;

    public StatusPanel(List<CrewMember> players ){
        super(new GridLayout(2,2));
        this.p1 = new JPanel();
        this.p2 = new JPanel();
        this.p3 = new JPanel();
        this.p4 = new JPanel();
        add(this.p1);
        add(this.p2);
        add(this.p3);
        add(this.p4);
        setSubPanelDefaults(players);
    }

    private Integer setSubPanelDefaults(List<CrewMember> players){

        ArrayList<JPanel> panels = getAllSubPanels();
        int count = 0;
        for (JPanel p : panels) {
            p.setLayout(new FlowLayout());

            CrewMember crewMember = players.get(count);

            p.add(new JLabel(String.format("%s %s",crewMember.getRank(),crewMember.getName())));
            p.add(new JLabel(String.format("HP : %d/%d",crewMember.getHP(),crewMember.getMaxHP())));
            p.add(new JLabel(String.format(" Attack %d",crewMember.attack)));

            p.setBackground(Color.GRAY);
            p.setPreferredSize(new Dimension(40,50));
            count+=1;
        }
        return 1;
    }
    private ArrayList<JPanel> getAllSubPanels(){
        ArrayList<JPanel> jPanels = new ArrayList<>();
        jPanels.add(getP1());
        jPanels.add(getP2());
        jPanels.add(getP3());
        jPanels.add(getP4());
        return jPanels;
    }

    public JPanel getP1() {return p1;}
    public void setP1(JPanel p1) {this.p1 = p1;}
    public JPanel getP2() {return p2;}
    public JPanel getP3() {return p3;}
    public JPanel getP4() {return p4;}
}