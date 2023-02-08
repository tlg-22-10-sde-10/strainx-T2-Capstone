package contents;

import org.junit.Test;

import java.io.IOException;
import java.util.Locale;

public class ItemFactoryTest {

    @Test
    public void createItemTest() {
        var items =  ItemFactory.createItem();

        Weapon w1 = new Weapon();
        Medical m1 = new Medical();
        KeyItem k1 = new KeyItem();

        System.out.println(items.getName());
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
    public void createItemTest2()  {
        System.out.println();
        System.out.println("M249".toLowerCase(Locale.ROOT));
    }
}