package ui.gui.components.buttons;

import gamecontrol.GlobalVariables;
import gamecontrol.contents.CrewMember;
import gamecontrol.contents.KeyItem;
import gamecontrol.contents.Weapon;
import ui.inventory.UIInventory;

import javax.swing.*;
import java.awt.*;

public class GodModeButton extends JButton {

    public GodModeButton(String text) {
        super(text);
        addActionListener(e -> enableGodMode());
        setEnabled(true);
        setFocusable(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void enableGodMode() {
        if (GlobalVariables.inGameMap != null) {
            String userInput = JOptionPane.showInputDialog(this,"Enter a password:","Cheat Codes",JOptionPane.INFORMATION_MESSAGE);
            if (userInput != null) {
                userInput = userInput.toLowerCase();

                switch (userInput) {
                    case "iddqd":
                        populateGodMode();
                        break;
                    case "idkfa":
                        getAllWeapons();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void populateGodMode() {
        KeyItem passWordNote = new KeyItem();
        passWordNote.setQty(1);
        passWordNote.setDescription("In case I can't remember! " + GlobalVariables.getPassWord());
        passWordNote.setName("An odd note");
        passWordNote.setRarity("unique");

        Weapon crystalFemur = new Weapon("Crystal Femur",899,"unique","It is painfully clear that this Amethyst Crystal Femur is shaped like a human femur, it is also painfully clear that these aliens must be cheap because Amethyst crystals are some of the cheapest crystals you can get in the market these days.");
        UIInventory.pickUpItem(crystalFemur);
        UIInventory.pickUpItem(GlobalVariables.itemTemplatesCollection.getKeyItems().get("body armor"));
        UIInventory.pickUpItem(GlobalVariables.itemTemplatesCollection.getKeyItems().get("squad equipment upgrades"));
        UIInventory.pickUpItem(passWordNote);
        for(int i = 0; i < 99; i++) {
            UIInventory.pickUpItem(GlobalVariables.itemTemplatesCollection.getMedicalItem().get("first aid kit"));
        }
        for(CrewMember c : GlobalVariables.mySquad) {
            c.setMaxHP(999);
            c.setHP(999);
            c.setAttack(100);
        }
    }

    private void getAllWeapons() {
        Weapon crystalFemur = new Weapon("Crystal Femur",899,"unique","It is painfully clear that this Amethyst Crystal Femur is shaped like a human femur, it is also painfully clear that these aliens must be cheap because Amethyst crystals are some of the cheapest crystals you can get in the market these days.");
        UIInventory.pickUpItem(crystalFemur);
        for(Weapon w : GlobalVariables.itemTemplatesCollection.getWeapons().values()) {
            UIInventory.pickUpItem(w);
        }
    }
}
