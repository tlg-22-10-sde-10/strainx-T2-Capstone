package gamecontrol.contents;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Inventory {

    private Map<String, Weapon> weapons;

    @JsonProperty("key items")
    private Map<String, KeyItem> keyItems;

    @JsonProperty("medical")
    private Map<String, Medical> medicalItem;

    public Map<String, Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(Map<String, Weapon> weapons) {
        this.weapons = weapons;
    }

    public Map<String, KeyItem> getKeyItems() {
        return keyItems;
    }

    public void setKeyItems(Map<String, KeyItem> keyItems) {
        this.keyItems = keyItems;
    }

    public Map<String, Medical> getMedicalItem() {
        return medicalItem;
    }

    public void setMedicalItem(Map<String, Medical> medicalItem) {
        this.medicalItem = medicalItem;
    }
}
