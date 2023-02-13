package gamecontrol.contents;
import com.fasterxml.jackson.annotation.JsonProperty;
import gamecontrol.GlobalVariables;
import java.util.Random;

public class Weapon extends Item {
    @JsonProperty("damage")
    private int weapon_base_dmg;

    private int weapon_ammo_consumption;

    private String weapon_description;

    public int getWeapon_base_dmg() {
        return weapon_base_dmg;
    }

    public void setWeapon_base_dmg(int weapon_base_dmg) {
        this.weapon_base_dmg = weapon_base_dmg;
    }

    public int getWeapon_ammo_consumption() {
        return weapon_ammo_consumption;
    }

    public void setWeapon_ammo_consumption(int weapon_ammo_consumption) {
        this.weapon_ammo_consumption = weapon_ammo_consumption;
    }

    public String getWeapon_description() {
        return weapon_description;
    }

    public void setWeapon_description(String weapon_description) {
        this.weapon_description = weapon_description;
    }
    public Weapon(String name, int dmg, String rarity, String weapon_description) {
        super.setName(name);
        this.weapon_base_dmg = dmg;
        super.setDescription(weapon_description);
        super.setQty(1);
        super.setRarity(rarity);
    }

    public Weapon() {
        this.weapon_ammo_consumption = 0;

        Random rg = new Random();
        this.weapon_base_dmg = 1 + rg.nextInt(10);

        super.setName("Bare Hands");

        this.weapon_description = "Powerful but old fashioned weapon. How old, maybe 10,000 years, who cares";
    }
}
