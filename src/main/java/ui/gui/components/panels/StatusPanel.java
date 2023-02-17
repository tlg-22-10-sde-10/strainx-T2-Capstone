package ui.gui.components.panels;

import gamecontrol.contents.CrewMember;
import gamecontrol.contents.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StatusPanel extends JPanel{

    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;

    public StatusPanel(List<CrewMember> players ){
        super(new GridLayout(2,2));

//        this.p1 = new JPanel();
//        this.p2 = new JPanel();
//        this.p3 = new JPanel();
//        this.p4 = new JPanel();
//        add(this.p1);
//        add(this.p2);
//        add(this.p3);
//        add(this.p4);
        setSubPanelDefaults(players);
    }

    private Integer setSubPanelDefaults(List<CrewMember> players){

        //ArrayList<JPanel> panels = getAllSubPanels();

        for (CrewMember crewMember : players) {
            JPanel p = new JPanel();

            p.setLayout(new FlowLayout());

            JLabel label = new JLabel(String.format("%s %s HP : %d/%d Attack %d",
                    crewMember.getRank(),crewMember.getName(),crewMember.getHP(),crewMember.getMaxHP(),
                    crewMember.getAttack()));
            addTimer(label,crewMember);
            p.add(label);
//            p.add(new JLabel(String.format("%s %s",crewMember.getRank(),crewMember.getName())));
//            p.add(new JLabel(String.format("HP : %d/%d",crewMember.getHP(),crewMember.getMaxHP())));
//            p.add(new JLabel(String.format(" Attack %d",crewMember.attack)));

            p.setBackground(Color.GRAY);
            //p.setPreferredSize(new Dimension(40,50));
            add(p);
        }
        return 1;
    }
//    private ArrayList<JPanel> getAllSubPanels(){
//        ArrayList<JPanel> jPanels = new ArrayList<>();
//        jPanels.add(getP1());
//        jPanels.add(getP2());
//        jPanels.add(getP3());
//        jPanels.add(getP4());
//        return jPanels;
//    }

    private void addTimer(JLabel eLabel, CrewMember crewMember) {
        Timer timer = new Timer(500,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (crewMember.getHP() > 0) {
                    eLabel.setText(String.format("%s %s HP : %d/%d Attack %d",
                            crewMember.getRank(),crewMember.getName(),crewMember.getHP(),crewMember.getMaxHP(),
                            crewMember.getAttack()));
                } else {
                    eLabel.setText("");
                }
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }
    //public JPanel getP1() {return p1;}
    //public void setP1(JPanel p1) {this.p1 = p1;}
    //public JPanel getP2() {return p2;}
    //public JPanel getP3() {return p3;}
    //public JPanel getP4() {return p4;}
}