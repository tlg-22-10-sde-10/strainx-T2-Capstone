package ui.gui;

import ui.gui.components.JOptionPanes;

import javax.swing.*;

@Deprecated
public class CheckHealth {
    public static void healthTimer(JFrame frame) {

        Timer playerHealthChecker = new Timer(500, event -> {
            JOptionPanes.youLosePane(frame);
        });
        playerHealthChecker.setRepeats(true);
        playerHealthChecker.setCoalesce(true);
        playerHealthChecker.start();

        Timer bossHealthChecker = new Timer(1000, e -> {
            JOptionPanes.youWinPane(frame);
        });
        bossHealthChecker.setRepeats(true);
        bossHealthChecker.setCoalesce(true);
        bossHealthChecker.start();
    }
}
