package ui.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryDialog extends JDialog implements ActionListener {

        JFrame frame;
        public InventoryDialog() {
            frame = new JFrame("Temple Jog");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            frame.add(panel);

            JButton button = new JButton("Inventory");
            button.addActionListener(this);
            panel.add(button);

            frame.setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            JDialog inventoryDialog = new JDialog(frame, "Inventory List");
            inventoryDialog.setSize(200, 400);
            inventoryDialog.setVisible(true);

            inventoryDialog.add(new JLabel("first aid"));
        }
    }