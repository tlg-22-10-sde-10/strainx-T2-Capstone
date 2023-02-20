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
        int enemyNumber = 1;
        for(Enemy e : enemies) {
            JPanel ePanel = new JPanel(new FlowLayout());
            JLabel eLabel = new JLabel(String.format("%d. %s %s HP: %d/%d Attack: %d", enemyNumber,
                    e.getEnemyType(),e.getName(),e.getHP(),e.getMaxHP(),e.getAttack()
            ));
            ePanel.add(eLabel);
            ePanel.setBackground(Color.LIGHT_GRAY);
            enemyNumber++;
            this.add(ePanel);
        }
        return 1;
    }

}
