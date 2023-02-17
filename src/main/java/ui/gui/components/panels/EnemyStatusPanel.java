package ui.gui.components.panels;

import gamecontrol.contents.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EnemyStatusPanel extends JPanel {


    List<JLabel> labels = new ArrayList<>();

    public EnemyStatusPanel(List<Enemy> enemies ){
        super(new GridLayout(3,2));
        populateEnemyList(enemies);
        setOpaque(false);
    }

    private Integer populateEnemyList(List<Enemy> enemies) {
        for(Enemy e : enemies) {
            JPanel ePanel = new JPanel(new FlowLayout());
            JLabel eLabel = new JLabel(String.format("%s %s HP: %d/%d Attack: %d",
                    e.getName(),e.getEnemyType(),e.getHP(),e.getMaxHP(),e.getAttack()
            ));
            addTimer(eLabel,e);
            ePanel.add(eLabel);
            ePanel.setBackground(Color.LIGHT_GRAY);
            //ePanel.setPreferredSize(new Dimension(40,50));

            this.add(ePanel);
        }
        return 1;
    }

    private void addTimer(JLabel eLabel, Enemy e) {
        Timer timer = new Timer(500,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (e.getHP() > 0) {
                    eLabel.setText(String.format("%s %s HP: %d/%d Attack: %d",
                            e.getName(),e.getEnemyType(),e.getHP(),e.getMaxHP(),e.getAttack()
                    ));
                } else {
                    eLabel.setText("");
                }
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

}
