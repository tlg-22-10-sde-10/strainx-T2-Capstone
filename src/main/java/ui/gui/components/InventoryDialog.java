package ui.gui.components;

import gamecontrol.contents.KeyItem;
import gamecontrol.contents.Medical;
import gamecontrol.contents.Weapon;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static gamecontrol.GlobalVariables.InventoryMap;

public class InventoryDialog extends JDialog {

    private final JFrame frame;

    public InventoryDialog(JFrame frame) {
        super(frame);
        this.frame = frame;
        setLayout(new BorderLayout());
        setBounds(200, 200, 150, 200);
        setResizable(false);
        setLocationRelativeTo(frame);
        add(jList(), BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private JScrollPane jList() {
        String[] items = getInventoryList();
        JList<String> itemList = new JList<>(items);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setBackground(Color.CYAN);
        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setBackground(Color.DARK_GRAY);
        return scrollPane;
    }

    private String[] getInventoryList(){
        String[] allItems = Stream.concat(Arrays.stream(getAllWeapons()),
                        Stream.concat(Arrays.stream(getAllMedicals()),
                                Arrays.stream(getAllKeyItems())))
                .toArray(String[]::new);
        return allItems;
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
}