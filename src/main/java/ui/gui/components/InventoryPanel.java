package ui.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class InventoryPanel extends JPanel implements ActionListener {
        private JButton inventoryButton;

        public InventoryPanel() {
//            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//            setLayout(new BoxLayout());
//            setPreferredSize(new Dimension(150, 150));// hardCoded sizing
//            setMaximumSize(new Dimension(250, 150));  // hardCoded sizing
//            setMinimumSize(new Dimension(150, 150));
//            setLocation(600, 200);
            inventoryButton = new JButton("Inventory");
            inventoryButton.addActionListener(this);
            add(inventoryButton);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == inventoryButton) {
                var items = inventoryItems();
                for (String item : items) {
                    JLabel label = new JLabel(item); //Give different colors for different items
                    label.setOpaque(true);
                    label.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                    if(item == "Item 1"){
                        label.setBackground(Color.blue);
                    } else if (item == "Item 3"){
                        label.setBackground(Color.yellow);
                    }
                    else {
                        label.setBackground(Color.darkGray);
                    }
                    add(label);
                }
                revalidate();
                repaint();
            }
        }

        private String[] inventoryItems(){
            //TODO: call the inventory function to display inventory list
            String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
            return items;
        }
    }