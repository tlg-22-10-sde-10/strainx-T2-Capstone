package ui.gui.components;

import gamecontrol.GameDifficulty;
import ui.gui.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class DifficultyDialog {

    public JFrame frame;
    public JDialog dialog;
    public DifficultyDialog(JFrame frame) {
        this.frame = frame;
        dialog = new JDialog(frame);
        dialog.setLayout(new BorderLayout());
        dialog.setBounds(200,200,300,100);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this.frame);
        dialog.add(label(),BorderLayout.NORTH);
        dialog.add(buttons(),BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private JPanel label() {
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Choose a Difficulty");
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        panel.add(label);
        return panel;
    }

    private JPanel buttons() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new DifficultyButton(GameDifficulty.Easy));
        panel.add(new DifficultyButton(GameDifficulty.Medium));
        panel.add(new DifficultyButton(GameDifficulty.Hard));
        return panel;
    }
}
