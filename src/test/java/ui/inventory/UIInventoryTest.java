package ui.inventory;

import static org.junit.Assert.*;

import gamecontrol.GlobalVariables;
import gamecontrol.contents.Inventory;
import gamecontrol.contents.Item;
import gamecontrol.contents.ItemFactory;
import gamecontrol.contents.KeyItem;
import gamemodel.mapengine.MainMap;
import gamemodel.mapengine.SubArea;
import java.io.IOException;
import org.junit.Test;

public class UIInventoryTest {

  @Test
  public void pickUpItem1() throws IOException {
    GlobalVariables.gameInitialization();

    Item item = ItemFactory.createItem("body armor");

    UIInventory.pickUpItem(item);

    UIInventory.pickUpItem(item);

    var hp = GlobalVariables.mySquad.get(0).getMaxHP();

    assertEquals(200, hp);
  }

  @Test
  public void pickUpKeyItem() throws IOException {
    GlobalVariables.gameInitialization();

    Item item = ItemFactory.createItem("body armor");

    UIInventory.pickUpItem(item);

    UIInventory.pickUpItem(item);

    assertEquals(GlobalVariables.InventoryMap.get("body armor").getQty(), 1);
  }
}