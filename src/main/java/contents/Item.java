package contents;

public abstract class Item {

    private String name;

    private String rarity;

    private int qty = 0;

    private String description;

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
        if(this.getClass().equals(Medical.class)) {
            this.qty += qty;
        } else {
            this.qty = 1;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
