package ui.gui.components.dialogs;

import gamecontrol.GameDifficulty;
import ui.gui.components.buttons.DifficultyButton;
import ui.gui.components.labels.ZombieLabel;
import ui.gui.components.panels.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class DifficultyDialog extends JDialog implements WindowConstants,RootPaneContainer {

    private final JFrame frame;
    public DifficultyDialog(JFrame frame) {
        super(frame);
        this.frame = frame;
        setLayout(new BorderLayout());
        setBounds(200,200,300,100);
        setResizable(false);
        setLocationRelativeTo(frame);
        add(label(),BorderLayout.NORTH);
        add(buttons(),BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        TitlePanel.getTitleStrobe().stop();
        ZombieLabel.getTimer().stop();
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

    public JFrame getFrame() {
        return frame;
    }
}
