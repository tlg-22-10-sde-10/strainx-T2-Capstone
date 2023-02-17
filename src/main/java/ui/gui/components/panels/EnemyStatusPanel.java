package ui.gui.components.panels;

import gamecontrol.contents.Enemy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EnemyStatusPanel extends JPanel {


    public EnemyStatusPanel(List<Enemy> enemies ){
        super(new GridLayout(3,2));
        populateEnemyList(enemies);
        setOpaque(false);
    }

    private Integer populateEnemyList(List<Enemy> enemies) {
        for(Enemy e : enemies) {
            JPanel ePanel = new JPanel(new FlowLayout());
            ePanel.add(new JLabel(String.format("%s %s",e.getName(),e.getEnemyType())));
            ePanel.add(new JLabel(String.format("HP: %d/%d",e.getHP(),e.getMaxHP())));
            ePanel.add(new JLabel(String.format("Attack: %d",e.getAttack())));
            ePanel.setBackground(Color.LIGHT_GRAY);
            //ePanel.setPreferredSize(new Dimension(40,50));
            this.add(ePanel);
        }
        return 1;
    }

}
