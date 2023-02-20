package ui.gui.components;

import gamecontrol.GlobalVariables;
import gamemodel.combatengine.EngageEnemy;
import ui.gui.components.panels.TitlePanel;

import javax.swing.*;

import static gamecontrol.GlobalVariables.FINAL_BOSS;
import static java.lang.System.exit;

public class JOptionPanes {

    public static boolean youLosePane(JFrame frame) {
        if(GlobalVariables.mySquad.get(0).getHP() <= 0) {
            int i = JOptionPane.showConfirmDialog(frame,
                    "You died! Do you want to play again?",
                    "You Lose!",
                    JOptionPane.YES_NO_OPTION);
            if(i == JOptionPane.YES_OPTION) {
                GlobalVariables.resetGlobalVariables();
                frame.getContentPane().removeAll();
                frame.add(new TitlePanel());
                frame.repaint();
                frame.pack();
            } else {
                exit(0);
            }
            return true;
        }
        return false;
    }

    public static boolean youWinPane(JFrame frame) {
        if(EngageEnemy.getEnemyKIAList().stream().anyMatch(ex -> ex.getName().equals(FINAL_BOSS.getName()))) {
            int i = JOptionPane.showConfirmDialog(frame,
                    "You won! You found and defeated Patient Zero!\nDo you want to play again?",
                    "You Win!",
                    JOptionPane.YES_NO_OPTION);
            if(i == JOptionPane.YES_OPTION) {
                GlobalVariables.resetGlobalVariables();
                frame.getContentPane().removeAll();
                frame.add(new TitlePanel());
                frame.repaint();
                frame.pack();
            } else {
                exit(0);
            }
            return true;
        }
        return false;
    }

    public static void combatWon(JFrame frame) {
        JOptionPane.showMessageDialog(frame,"You won the combat! The area is now safe!","Combat Won",JOptionPane.INFORMATION_MESSAGE);
    }

    public static int confirmExit(JFrame frame) {
        int a = JOptionPane.showConfirmDialog(frame,"Are you sure you want to exit?");

        return a;
    }
}
