package ui.gui.components.dialogs;
import gamecontrol.contents.*;
import ui.gui.components.panels.StatusPanel;
import ui.gui.GUIEntry;
import ui.inventory.UIInventory;
import static gamecontrol.GlobalVariables.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class InventoryDialog extends JDialog {

    public JList<String> jList;

    public InventoryDialog(JFrame frame) {
        super(frame);
        this.jList = createJList();
        setLayout(new BorderLayout());
        setBounds(200, 200, 300, 200);
        setResizable(false);
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        add(addScrollPane(), BorderLayout.CENTER);
        addWindowListener(GUIEntry.disableJFrame(frame));
    }

    private JList<String> createJList(){
        // TODO Figure out why the tooltip isn't showing
        jList = new JList<>(getInventoryList());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                handleSelection(getJList().getSelectedValue());
                this.dispose(); // auto close dialog
            }
        });
        return jList;
    }

    private Integer handleSelection(String selected){
        Item type;
        if(determineItemType(selected) == null ) return -1;

        type = determineItemType(selected);
        if( type.getClass().equals(Weapon.class) ) handleEquipWeapon(selected);
        if( type.getClass().equals(Medical.class) ) handleEquipMedicals(selected);
        if( type.getClass().equals(KeyItem.class) ) handleKeyItems(selected);
        StatusPanel.statusPanel.repaint();
        return 1;
    }
    private Integer handleKeyItems(String selected){
        List<Item> keyItems = InventoryMap.values().stream()
                .filter(i -> i.getClass().equals(KeyItem.class))
                .collect(Collectors.toList());
        Item inventoryItem = findInventoryItem(keyItems, selected);
        KeyItem ki = (KeyItem)(inventoryItem);
        String out;
        if(ki.getName().equals("An odd note")){
            out = "<html>" +
                    ki.getName().toUpperCase() + " <br>" +
                    ki.getDescription() + " <br>" +
                    "</html>";
        }
        else{
            out = "<html>" +
                    ki.getName().toUpperCase() + " <br>" +
                    "Damage: "+ki.getDamage() + " <br>" +
                    "max HP: "+ki.getHealth() + " <br>" +
                    "</html>";
        }
        JOptionPane.showMessageDialog(this, out);
        return 1;
    }

    private Integer handleEquipMedicals(String selected){
//        TODO: breaks on cancel btn

        List<Item> medicals = InventoryMap.values().stream()
                .filter(i -> i.getClass().equals(Medical.class))
                .collect(Collectors.toList());
        Item medItem = findInventoryItem(medicals, selected);
        Medical newMeds = (Medical)(medItem);

        try {
            int inputNumber = parseUserInput();
            CrewMember soldier = mySquad.get(inputNumber);
            int HP = Math.min(soldier.getMaxHP(), soldier.getHP() + newMeds.getValue());
            soldier.setHP(HP);
            UIInventory.useItems(newMeds.getName());
        } catch(IndexOutOfBoundsException e){
            return -1;
        }
        return 1;
    }
    private Integer handleEquipWeapon(String selected){
        List<Item> weapons = InventoryMap.values().stream()
                .filter(i -> i.getClass().equals(Weapon.class))
                .collect(Collectors.toList());
        if(findInventoryItem(weapons,selected) != null){
            Item weapon = findInventoryItem(weapons, selected);
            Item w = InventoryMap.get(weapon.getName());
            Weapon weaponFromInv = (Weapon)(w);
            mySquad.get(0).setWeapon(weaponFromInv);
            String message = "<html> Equipped: "+
                    weaponFromInv.getName() +
                    "<br>Base DMG: "+
                    weaponFromInv.getWeapon_base_dmg()+
                    "<br>" + weaponFromInv.getDescription();
            JOptionPane.showMessageDialog(this,message);
        }
        return 1;
    }
    private Item determineItemType(String itemName){
        Item targetItem = null;
        for (Item value : InventoryMap.values()) {
            if( itemName.contains(value.getName()) ) targetItem = value;
        }
        return targetItem;
    }
    private int parseUserInput() {
        String userInput;
        int squid = 0;
        while (squid < 1 || squid > mySquad.size()) {
            try {
                userInput = JOptionPane.showInputDialog(this,"Choose soldier number use item",JOptionPane.INFORMATION_MESSAGE);
                if(userInput == null ) break;
                squid = Integer.parseInt(userInput);
                if(squid < 1 || squid > mySquad.size()) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Error that input is not a valid number!","Invalid Input Error",JOptionPane.ERROR_MESSAGE);
                squid = -1;
            }
        }
        squid--;
        return squid;
    }
    private Item findInventoryItem(List<Item> items, String selected){
        Item item = null;
        for (Item i : items) {
            if(selected.contains(i.getName())) item = i;
        }
        return item;
    }

    private JScrollPane addScrollPane() {
        JScrollPane scrollPane = new JScrollPane(getJList());
        scrollPane.setBackground(Color.DARK_GRAY);
        return scrollPane;
    }
    private String[] getInventoryList(){
        return Stream.concat(Arrays.stream(getAllWeapons()),
                        Stream.concat(Arrays.stream(getAllMedicals()),
                                Arrays.stream(getAllKeyItems())))
                .toArray(String[]::new);
    }
    private String[] getAllMedicals(){
        var medicals = InventoryMap.values().stream()
                .filter(i->i.getClass().equals(Medical.class))
                .collect(Collectors.toList());
        String[] weaponsArray = new String[medicals.size()];
        for(int x = 0; x < medicals.size(); x++){
            String weaponsInfo = medicals.get(x).getName() + "  x" + medicals.get(x).getQty();
            weaponsArray[x] = weaponsInfo;
        }
        return weaponsArray;
    }
    private String[] getAllWeapons(){
        var weapons = InventoryMap.values().stream()
                .filter(i->i.getClass().equals(Weapon.class))
                .collect(Collectors.toList());
        String[] weaponsArray = new String[weapons.size()];
        for(int x = 0; x < weapons.size(); x++){
            String weaponsInfo = weapons.get(x).getName() + "  x" + weapons.get(x).getQty();
            weaponsArray[x] = weaponsInfo;
        }
        return weaponsArray;
    }
    private String[] getAllKeyItems(){
        var keyItems = InventoryMap.values().stream()
                .filter(i->i.getClass().equals(KeyItem.class))
                .collect(Collectors.toList());
        String[] weaponsArray = new String[keyItems.size()];
        for(int x = 0; x < keyItems.size(); x++){
            String weaponsInfo = keyItems.get(x).getName() + "  x" + keyItems.get(x).getQty();
            weaponsArray[x] = weaponsInfo;
        }
        return weaponsArray;
    }

    public JList<String> getJList() { return jList; }
}