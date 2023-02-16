package client;

import ui.UIEnterGame;
import ui.gui.components.InventoryPanel;

import java.io.IOException;

public class StrainXMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        InventoryPanel inventoryPanel = new InventoryPanel();
        inventoryPanel.setVisible(true);
        UIEnterGame.displayEnterGame(inventoryPanel);
    }
}
