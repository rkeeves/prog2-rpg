package hu.unideb.inf.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public class Enemy extends Entity {

    private Weapon weaponDrop;
    private Armor armorDrop;
    private int xpDrop;

}
