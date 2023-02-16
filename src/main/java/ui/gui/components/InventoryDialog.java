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
            JDialog inventoryDialog = getjDialog();
            String[] items = getInventoryList();

            //creating a new JList and add to JScrollPane
            JList<String> itemList = new JList<>(items);
            itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(itemList);

            //add JScrollPane to dialog
            inventoryDialog.add(scrollPane, BorderLayout.CENTER);
        }

    private static String[] getInventoryList() {
            //TODO: call the function to display inventory list
        String[] items = {"axe", "first aid", "knife"};
        return items;
    }


    private JDialog getjDialog() {
        JDialog inventoryDialog = new JDialog(frame, "Inventory List");
        inventoryDialog.setSize(200, 300);
        inventoryDialog.getContentPane().setBackground(Color.red);
        inventoryDialog.setLocationRelativeTo(frame);
        inventoryDialog.setVisible(true);
        return inventoryDialog;
    }
}