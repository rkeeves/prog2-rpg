package hu.unideb.inf.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Enemy extends Entity {

    private Weapon weaponDrop;
    private Armor armorDrop;
    private int xpDrop;

}
