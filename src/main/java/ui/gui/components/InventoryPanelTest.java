package ui.gui.components;


import javax.swing.*;
import java.awt.*;

public class InventoryPanelTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        InventoryPanel ip = new InventoryPanel();
        frame.add(ip, BorderLayout.NORTH);
        frame.setVisible(true);
    }
}
