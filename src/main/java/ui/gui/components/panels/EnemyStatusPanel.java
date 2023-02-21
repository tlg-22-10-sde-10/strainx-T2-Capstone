package ui.gui.components.panels;

import gamecontrol.contents.Enemy;
import ui.gui.components.HealthBar;

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
            JLabel eLabel = new JLabel(String.format("%d. %s %s | Attack: %d | HP:", enemyNumber,
                    e.getEnemyType(),e.getName(),e.getAttack()
            ));
            ePanel.add(eLabel);
            ePanel.add(new HealthBar(e));
            enemyNumber++;
            this.add(ePanel);
        }
        return 1;
    }

}
