package Contents;

import java.util.ArrayList;

public abstract class Item {

    private String name;

    private String rarity;

    private int qty = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        if(this.getClass().equals(new Medical().getClass())) {
            this.qty += qty;
        } else {
            this.qty = 1;
        }
    }
}
