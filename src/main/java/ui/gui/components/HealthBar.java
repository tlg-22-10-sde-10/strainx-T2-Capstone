package ui.gui.components;

import gamecontrol.contents.CrewMember;
import gamecontrol.contents.Enemy;

import javax.swing.*;

public class HealthBar extends JSlider {
    private int currentHealth;
    private int maxHealth;
    private static final int MIN_HEALTH = 0;

    public HealthBar(CrewMember crewMember) {
        super(MIN_HEALTH,crewMember.getMaxHP(),
                (Math.max(crewMember.getHP(), 0)));
        setCurrentHealth(Math.max(crewMember.getHP(), 0));
        setMaxHealth(crewMember.getMaxHP());
        setUI(new CustomSliderUI(this));
        setPaintTrack(true);
        setPaintLabels(true);
        setEnabled(false);
        setMajorTickSpacing((getMaxHealth()/4));
        setMinorTickSpacing(5);
        setToolTipText(Integer.toString(crewMember.getHP()));
    }

    public HealthBar(Enemy enemy) {
        super(MIN_HEALTH,enemy.getMaxHP(),
                (Math.max(enemy.getHP(), 0)));
        currentHealth = Math.max(enemy.getHP(), 0);
        maxHealth = enemy.getMaxHP();
        setUI(new CustomSliderUI(this));
        setPaintTrack(true);
        setPaintLabels(true);
        setEnabled(false);
        setMajorTickSpacing((getMaxHealth()/4));
        setMinorTickSpacing(5);
        setToolTipText(Integer.toString(enemy.getHP()));
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
