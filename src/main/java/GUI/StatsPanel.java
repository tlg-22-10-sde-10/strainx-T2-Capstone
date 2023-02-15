package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatsPanel extends JPanel{

    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;

    public StatsPanel(ArrayList<String> players){
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
        setSize(800,100);
    }

    private Integer setSubPanelDefaults(ArrayList<String> players){
        ArrayList<JPanel> panels = getAllSubPanels();
        for (JPanel p : panels) {
            p.setLayout(new FlowLayout());
            JLabel label = new JLabel(players.remove(0));
            p.add(label);
            p.add(new JLabel("HP : N/100"));
            p.add(new JLabel("ATK: N    "));

            p.setBackground(Color.DARK_GRAY);
            p.setPreferredSize(new Dimension(40,50));
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
    public void setP2(JPanel p2) {this.p2 = p2;}
    public JPanel getP3() {return p3;}
    public void setP3(JPanel p3) {this.p3 = p3;}
    public JPanel getP4() {return p4;}
    public void setP4(JPanel p4) {this.p4 = p4;}
}