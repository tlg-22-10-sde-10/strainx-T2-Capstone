package ui.inventory;

import gamecontrol.GlobalVariables;
import gamecontrol.contents.ItemFactory;

import java.io.IOException;
import gamecontrol.contents.CrewMember;
import java.io.Reader;

import static gamecontrol.GlobalVariables.InventoryMap;
import static gamecontrol.GlobalVariables.mySquad;
import static ui.endgame.UITitlePage.displayTitle;
import static ui.inventory.UIInventory.pickUpItem;

public class UI_special_test {
    public static void main(String[] args) throws IOException {
        GlobalVariables.gameInitialization();

        for(int i=0; i<0; i++) {

            var item = ItemFactory.createItem();

            pickUpItem(item);
        }

        for (var k : InventoryMap.keySet()) {
            System.out.println(InventoryMap.get(k).getName() + " x" + InventoryMap.get(k).getQty());
        }

        for (CrewMember c : mySquad) {
            System.out.println(c.getName() + ": " + c.getMaxHP());
        }

        //System.out.println('\u2591');

        System.out.println('\u2580');
        System.out.println('\u2584');
        System.out.println('\u2588');

        char s = '\u2588';

        String s1 = '\u2588' + "";

        StringBuilder sb = new StringBuilder();
        sb.append(s1.repeat(25));
        System.out.println(sb);

        String jacob = displayTitle();

        var c = jacob.toCharArray();

        for(var z : c) {
            System.out.print(z);
        }

    }
}
