package Contents;

import org.junit.Test;

import java.io.IOException;
import java.util.Locale;

import static Contents.ItemFactory.createItem;
import static Contents.ItemFactory.createItemName;
import static org.junit.Assert.*;

public class ItemFactoryTest {

    @Test
    public void createItemTest() throws IOException {
        var items =  createItem("M249");

        Weapon w1 = new Weapon();
        Medical m1 = new Medical();
        KeyItem k1 = new KeyItem();

        System.out.println(items.getClass());
        if (items.getClass().equals(w1.getClass())) {
            System.out.println("it is a weapon");
        }
        if (items.getClass().equals(m1.getClass())) {
            System.out.println("it is a medical");
        }
        if (items.getClass().equals(k1.getClass())) {
            System.out.println("it is a key item");
        }
    }

    @Test
    public void createItemTest2() throws IOException {
        var items = createItemName();
        System.out.println(items);
        System.out.println("M249".toLowerCase(Locale.ROOT));
    }
}