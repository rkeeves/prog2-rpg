package hu.unideb.inf.room;

import hu.unideb.inf.factory.EnemyCreationService;
import hu.unideb.inf.factory.PlayerCreationService;
import hu.unideb.inf.model.Enemy;
import hu.unideb.inf.model.Player;
import lombok.Data;

@Data
public class FirstRoom {

    private final PlayerCreationService playerCreationService;

    private final EnemyCreationService enemyCreationService;

    public void enter() {
        Player player = playerCreationService.create();
        System.out.println("Player created with details: " + player);

        Enemy enemy = enemyCreationService.create();
    }

}
