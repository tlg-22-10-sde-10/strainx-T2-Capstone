package ui.gui;

import gamecontrol.GlobalVariables;
import gamemodel.combatengine.EngageEnemy;
import ui.gui.components.panels.TitlePanel;

import javax.swing.*;

import static gamecontrol.GlobalVariables.FINAL_BOSS;
import static java.lang.System.exit;

public class CheckHealth {
    public static void healthTimer(JFrame frame) {

        Timer playerHealthChecker = new Timer(500, event -> {
            if(GlobalVariables.mySquad.get(0).getHP() <= 0) {
                int i = JOptionPane.showConfirmDialog(frame,
                        "You died! Do you want to play again?",
                        "You Lose!",
                        JOptionPane.YES_NO_OPTION);
                if(i == JOptionPane.YES_OPTION) {
                    GlobalVariables.mySquad.get(0).setHP(100);
                    frame.getContentPane().removeAll();
                    frame.add(new TitlePanel());
                    frame.repaint();
                    frame.pack();
                } else {
                    exit(0);
                }
            }
        });
        playerHealthChecker.setRepeats(true);
        playerHealthChecker.setCoalesce(true);
        playerHealthChecker.start();

        Timer bossHealthChecker = new Timer(1000, e -> {
            if(EngageEnemy.getEnemyKIAList().stream().anyMatch(ex -> ex.getName().equals(FINAL_BOSS.getName()))) {
                int i = JOptionPane.showConfirmDialog(frame,
                        "You won! You found and defeated Patient Zero!\nDo you want to play again?",
                        "You Win!",
                        JOptionPane.YES_NO_OPTION);
                if(i == JOptionPane.YES_OPTION) {
                    GlobalVariables.mySquad.get(0).setHP(100);
                    frame.getContentPane().removeAll();
                    frame.add(new TitlePanel());
                    frame.repaint();
                    frame.pack();
                } else {
                    exit(0);
                }
            }
        });
        bossHealthChecker.setRepeats(true);
        bossHealthChecker.setCoalesce(true);
        bossHealthChecker.start();
    }
}
