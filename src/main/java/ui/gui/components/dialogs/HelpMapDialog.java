package ui.gui.components.dialogs;

import javax.swing.*;
import java.awt.*;

public class HelpMapDialog extends JDialog {

    private final JFrame frame;
    public HelpMapDialog(JFrame frame) {
        super(frame);
        this.frame = frame;
        setLayout(new BorderLayout());
        setBounds(200,200,400,360);
        setResizable(false);
        setLocationRelativeTo(frame);
        add(label(),BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    String help = "Descriptions of following action buttons: \n\n" +
            "Map: Display the main map\n\n" +
            "Loot: Loot the items from the area which can be useful to survive and fight\n\n" +
            "Enter combat: Display combat area where player has options to fight with\n\n" +
            "Retreat: With draw from fight, no effect on player health points\n\n" +
            "Auto Combat: Fighting mode with zombies, reduce player health points\n\n" +
            "Attack Target: Fighting mode where player has to choose the target, reduce player health points\n\n" +
            "Use Items: Player can select inventory item to recover health or to equip with weapons";

    private JPanel label(){
        JTextArea multi = new JTextArea(help);
        multi.setWrapStyleWord(true);
        multi.setLineWrap(true);
        multi.setEditable(false);
        multi.setSize(400, 360);
        multi.setBackground(Color.ORANGE);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(multi);
        return panel;
    }

}
