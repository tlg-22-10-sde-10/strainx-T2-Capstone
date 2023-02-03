package Contents;

public class Medical extends Item {

    private static int value;
    private static String rarity;

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        Medical.rarity = rarity;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        Medical.value = value;
    }
}